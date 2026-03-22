package io.grpc.android;

import android.net.LocalSocketAddress;
import io.grpc.ChannelCredentials;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannelBuilder;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;
import javax.net.SocketFactory;

/* JADX INFO: loaded from: classes3.dex */
public final class UdsChannelBuilder {

    @Nullable
    private static final Class<? extends ManagedChannelBuilder> OKHTTP_CHANNEL_BUILDER_CLASS = findOkHttp();

    private static Class<? extends ManagedChannelBuilder> findOkHttp() {
        try {
            return Class.forName("io.grpc.okhttp.OkHttpChannelBuilder").asSubclass(ManagedChannelBuilder.class);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static ManagedChannelBuilder<?> forPath(String str, LocalSocketAddress.Namespace namespace) {
        Class<? extends ManagedChannelBuilder> cls = OKHTTP_CHANNEL_BUILDER_CLASS;
        if (cls == null) {
            throw new UnsupportedOperationException("OkHttpChannelBuilder not found on the classpath");
        }
        try {
            ManagedChannelBuilder<?> managedChannelBuilderCast = cls.cast(cls.getMethod("forTarget", String.class, ChannelCredentials.class).invoke(null, "dns:///localhost", InsecureChannelCredentials.create()));
            cls.getMethod("socketFactory", SocketFactory.class).invoke(managedChannelBuilderCast, new UdsSocketFactory(str, namespace));
            return managedChannelBuilderCast;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to create OkHttpChannelBuilder", e);
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException("Failed to create OkHttpChannelBuilder", e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("Failed to create OkHttpChannelBuilder", e3);
        }
    }

    private UdsChannelBuilder() {
    }
}
