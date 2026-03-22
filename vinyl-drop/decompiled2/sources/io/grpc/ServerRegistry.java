package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ServerProvider;
import io.grpc.ServiceProviders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes3.dex */
public final class ServerRegistry {
    private static ServerRegistry instance;
    private static final Logger logger = Logger.getLogger(ServerRegistry.class.getName());
    private final LinkedHashSet<ServerProvider> allProviders = new LinkedHashSet<>();
    private List<ServerProvider> effectiveProviders = Collections.emptyList();

    public synchronized void register(ServerProvider serverProvider) {
        addProvider(serverProvider);
        refreshProviders();
    }

    private synchronized void addProvider(ServerProvider serverProvider) {
        Preconditions.checkArgument(serverProvider.isAvailable(), "isAvailable() returned false");
        this.allProviders.add(serverProvider);
    }

    public synchronized void deregister(ServerProvider serverProvider) {
        this.allProviders.remove(serverProvider);
        refreshProviders();
    }

    private synchronized void refreshProviders() {
        ArrayList arrayList = new ArrayList(this.allProviders);
        Collections.sort(arrayList, Collections.reverseOrder(new Comparator<ServerProvider>() { // from class: io.grpc.ServerRegistry.1
            @Override // java.util.Comparator
            public int compare(ServerProvider serverProvider, ServerProvider serverProvider2) {
                return serverProvider.priority() - serverProvider2.priority();
            }
        }));
        this.effectiveProviders = Collections.unmodifiableList(arrayList);
    }

    public static synchronized ServerRegistry getDefaultRegistry() {
        if (instance == null) {
            List<ServerProvider> listLoadAll = ServiceProviders.loadAll(ServerProvider.class, getHardCodedClasses(), ServerProvider.class.getClassLoader(), new ServerPriorityAccessor());
            instance = new ServerRegistry();
            for (ServerProvider serverProvider : listLoadAll) {
                logger.fine("Service loader found " + serverProvider);
                instance.addProvider(serverProvider);
            }
            instance.refreshProviders();
        }
        return instance;
    }

    synchronized List<ServerProvider> providers() {
        return this.effectiveProviders;
    }

    ServerProvider provider() {
        List<ServerProvider> listProviders = providers();
        if (listProviders.isEmpty()) {
            return null;
        }
        return listProviders.get(0);
    }

    static List<Class<?>> getHardCodedClasses() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(Class.forName("io.grpc.okhttp.OkHttpServerProvider"));
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Unable to find OkHttpServerProvider", (Throwable) e);
        }
        return Collections.unmodifiableList(arrayList);
    }

    ServerBuilder<?> newServerBuilderForPort(int i, ServerCredentials serverCredentials) {
        if (providers().isEmpty()) {
            throw new ProviderNotFoundException("No functional server found. Try adding a dependency on the grpc-netty, grpc-netty-shaded, or grpc-okhttp artifact");
        }
        StringBuilder sb = new StringBuilder();
        for (ServerProvider serverProvider : providers()) {
            ServerProvider.NewServerBuilderResult newServerBuilderResultNewServerBuilderForPort = serverProvider.newServerBuilderForPort(i, serverCredentials);
            if (newServerBuilderResultNewServerBuilderForPort.getServerBuilder() != null) {
                return newServerBuilderResultNewServerBuilderForPort.getServerBuilder();
            }
            sb.append("; ");
            sb.append(serverProvider.getClass().getName());
            sb.append(": ");
            sb.append(newServerBuilderResultNewServerBuilderForPort.getError());
        }
        throw new ProviderNotFoundException(sb.substring(2));
    }

    private static final class ServerPriorityAccessor implements ServiceProviders.PriorityAccessor<ServerProvider> {
        private ServerPriorityAccessor() {
        }

        @Override // io.grpc.ServiceProviders.PriorityAccessor
        public boolean isAvailable(ServerProvider serverProvider) {
            return serverProvider.isAvailable();
        }

        @Override // io.grpc.ServiceProviders.PriorityAccessor
        public int getPriority(ServerProvider serverProvider) {
            return serverProvider.priority();
        }
    }

    public static final class ProviderNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ProviderNotFoundException(String str) {
            super(str);
        }
    }
}
