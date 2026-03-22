package io.grpc.android;

import android.net.LocalSocketAddress;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.SocketFactory;

/* JADX INFO: loaded from: classes3.dex */
class UdsSocketFactory extends SocketFactory {
    private final LocalSocketAddress localSocketAddress;

    public UdsSocketFactory(String str, LocalSocketAddress.Namespace namespace) {
        this.localSocketAddress = new LocalSocketAddress(str, namespace);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        return create();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        return createAndConnect();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return createAndConnect();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return createAndConnect();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return createAndConnect();
    }

    private Socket create() {
        return new UdsSocket(this.localSocketAddress);
    }

    private Socket createAndConnect() throws IOException {
        Socket socketCreate = create();
        socketCreate.connect(new InetSocketAddress(0));
        return socketCreate;
    }
}
