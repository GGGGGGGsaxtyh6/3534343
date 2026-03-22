package io.grpc.util;

import com.google.common.base.Preconditions;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;

/* JADX INFO: loaded from: classes3.dex */
public final class AdvancedTlsX509KeyManager extends X509ExtendedKeyManager {
    private static final Logger log = Logger.getLogger(AdvancedTlsX509KeyManager.class.getName());
    private volatile KeyInfo keyInfo;

    public interface Closeable extends java.io.Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        void close();
    }

    @Override // javax.net.ssl.X509KeyManager
    public PrivateKey getPrivateKey(String str) {
        if (str.equals("default")) {
            return this.keyInfo.key;
        }
        return null;
    }

    @Override // javax.net.ssl.X509KeyManager
    public X509Certificate[] getCertificateChain(String str) {
        if (str.equals("default")) {
            return (X509Certificate[]) Arrays.copyOf(this.keyInfo.certs, this.keyInfo.certs.length);
        }
        return null;
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getClientAliases(String str, Principal[] principalArr) {
        return new String[]{"default"};
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
        return "default";
    }

    @Override // javax.net.ssl.X509ExtendedKeyManager
    public String chooseEngineClientAlias(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
        return "default";
    }

    @Override // javax.net.ssl.X509KeyManager
    public String[] getServerAliases(String str, Principal[] principalArr) {
        return new String[]{"default"};
    }

    @Override // javax.net.ssl.X509KeyManager
    public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
        return "default";
    }

    @Override // javax.net.ssl.X509ExtendedKeyManager
    public String chooseEngineServerAlias(String str, Principal[] principalArr, SSLEngine sSLEngine) {
        return "default";
    }

    public void updateIdentityCredentials(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
        this.keyInfo = new KeyInfo((PrivateKey) Preconditions.checkNotNull(privateKey, "key"), (X509Certificate[]) Preconditions.checkNotNull(x509CertificateArr, "certs"));
    }

    public Closeable updateIdentityCredentialsFromFile(File file, File file2, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) throws GeneralSecurityException, IOException {
        if (!readAndUpdate(file, file2, 0L, 0L).success) {
            throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
        }
        final ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay = scheduledExecutorService.scheduleWithFixedDelay(new LoadFilePathExecution(file, file2), j, j, timeUnit);
        return new Closeable() { // from class: io.grpc.util.AdvancedTlsX509KeyManager.1
            @Override // io.grpc.util.AdvancedTlsX509KeyManager.Closeable, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                scheduledFutureScheduleWithFixedDelay.cancel(false);
            }
        };
    }

    public void updateIdentityCredentialsFromFile(File file, File file2) throws GeneralSecurityException, IOException {
        if (!readAndUpdate(file, file2, 0L, 0L).success) {
            throw new GeneralSecurityException("Files were unmodified before their initial update. Probably a bug.");
        }
    }

    private static class KeyInfo {
        final X509Certificate[] certs;
        final PrivateKey key;

        public KeyInfo(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
            this.key = privateKey;
            this.certs = x509CertificateArr;
        }
    }

    private class LoadFilePathExecution implements Runnable {
        File certFile;
        File keyFile;
        long currentKeyTime = 0;
        long currentCertTime = 0;

        public LoadFilePathExecution(File file, File file2) {
            this.keyFile = file;
            this.certFile = file2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                UpdateResult andUpdate = AdvancedTlsX509KeyManager.this.readAndUpdate(this.keyFile, this.certFile, this.currentKeyTime, this.currentCertTime);
                if (andUpdate.success) {
                    this.currentKeyTime = andUpdate.keyTime;
                    this.currentCertTime = andUpdate.certTime;
                }
            } catch (IOException | GeneralSecurityException e) {
                AdvancedTlsX509KeyManager.log.log(Level.SEVERE, "Failed refreshing private key and certificate chain from files. Using previous ones", e);
            }
        }
    }

    private static class UpdateResult {
        long certTime;
        long keyTime;
        boolean success;

        public UpdateResult(boolean z, long j, long j2) {
            this.success = z;
            this.keyTime = j;
            this.certTime = j2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003a, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003f, code lost:
    
        throw r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public io.grpc.util.AdvancedTlsX509KeyManager.UpdateResult readAndUpdate(java.io.File r7, java.io.File r8, long r9, long r11) throws java.security.GeneralSecurityException, java.io.IOException {
        /*
            r6 = this;
            r2 = r9
            long r9 = r7.lastModified()
            r4 = r11
            long r11 = r8.lastModified()
            int r0 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r0 == 0) goto L40
            int r0 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r0 == 0) goto L40
            java.io.FileInputStream r1 = new java.io.FileInputStream
            r1.<init>(r7)
            java.security.PrivateKey r7 = io.grpc.util.CertificateUtils.getPrivateKey(r1)     // Catch: java.lang.Throwable -> L3a
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3a
            r2.<init>(r8)     // Catch: java.lang.Throwable -> L3a
            java.security.cert.X509Certificate[] r8 = io.grpc.util.CertificateUtils.getX509Certificates(r2)     // Catch: java.lang.Throwable -> L34
            r6.updateIdentityCredentials(r7, r8)     // Catch: java.lang.Throwable -> L34
            io.grpc.util.AdvancedTlsX509KeyManager$UpdateResult r7 = new io.grpc.util.AdvancedTlsX509KeyManager$UpdateResult     // Catch: java.lang.Throwable -> L34
            r8 = 1
            r7.<init>(r8, r9, r11)     // Catch: java.lang.Throwable -> L34
            r2.close()     // Catch: java.lang.Throwable -> L3a
            r1.close()
            return r7
        L34:
            r0 = move-exception
            r7 = r0
            r2.close()     // Catch: java.lang.Throwable -> L3a
            throw r7     // Catch: java.lang.Throwable -> L3a
        L3a:
            r0 = move-exception
            r7 = r0
            r1.close()
            throw r7
        L40:
            io.grpc.util.AdvancedTlsX509KeyManager$UpdateResult r0 = new io.grpc.util.AdvancedTlsX509KeyManager$UpdateResult
            r1 = 0
            r0.<init>(r1, r2, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.util.AdvancedTlsX509KeyManager.readAndUpdate(java.io.File, java.io.File, long, long):io.grpc.util.AdvancedTlsX509KeyManager$UpdateResult");
    }
}
