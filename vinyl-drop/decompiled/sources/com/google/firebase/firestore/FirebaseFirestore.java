package com.google.firebase.firestore;

import android.app.Activity;
import android.content.Context;
import androidx.core.util.Consumer;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.FirebaseAppCheckTokenProvider;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.ComponentProvider;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.remote.FirestoreChannel;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ByteBufferInputStream;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.inject.Deferred;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class FirebaseFirestore {
    private static final String TAG = "FirebaseFirestore";
    private final CredentialsProvider<String> appCheckProvider;
    private final CredentialsProvider<User> authProvider;
    private final Function<FirebaseFirestoreSettings, ComponentProvider> componentProviderFactory;
    private final Context context;
    private final DatabaseId databaseId;
    private EmulatedServiceSettings emulatorSettings;
    private final FirebaseApp firebaseApp;
    private final InstanceRegistry instanceRegistry;
    private final GrpcMetadataProvider metadataProvider;
    private final String persistenceKey;
    private PersistentCacheIndexManager persistentCacheIndexManager;
    private final UserDataReader userDataReader;
    final FirestoreClientProvider clientProvider = new FirestoreClientProvider(new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda5
        @Override // com.google.firebase.firestore.util.Function
        public final Object apply(Object obj) {
            return this.f$0.newClient((AsyncQueue) obj);
        }
    });
    private FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();

    public interface InstanceRegistry {
        void remove(String str);
    }

    private static FirebaseApp getDefaultFirebaseApp() {
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        if (firebaseApp != null) {
            return firebaseApp;
        }
        throw new IllegalStateException("You must call FirebaseApp.initializeApp first.");
    }

    public static FirebaseFirestore getInstance() {
        return getInstance(getDefaultFirebaseApp(), DatabaseId.DEFAULT_DATABASE_ID);
    }

    public static FirebaseFirestore getInstance(FirebaseApp firebaseApp) {
        return getInstance(firebaseApp, DatabaseId.DEFAULT_DATABASE_ID);
    }

    public static FirebaseFirestore getInstance(String str) {
        return getInstance(getDefaultFirebaseApp(), str);
    }

    public static FirebaseFirestore getInstance(FirebaseApp firebaseApp, String str) {
        Preconditions.checkNotNull(firebaseApp, "Provided FirebaseApp must not be null.");
        Preconditions.checkNotNull(str, "Provided database name must not be null.");
        FirestoreMultiDbComponent firestoreMultiDbComponent = (FirestoreMultiDbComponent) firebaseApp.get(FirestoreMultiDbComponent.class);
        Preconditions.checkNotNull(firestoreMultiDbComponent, "Firestore component is not present.");
        return firestoreMultiDbComponent.get(str);
    }

    static FirebaseFirestore newInstance(Context context, FirebaseApp firebaseApp, Deferred<InternalAuthProvider> deferred, Deferred<InteropAppCheckTokenProvider> deferred2, String str, InstanceRegistry instanceRegistry, GrpcMetadataProvider grpcMetadataProvider) {
        String projectId = firebaseApp.getOptions().getProjectId();
        if (projectId == null) {
            throw new IllegalArgumentException("FirebaseOptions.getProjectId() cannot be null");
        }
        return new FirebaseFirestore(context, DatabaseId.forDatabase(projectId, str), firebaseApp.getName(), new FirebaseAuthCredentialsProvider(deferred), new FirebaseAppCheckTokenProvider(deferred2), new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda7
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return ComponentProvider.defaultFactory((FirebaseFirestoreSettings) obj);
            }
        }, firebaseApp, instanceRegistry, grpcMetadataProvider);
    }

    FirebaseFirestore(Context context, DatabaseId databaseId, String str, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, Function<FirebaseFirestoreSettings, ComponentProvider> function, FirebaseApp firebaseApp, InstanceRegistry instanceRegistry, GrpcMetadataProvider grpcMetadataProvider) {
        this.context = (Context) Preconditions.checkNotNull(context);
        this.databaseId = (DatabaseId) Preconditions.checkNotNull((DatabaseId) Preconditions.checkNotNull(databaseId));
        this.userDataReader = new UserDataReader(databaseId);
        this.persistenceKey = (String) Preconditions.checkNotNull(str);
        this.authProvider = (CredentialsProvider) Preconditions.checkNotNull(credentialsProvider);
        this.appCheckProvider = (CredentialsProvider) Preconditions.checkNotNull(credentialsProvider2);
        this.componentProviderFactory = (Function) Preconditions.checkNotNull(function);
        this.firebaseApp = firebaseApp;
        this.instanceRegistry = instanceRegistry;
        this.metadataProvider = grpcMetadataProvider;
    }

    public FirebaseFirestoreSettings getFirestoreSettings() {
        return this.settings;
    }

    public void setFirestoreSettings(FirebaseFirestoreSettings firebaseFirestoreSettings) {
        Preconditions.checkNotNull(firebaseFirestoreSettings, "Provided settings must not be null.");
        synchronized (this.databaseId) {
            FirebaseFirestoreSettings firebaseFirestoreSettingsMergeEmulatorSettings = mergeEmulatorSettings(firebaseFirestoreSettings, this.emulatorSettings);
            if (this.clientProvider.isConfigured() && !this.settings.equals(firebaseFirestoreSettingsMergeEmulatorSettings)) {
                throw new IllegalStateException("FirebaseFirestore has already been started and its settings can no longer be changed. You can only call setFirestoreSettings() before calling any other methods on a FirebaseFirestore object.");
            }
            this.settings = firebaseFirestoreSettingsMergeEmulatorSettings;
        }
    }

    public void useEmulator(String str, int i) {
        synchronized (this.clientProvider) {
            if (this.clientProvider.isConfigured()) {
                throw new IllegalStateException("Cannot call useEmulator() after instance has already been initialized.");
            }
            EmulatedServiceSettings emulatedServiceSettings = new EmulatedServiceSettings(str, i);
            this.emulatorSettings = emulatedServiceSettings;
            this.settings = mergeEmulatorSettings(this.settings, emulatedServiceSettings);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FirestoreClient newClient(AsyncQueue asyncQueue) {
        FirestoreClient firestoreClient;
        synchronized (this.clientProvider) {
            firestoreClient = new FirestoreClient(this.context, new DatabaseInfo(this.databaseId, this.persistenceKey, this.settings.getHost(), this.settings.isSslEnabled()), this.authProvider, this.appCheckProvider, asyncQueue, this.metadataProvider, this.componentProviderFactory.apply(this.settings));
        }
        return firestoreClient;
    }

    private FirebaseFirestoreSettings mergeEmulatorSettings(FirebaseFirestoreSettings firebaseFirestoreSettings, EmulatedServiceSettings emulatedServiceSettings) {
        if (emulatedServiceSettings == null) {
            return firebaseFirestoreSettings;
        }
        if (!FirebaseFirestoreSettings.DEFAULT_HOST.equals(firebaseFirestoreSettings.getHost())) {
            Logger.warn(TAG, "Host has been set in FirebaseFirestoreSettings and useEmulator, emulator host will be used.", new Object[0]);
        }
        return new FirebaseFirestoreSettings.Builder(firebaseFirestoreSettings).setHost(emulatedServiceSettings.getHost() + ":" + emulatedServiceSettings.getPort()).setSslEnabled(false).build();
    }

    public FirebaseApp getApp() {
        return this.firebaseApp;
    }

    @Deprecated
    public Task<Void> setIndexConfiguration(String str) {
        this.clientProvider.ensureConfigured();
        Preconditions.checkState(this.settings.isPersistenceEnabled(), "Cannot enable indexes when persistence is disabled");
        final ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("indexes")) {
                JSONArray jSONArray = jSONObject.getJSONArray("indexes");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("collectionGroup");
                    ArrayList arrayList2 = new ArrayList();
                    JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("fields");
                    for (int i2 = 0; jSONArrayOptJSONArray != null && i2 < jSONArrayOptJSONArray.length(); i2++) {
                        JSONObject jSONObject3 = jSONArrayOptJSONArray.getJSONObject(i2);
                        com.google.firebase.firestore.model.FieldPath fieldPathFromServerFormat = com.google.firebase.firestore.model.FieldPath.fromServerFormat(jSONObject3.getString("fieldPath"));
                        if ("CONTAINS".equals(jSONObject3.optString("arrayConfig"))) {
                            arrayList2.add(FieldIndex.Segment.create(fieldPathFromServerFormat, FieldIndex.Segment.Kind.CONTAINS));
                        } else if ("ASCENDING".equals(jSONObject3.optString("order"))) {
                            arrayList2.add(FieldIndex.Segment.create(fieldPathFromServerFormat, FieldIndex.Segment.Kind.ASCENDING));
                        } else {
                            arrayList2.add(FieldIndex.Segment.create(fieldPathFromServerFormat, FieldIndex.Segment.Kind.DESCENDING));
                        }
                    }
                    arrayList.add(FieldIndex.create(-1, string, arrayList2, FieldIndex.INITIAL_STATE));
                }
            }
            return (Task) this.clientProvider.call(new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda17
                @Override // com.google.firebase.firestore.util.Function
                public final Object apply(Object obj) {
                    return ((FirestoreClient) obj).configureFieldIndexes(arrayList);
                }
            });
        } catch (JSONException e) {
            throw new IllegalArgumentException("Failed to parse index configuration", e);
        }
    }

    public PersistentCacheIndexManager getPersistentCacheIndexManager() {
        this.clientProvider.ensureConfigured();
        if (this.persistentCacheIndexManager == null && (this.settings.isPersistenceEnabled() || (this.settings.getCacheSettings() instanceof PersistentCacheSettings))) {
            this.persistentCacheIndexManager = new PersistentCacheIndexManager(this.clientProvider);
        }
        return this.persistentCacheIndexManager;
    }

    public CollectionReference collection(String str) {
        Preconditions.checkNotNull(str, "Provided collection path must not be null.");
        this.clientProvider.ensureConfigured();
        return new CollectionReference(ResourcePath.fromString(str), this);
    }

    public DocumentReference document(String str) {
        Preconditions.checkNotNull(str, "Provided document path must not be null.");
        this.clientProvider.ensureConfigured();
        return DocumentReference.forPath(ResourcePath.fromString(str), this);
    }

    public Query collectionGroup(String str) {
        Preconditions.checkNotNull(str, "Provided collection ID must not be null.");
        if (str.contains(DomExceptionUtils.SEPARATOR)) {
            throw new IllegalArgumentException(String.format("Invalid collectionId '%s'. Collection IDs must not contain '/'.", str));
        }
        this.clientProvider.ensureConfigured();
        return new Query(new com.google.firebase.firestore.core.Query(ResourcePath.EMPTY, str), this);
    }

    private <ResultT> Task<ResultT> runTransaction(final TransactionOptions transactionOptions, final Transaction.Function<ResultT> function, final Executor executor) {
        this.clientProvider.ensureConfigured();
        final Function function2 = new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda13
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return this.f$0.m608x9099e0de(executor, function, (com.google.firebase.firestore.core.Transaction) obj);
            }
        };
        return (Task) this.clientProvider.call(new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda14
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return ((FirestoreClient) obj).transaction(transactionOptions, function2);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$runTransaction$2$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ Task m608x9099e0de(Executor executor, final Transaction.Function function, final com.google.firebase.firestore.core.Transaction transaction) {
        return Tasks.call(executor, new Callable() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m607x911046dd(function, transaction);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$runTransaction$1$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ Object m607x911046dd(Transaction.Function function, com.google.firebase.firestore.core.Transaction transaction) throws Exception {
        return function.apply(new Transaction(transaction, this));
    }

    public <TResult> Task<TResult> runTransaction(Transaction.Function<TResult> function) {
        return runTransaction(TransactionOptions.DEFAULT, function);
    }

    public <TResult> Task<TResult> runTransaction(TransactionOptions transactionOptions, Transaction.Function<TResult> function) {
        Preconditions.checkNotNull(function, "Provided transaction update function must not be null.");
        return runTransaction(transactionOptions, function, com.google.firebase.firestore.core.Transaction.getDefaultExecutor());
    }

    public WriteBatch batch() {
        this.clientProvider.ensureConfigured();
        return new WriteBatch(this);
    }

    public Task<Void> runBatch(WriteBatch.Function function) {
        WriteBatch writeBatchBatch = batch();
        function.apply(writeBatchBatch);
        return writeBatchBatch.commit();
    }

    public Task<Void> terminate() {
        this.instanceRegistry.remove(getDatabaseId().getDatabaseId());
        return this.clientProvider.terminate();
    }

    public Task<Void> waitForPendingWrites() {
        return (Task) this.clientProvider.call(new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return ((FirestoreClient) obj).waitForPendingWrites();
            }
        });
    }

    public Task<Void> enableNetwork() {
        return (Task) this.clientProvider.call(new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return ((FirestoreClient) obj).enableNetwork();
            }
        });
    }

    public Task<Void> disableNetwork() {
        return (Task) this.clientProvider.call(new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda6
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return ((FirestoreClient) obj).disableNetwork();
            }
        });
    }

    public static void setLoggingEnabled(boolean z) {
        if (z) {
            Logger.setLogLevel(Logger.Level.DEBUG);
        } else {
            Logger.setLogLevel(Logger.Level.WARN);
        }
    }

    public Task<Void> clearPersistence() {
        return (Task) this.clientProvider.executeIfShutdown(new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda11
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return this.f$0.clearPersistence((Executor) obj);
            }
        }, new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda12
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return Tasks.forException(new FirebaseFirestoreException("Persistence cannot be cleared while the firestore instance is running.", FirebaseFirestoreException.Code.FAILED_PRECONDITION));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Void> clearPersistence(Executor executor) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        executor.execute(new Runnable() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m605x40c7eca0(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: renamed from: lambda$clearPersistence$5$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ void m605x40c7eca0(TaskCompletionSource taskCompletionSource) {
        try {
            SQLitePersistence.clearPersistence(this.context, this.databaseId, this.persistenceKey);
            taskCompletionSource.setResult(null);
        } catch (FirebaseFirestoreException e) {
            taskCompletionSource.setException(e);
        }
    }

    public ListenerRegistration addSnapshotsInSyncListener(Runnable runnable) {
        return addSnapshotsInSyncListener(Executors.DEFAULT_CALLBACK_EXECUTOR, runnable);
    }

    public ListenerRegistration addSnapshotsInSyncListener(Activity activity, Runnable runnable) {
        return addSnapshotsInSyncListener(Executors.DEFAULT_CALLBACK_EXECUTOR, activity, runnable);
    }

    public ListenerRegistration addSnapshotsInSyncListener(Executor executor, Runnable runnable) {
        return addSnapshotsInSyncListener(executor, null, runnable);
    }

    public LoadBundleTask loadBundle(final InputStream inputStream) {
        final LoadBundleTask loadBundleTask = new LoadBundleTask();
        this.clientProvider.procedure(new Consumer() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda1
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                ((FirestoreClient) obj).loadBundle(inputStream, loadBundleTask);
            }
        });
        return loadBundleTask;
    }

    public LoadBundleTask loadBundle(byte[] bArr) {
        return loadBundle(new ByteArrayInputStream(bArr));
    }

    public LoadBundleTask loadBundle(ByteBuffer byteBuffer) {
        return loadBundle(new ByteBufferInputStream(byteBuffer));
    }

    public Task<Query> getNamedQuery(final String str) {
        return ((Task) this.clientProvider.call(new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda9
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return ((FirestoreClient) obj).getNamedQuery(str);
            }
        })).continueWith(new Continuation() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda10
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f$0.m606xe28faffc(task);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getNamedQuery$8$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ Query m606xe28faffc(Task task) throws Exception {
        com.google.firebase.firestore.core.Query query = (com.google.firebase.firestore.core.Query) task.getResult();
        if (query != null) {
            return new Query(query, this);
        }
        return null;
    }

    private ListenerRegistration addSnapshotsInSyncListener(Executor executor, final Activity activity, final Runnable runnable) {
        final AsyncEventListener asyncEventListener = new AsyncEventListener(executor, new EventListener() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda15
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                FirebaseFirestore.lambda$addSnapshotsInSyncListener$9(runnable, (Void) obj, firebaseFirestoreException);
            }
        });
        return (ListenerRegistration) this.clientProvider.call(new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda16
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return FirebaseFirestore.lambda$addSnapshotsInSyncListener$11(asyncEventListener, activity, (FirestoreClient) obj);
            }
        });
    }

    static /* synthetic */ void lambda$addSnapshotsInSyncListener$9(Runnable runnable, Void r2, FirebaseFirestoreException firebaseFirestoreException) {
        Assert.hardAssert(firebaseFirestoreException == null, "snapshots-in-sync listeners should never get errors.", new Object[0]);
        runnable.run();
    }

    static /* synthetic */ ListenerRegistration lambda$addSnapshotsInSyncListener$11(final AsyncEventListener asyncEventListener, Activity activity, final FirestoreClient firestoreClient) {
        firestoreClient.addSnapshotsInSyncListener(asyncEventListener);
        return ActivityScope.bind(activity, new ListenerRegistration() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda8
            @Override // com.google.firebase.firestore.ListenerRegistration
            public final void remove() {
                FirebaseFirestore.lambda$addSnapshotsInSyncListener$10(asyncEventListener, firestoreClient);
            }
        });
    }

    static /* synthetic */ void lambda$addSnapshotsInSyncListener$10(AsyncEventListener asyncEventListener, FirestoreClient firestoreClient) {
        asyncEventListener.mute();
        firestoreClient.removeSnapshotsInSyncListener(asyncEventListener);
    }

    <T> T callClient(Function<FirestoreClient, T> function) {
        return (T) this.clientProvider.call(function);
    }

    DatabaseId getDatabaseId() {
        return this.databaseId;
    }

    UserDataReader getUserDataReader() {
        return this.userDataReader;
    }

    void validateReference(DocumentReference documentReference) {
        Preconditions.checkNotNull(documentReference, "Provided DocumentReference must not be null.");
        if (documentReference.getFirestore() != this) {
            throw new IllegalArgumentException("Provided document reference is from a different Cloud Firestore instance.");
        }
    }

    static void setClientLanguage(String str) {
        FirestoreChannel.setClientLanguage(str);
    }

    public PipelineSource pipeline() {
        this.clientProvider.ensureConfigured();
        return new PipelineSource(this);
    }

    RealtimePipelineSource realtimePipeline() {
        this.clientProvider.ensureConfigured();
        return new RealtimePipelineSource(this);
    }
}
