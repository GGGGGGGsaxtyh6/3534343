package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.common.zzh;
import com.google.android.gms.internal.common.zzi;
import com.google.android.gms.internal.common.zzj;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* JADX INFO: loaded from: classes2.dex */
public class ProviderInstaller {
    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    private static final GoogleApiAvailabilityLight zza = GoogleApiAvailabilityLight.getInstance();
    private static final Object zzb = new Object();
    private static Method zzc = null;
    private static boolean zzd = false;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
    public interface ProviderInstallListener {
        void onProviderInstallFailed(int i, Intent intent);

        void onProviderInstalled();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0052 A[Catch: all -> 0x00a7, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0014, B:7:0x001b, B:12:0x0040, B:13:0x0045, B:10:0x0029, B:15:0x0047, B:26:0x0091, B:27:0x0096, B:29:0x0098, B:30:0x00a6, B:18:0x0052, B:20:0x0057, B:23:0x0081), top: B:36:0x0014, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0091 A[Catch: all -> 0x00a7, TryCatch #1 {, blocks: (B:4:0x0014, B:7:0x001b, B:12:0x0040, B:13:0x0045, B:10:0x0029, B:15:0x0047, B:26:0x0091, B:27:0x0096, B:29:0x0098, B:30:0x00a6, B:18:0x0052, B:20:0x0057, B:23:0x0081), top: B:36:0x0014, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0098 A[Catch: all -> 0x00a7, TryCatch #1 {, blocks: (B:4:0x0014, B:7:0x001b, B:12:0x0040, B:13:0x0045, B:10:0x0029, B:15:0x0047, B:26:0x0091, B:27:0x0096, B:29:0x0098, B:30:0x00a6, B:18:0x0052, B:20:0x0057, B:23:0x0081), top: B:36:0x0014, inners: #0, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void installIfNeeded(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        Context remoteContext;
        Context moduleContext;
        Preconditions.checkNotNull(context, "Context must not be null");
        zza.verifyGooglePlayServicesIsAvailable(context, 11925000);
        long jUptimeMillis = SystemClock.uptimeMillis();
        synchronized (zzb) {
            Context context2 = null;
            if (!zzd) {
                try {
                    moduleContext = DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.providerinstaller.dynamite").getModuleContext();
                } catch (DynamiteModule.LoadingException e) {
                    String message = e.getMessage();
                    String.valueOf(message);
                    Log.w("ProviderInstaller", "Failed to load providerinstaller module: ".concat(String.valueOf(message)));
                    moduleContext = null;
                }
                if (moduleContext != null) {
                    zzb(moduleContext, context, "com.google.android.gms.providerinstaller.ProviderInstallerImpl");
                    return;
                }
                boolean z = zzd;
                remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
                if (remoteContext == null) {
                    zzd = true;
                    if (!z) {
                        try {
                            zzj.zzb("com.google.android.gms.common.security.ProviderInstallerImpl", "reportRequestStats2", remoteContext.getClassLoader(), zzi.zzb(Context.class, context), zzh.zza(jUptimeMillis), zzh.zza(SystemClock.uptimeMillis()));
                        } catch (Exception e2) {
                            Log.w("ProviderInstaller", "Failed to report request stats: ".concat(e2.toString()));
                        }
                    }
                    context2 = remoteContext;
                }
                if (context2 == null) {
                    zzb(context2, context, "com.google.android.gms.common.security.ProviderInstallerImpl");
                    return;
                } else {
                    Log.e("ProviderInstaller", "Failed to get remote context");
                    throw new GooglePlayServicesNotAvailableException(8);
                }
            }
            boolean z2 = zzd;
            remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
            if (remoteContext == null) {
            }
            if (context2 == null) {
            }
        }
    }

    public static void installIfNeededAsync(Context context, ProviderInstallListener providerInstallListener) {
        Preconditions.checkNotNull(context, "Context must not be null");
        Preconditions.checkNotNull(providerInstallListener, "Listener must not be null");
        Preconditions.checkMainThread("Must be called on the UI thread");
        new zza(context, providerInstallListener).execute(new Void[0]);
    }

    private static void zzb(Context context, Context context2, String str) throws GooglePlayServicesNotAvailableException {
        try {
            if (zzc == null) {
                zzc = context.getClassLoader().loadClass(str).getMethod("insertProvider", Context.class);
            }
            zzc.invoke(null, context);
        } catch (Exception e) {
            Throwable cause = e.getCause();
            if (Log.isLoggable("ProviderInstaller", 6)) {
                String string = cause == null ? e.toString() : cause.toString();
                String.valueOf(string);
                Log.e("ProviderInstaller", "Failed to install provider: ".concat(String.valueOf(string)));
            }
            throw new GooglePlayServicesNotAvailableException(8);
        }
    }
}
