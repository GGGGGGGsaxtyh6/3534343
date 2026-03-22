package com.google.firebase.firestore.core;

import android.content.Context;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.IndexBackfiller;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.Scheduler;
import com.google.firebase.firestore.remote.ConnectivityMonitor;
import com.google.firebase.firestore.remote.Datastore;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.remote.RemoteComponenetProvider;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ComponentProvider {
    private EventManager eventManager;
    private Scheduler garbageCollectionScheduler;
    private IndexBackfiller indexBackfiller;
    private LocalStore localStore;
    private Persistence persistence;
    private RemoteComponenetProvider remoteProvider = new RemoteComponenetProvider();
    private RemoteStore remoteStore;
    protected final FirebaseFirestoreSettings settings;
    private SyncEngine syncEngine;

    protected abstract EventManager createEventManager(Configuration configuration);

    protected abstract Scheduler createGarbageCollectionScheduler(Configuration configuration);

    protected abstract IndexBackfiller createIndexBackfiller(Configuration configuration);

    protected abstract LocalStore createLocalStore(Configuration configuration);

    protected abstract Persistence createPersistence(Configuration configuration);

    protected abstract RemoteStore createRemoteStore(Configuration configuration);

    protected abstract SyncEngine createSyncEngine(Configuration configuration);

    public ComponentProvider(FirebaseFirestoreSettings firebaseFirestoreSettings) {
        this.settings = firebaseFirestoreSettings;
    }

    public static ComponentProvider defaultFactory(FirebaseFirestoreSettings firebaseFirestoreSettings) {
        if (firebaseFirestoreSettings.isPersistenceEnabled()) {
            return new SQLiteComponentProvider(firebaseFirestoreSettings);
        }
        return new MemoryComponentProvider(firebaseFirestoreSettings);
    }

    public static final class Configuration {
        public final CredentialsProvider<String> appCheckProvider;
        public final AsyncQueue asyncQueue;
        public final CredentialsProvider<User> authProvider;
        public final Context context;
        public final DatabaseInfo databaseInfo;
        public final User initialUser;
        public final int maxConcurrentLimboResolutions;
        public final GrpcMetadataProvider metadataProvider;

        public Configuration(Context context, AsyncQueue asyncQueue, DatabaseInfo databaseInfo, User user, int i, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, GrpcMetadataProvider grpcMetadataProvider) {
            this.context = context;
            this.asyncQueue = asyncQueue;
            this.databaseInfo = databaseInfo;
            this.initialUser = user;
            this.maxConcurrentLimboResolutions = i;
            this.authProvider = credentialsProvider;
            this.appCheckProvider = credentialsProvider2;
            this.metadataProvider = grpcMetadataProvider;
        }
    }

    public void setRemoteProvider(RemoteComponenetProvider remoteComponenetProvider) {
        Assert.hardAssert(this.remoteStore == null, "cannot set remoteProvider after initialize", new Object[0]);
        this.remoteProvider = remoteComponenetProvider;
    }

    public RemoteSerializer getRemoteSerializer() {
        return this.remoteProvider.getRemoteSerializer();
    }

    public Datastore getDatastore() {
        return this.remoteProvider.getDatastore();
    }

    public Persistence getPersistence() {
        return (Persistence) Assert.hardAssertNonNull(this.persistence, "persistence not initialized yet", new Object[0]);
    }

    public Scheduler getGarbageCollectionScheduler() {
        return this.garbageCollectionScheduler;
    }

    public IndexBackfiller getIndexBackfiller() {
        return this.indexBackfiller;
    }

    public LocalStore getLocalStore() {
        return (LocalStore) Assert.hardAssertNonNull(this.localStore, "localStore not initialized yet", new Object[0]);
    }

    public SyncEngine getSyncEngine() {
        return (SyncEngine) Assert.hardAssertNonNull(this.syncEngine, "syncEngine not initialized yet", new Object[0]);
    }

    public RemoteStore getRemoteStore() {
        return (RemoteStore) Assert.hardAssertNonNull(this.remoteStore, "remoteStore not initialized yet", new Object[0]);
    }

    public EventManager getEventManager() {
        return (EventManager) Assert.hardAssertNonNull(this.eventManager, "eventManager not initialized yet", new Object[0]);
    }

    protected ConnectivityMonitor getConnectivityMonitor() {
        return this.remoteProvider.getConnectivityMonitor();
    }

    public void initialize(Configuration configuration) {
        this.remoteProvider.initialize(configuration);
        Persistence persistenceCreatePersistence = createPersistence(configuration);
        this.persistence = persistenceCreatePersistence;
        persistenceCreatePersistence.start();
        this.localStore = createLocalStore(configuration);
        this.remoteStore = createRemoteStore(configuration);
        this.syncEngine = createSyncEngine(configuration);
        this.eventManager = createEventManager(configuration);
        this.localStore.start();
        this.remoteStore.start();
        this.garbageCollectionScheduler = createGarbageCollectionScheduler(configuration);
        this.indexBackfiller = createIndexBackfiller(configuration);
    }
}
