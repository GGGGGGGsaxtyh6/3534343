package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.core.ComponentProvider;
import com.google.firebase.firestore.util.Assert;

/* JADX INFO: loaded from: classes3.dex */
public class RemoteComponenetProvider {
    private ConnectivityMonitor connectivityMonitor;
    private Datastore datastore;
    private FirestoreChannel firestoreChannel;
    private GrpcCallProvider grpcCallProvider;
    private RemoteSerializer remoteSerializer;

    public void initialize(ComponentProvider.Configuration configuration) {
        this.remoteSerializer = createRemoteSerializer(configuration);
        this.grpcCallProvider = createGrpcCallProvider(configuration);
        this.firestoreChannel = createFirestoreChannel(configuration);
        this.datastore = createDatastore(configuration);
        this.connectivityMonitor = createConnectivityMonitor(configuration);
    }

    public GrpcCallProvider getGrpcCallProvider() {
        return (GrpcCallProvider) Assert.hardAssertNonNull(this.grpcCallProvider, "grpcCallProvider not initialized yet", new Object[0]);
    }

    public RemoteSerializer getRemoteSerializer() {
        return (RemoteSerializer) Assert.hardAssertNonNull(this.remoteSerializer, "remoteSerializer not initialized yet", new Object[0]);
    }

    public FirestoreChannel getFirestoreChannel() {
        return (FirestoreChannel) Assert.hardAssertNonNull(this.firestoreChannel, "firestoreChannel not initialized yet", new Object[0]);
    }

    public Datastore getDatastore() {
        return (Datastore) Assert.hardAssertNonNull(this.datastore, "datastore not initialized yet", new Object[0]);
    }

    public ConnectivityMonitor getConnectivityMonitor() {
        return (ConnectivityMonitor) Assert.hardAssertNonNull(this.connectivityMonitor, "connectivityMonitor not initialized yet", new Object[0]);
    }

    protected GrpcCallProvider createGrpcCallProvider(ComponentProvider.Configuration configuration) {
        return new GrpcCallProvider(configuration.asyncQueue, configuration.context, configuration.databaseInfo, new FirestoreCallCredentials(configuration.authProvider, configuration.appCheckProvider));
    }

    protected RemoteSerializer createRemoteSerializer(ComponentProvider.Configuration configuration) {
        return new RemoteSerializer(configuration.databaseInfo.getDatabaseId());
    }

    protected FirestoreChannel createFirestoreChannel(ComponentProvider.Configuration configuration) {
        return new FirestoreChannel(configuration.asyncQueue, configuration.authProvider, configuration.appCheckProvider, configuration.databaseInfo.getDatabaseId(), configuration.metadataProvider, getGrpcCallProvider());
    }

    protected Datastore createDatastore(ComponentProvider.Configuration configuration) {
        return new Datastore(configuration.asyncQueue, getRemoteSerializer(), getFirestoreChannel());
    }

    protected ConnectivityMonitor createConnectivityMonitor(ComponentProvider.Configuration configuration) {
        return new AndroidConnectivityMonitor(configuration.context);
    }
}
