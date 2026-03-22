package com.google.android.gms.dynamite;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.camera.video.internal.workaround.DefaultEncoderProfilesProvider;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import dalvik.system.DelegateLastClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class DynamiteModule {
    public static final int LOCAL = -1;
    public static final int NONE = 0;
    public static final int NO_SELECTION = 0;
    public static final int REMOTE = 1;
    private static Boolean zzc = null;
    private static String zzd = null;
    private static boolean zze = false;
    private static int zzf = -1;
    private static Boolean zzg;
    private static zzp zzl;
    private static zzq zzm;
    private final Context zzk;
    private static final ThreadLocal zzh = new ThreadLocal();
    private static final ThreadLocal zzi = new zze();
    private static final VersionPolicy.IVersions zzj = new zzf();
    public static final VersionPolicy PREFER_REMOTE = new zzg();
    public static final VersionPolicy PREFER_LOCAL = new zzh();
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzi();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzj();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzk();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzl();
    public static final VersionPolicy zza = new zzm();
    public static final VersionPolicy zzb = new zzc();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
    public static class LoadingException extends Exception {
        /* synthetic */ LoadingException(String str, Throwable th, byte[] bArr) {
            super(str, th);
        }

        /* synthetic */ LoadingException(String str, byte[] bArr) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
    public interface VersionPolicy {

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
        public interface IVersions {
            int zza(Context context, String str, boolean z) throws LoadingException;

            int zzb(Context context, String str);
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
        public static class SelectionResult {
            public int localVersion = 0;
            public int remoteVersion = 0;
            public int selection = 0;
        }

        SelectionResult selectModule(Context context, String str, IVersions iVersions) throws LoadingException;
    }

    private DynamiteModule(Context context) {
        Preconditions.checkNotNull(context);
        this.zzk = context;
    }

    public static int getLocalVersion(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".ModuleDescriptor");
            Class<?> clsLoadClass = classLoader.loadClass(sb.toString());
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.equal(declaredField.get(null), str)) {
                return declaredField2.getInt(null);
            }
            String strValueOf = String.valueOf(declaredField.get(null));
            StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf).length() + 50 + String.valueOf(str).length() + 1);
            sb2.append("Module descriptor id '");
            sb2.append(strValueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e) {
            String message = e.getMessage();
            String.valueOf(message);
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(message)));
            return 0;
        }
    }

    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00c8 A[Catch: all -> 0x0366, TRY_LEAVE, TryCatch #5 {all -> 0x0366, blocks: (B:5:0x0041, B:9:0x00ba, B:14:0x00c2, B:17:0x00c8, B:30:0x00ea, B:103:0x0274, B:104:0x027f, B:106:0x0281, B:108:0x0283, B:109:0x028b, B:111:0x028d, B:113:0x02b5, B:115:0x02c4, B:125:0x02e8, B:126:0x02f0, B:127:0x02f1, B:128:0x0312, B:129:0x0313, B:130:0x0365), top: B:147:0x0041, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static DynamiteModule load(Context context, VersionPolicy versionPolicy, String str) throws LoadingException {
        DynamiteModule dynamiteModuleZze;
        int i;
        Boolean bool;
        IObjectWrapper iObjectWrapperZze;
        zzq zzqVar;
        Boolean boolValueOf;
        IObjectWrapper iObjectWrapperZze2;
        Context applicationContext = context.getApplicationContext();
        byte[] bArr = null;
        if (applicationContext == null) {
            throw new LoadingException("null application Context", null);
        }
        ThreadLocal threadLocal = zzh;
        zzn zznVar = (zzn) threadLocal.get();
        zzn zznVar2 = new zzn(null);
        threadLocal.set(zznVar2);
        ThreadLocal threadLocal2 = zzi;
        long jLongValue = ((Long) threadLocal2.get()).longValue();
        try {
            threadLocal2.set(Long.valueOf(SystemClock.uptimeMillis()));
            VersionPolicy.SelectionResult selectionResultSelectModule = versionPolicy.selectModule(context, str, zzj);
            int i2 = selectionResultSelectModule.localVersion;
            int i3 = selectionResultSelectModule.remoteVersion;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 26 + String.valueOf(i2).length() + 19 + String.valueOf(str).length() + 1 + String.valueOf(i3).length());
            sb.append("Considering local module ");
            sb.append(str);
            sb.append(":");
            sb.append(i2);
            sb.append(" and remote module ");
            sb.append(str);
            sb.append(":");
            sb.append(i3);
            Log.i("DynamiteModule", sb.toString());
            int i4 = selectionResultSelectModule.selection;
            if (i4 != 0) {
                if (i4 != -1) {
                    if (i4 == 1 || selectionResultSelectModule.remoteVersion != 0) {
                        if (i4 != -1) {
                            DynamiteModule dynamiteModuleZze2 = zze(applicationContext, str);
                            if (jLongValue == 0) {
                                threadLocal2.remove();
                            } else {
                                threadLocal2.set(Long.valueOf(jLongValue));
                            }
                            Cursor cursor = zznVar2.zza;
                            if (cursor != null) {
                                cursor.close();
                            }
                            threadLocal.set(zznVar);
                            return dynamiteModuleZze2;
                        }
                        if (i4 != 1) {
                            StringBuilder sb2 = new StringBuilder(String.valueOf(i4).length() + 36);
                            sb2.append("VersionPolicy returned invalid code:");
                            sb2.append(i4);
                            throw new LoadingException(sb2.toString(), null);
                        }
                        try {
                            i = selectionResultSelectModule.remoteVersion;
                        } catch (LoadingException e) {
                            String message = e.getMessage();
                            StringBuilder sb3 = new StringBuilder(String.valueOf(message).length() + 30);
                            sb3.append("Failed to load remote module: ");
                            sb3.append(message);
                            Log.w("DynamiteModule", sb3.toString());
                            int i5 = selectionResultSelectModule.localVersion;
                            if (i5 == 0 || versionPolicy.selectModule(context, str, new zzo(i5, 0)).selection != -1) {
                                throw new LoadingException("Remote load failed. No local fallback found.", e, null);
                            }
                            dynamiteModuleZze = zze(applicationContext, str);
                        }
                        try {
                            synchronized (DynamiteModule.class) {
                                if (!zzb(context)) {
                                    throw new LoadingException("Remote loading disabled", null);
                                }
                                bool = zzc;
                            }
                            if (bool == null) {
                                throw new LoadingException("Failed to determine which loading route to use.", null);
                            }
                            if (bool.booleanValue()) {
                                StringBuilder sb4 = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(i).length());
                                sb4.append("Selected remote version of ");
                                sb4.append(str);
                                sb4.append(", version >= ");
                                sb4.append(i);
                                Log.i("DynamiteModule", sb4.toString());
                                synchronized (DynamiteModule.class) {
                                    zzqVar = zzm;
                                }
                                if (zzqVar == null) {
                                    throw new LoadingException("DynamiteLoaderV2 was not cached.", null);
                                }
                                zzn zznVar3 = (zzn) threadLocal.get();
                                if (zznVar3 == null || zznVar3.zza == null) {
                                    throw new LoadingException("No result cursor", null);
                                }
                                Context applicationContext2 = context.getApplicationContext();
                                Cursor cursor2 = zznVar3.zza;
                                ObjectWrapper.wrap(null);
                                synchronized (DynamiteModule.class) {
                                    boolValueOf = Boolean.valueOf(zzf >= 2);
                                }
                                if (boolValueOf.booleanValue()) {
                                    Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                                    iObjectWrapperZze2 = zzqVar.zzf(ObjectWrapper.wrap(applicationContext2), str, i, ObjectWrapper.wrap(cursor2));
                                } else {
                                    Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                                    iObjectWrapperZze2 = zzqVar.zze(ObjectWrapper.wrap(applicationContext2), str, i, ObjectWrapper.wrap(cursor2));
                                }
                                Context context2 = (Context) ObjectWrapper.unwrap(iObjectWrapperZze2);
                                if (context2 == null) {
                                    throw new LoadingException("Failed to get module context", bArr);
                                }
                                dynamiteModuleZze = new DynamiteModule(context2);
                            } else {
                                StringBuilder sb5 = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(i).length());
                                sb5.append("Selected remote version of ");
                                sb5.append(str);
                                sb5.append(", version >= ");
                                sb5.append(i);
                                Log.i("DynamiteModule", sb5.toString());
                                zzp zzpVarZzg = zzg(context);
                                if (zzpVarZzg == null) {
                                    throw new LoadingException("Failed to create IDynamiteLoader.", null);
                                }
                                int iZzi = zzpVarZzg.zzi();
                                if (iZzi >= 3) {
                                    zzn zznVar4 = (zzn) threadLocal.get();
                                    if (zznVar4 == null) {
                                        throw new LoadingException("No cached result cursor holder", null);
                                    }
                                    iObjectWrapperZze = zzpVarZzg.zzk(ObjectWrapper.wrap(context), str, i, ObjectWrapper.wrap(zznVar4.zza));
                                } else if (iZzi == 2) {
                                    Log.w("DynamiteModule", "IDynamite loader version = 2");
                                    iObjectWrapperZze = zzpVarZzg.zzg(ObjectWrapper.wrap(context), str, i);
                                } else {
                                    Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                    iObjectWrapperZze = zzpVarZzg.zze(ObjectWrapper.wrap(context), str, i);
                                }
                                Object objUnwrap = ObjectWrapper.unwrap(iObjectWrapperZze);
                                if (objUnwrap == null) {
                                    throw new LoadingException("Failed to load remote module.", null);
                                }
                                dynamiteModuleZze = new DynamiteModule((Context) objUnwrap);
                            }
                            return dynamiteModuleZze;
                        } catch (RemoteException e2) {
                            throw new LoadingException("Failed to load remote module.", e2, null);
                        } catch (LoadingException e3) {
                            throw e3;
                        } catch (Throwable th) {
                            CrashUtils.addDynamiteErrorToDropBox(context, th);
                            throw new LoadingException("Failed to load remote module.", th, null);
                        }
                    }
                } else if (selectionResultSelectModule.localVersion != 0) {
                    i4 = -1;
                    if (i4 == 1) {
                    }
                    if (i4 != -1) {
                    }
                }
            }
            int i6 = selectionResultSelectModule.localVersion;
            int i7 = selectionResultSelectModule.remoteVersion;
            StringBuilder sb6 = new StringBuilder(String.valueOf(str).length() + 46 + String.valueOf(i6).length() + 23 + String.valueOf(i7).length() + 1);
            sb6.append("No acceptable module ");
            sb6.append(str);
            sb6.append(" found. Local version is ");
            sb6.append(i6);
            sb6.append(" and remote version is ");
            sb6.append(i7);
            sb6.append(".");
            throw new LoadingException(sb6.toString(), null);
        } finally {
            if (jLongValue == 0) {
                zzi.remove();
            } else {
                zzi.set(Long.valueOf(jLongValue));
            }
            Cursor cursor3 = zznVar2.zza;
            if (cursor3 != null) {
                cursor3.close();
            }
            zzh.set(zznVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:96:0x0182 A[Catch: all -> 0x01ed, TRY_ENTER, TRY_LEAVE, TryCatch #9 {all -> 0x01ed, blocks: (B:4:0x0006, B:62:0x00e9, B:64:0x00ef, B:70:0x0119, B:92:0x0173, B:96:0x0182, B:117:0x01e5, B:118:0x01e8, B:112:0x01dc, B:68:0x00f6, B:122:0x01ec, B:5:0x0007, B:8:0x000d, B:9:0x0029, B:60:0x00e6, B:20:0x004a, B:42:0x00a4, B:45:0x00a7, B:53:0x00c0, B:61:0x00e8, B:59:0x00c6), top: B:135:0x0006, inners: #6, #8 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:111:0x01da -> B:127:0x01df). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:112:0x01dc -> B:127:0x01df). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int zza(Context context, String str, boolean z) {
        Field declaredField;
        Throwable th;
        RemoteException remoteException;
        Cursor cursor;
        try {
            synchronized (DynamiteModule.class) {
                Boolean bool = zzc;
                int iZzf = 0;
                if (bool == null) {
                    try {
                        declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                        String string = e.toString();
                        StringBuilder sb = new StringBuilder(string.length() + 30);
                        sb.append("Failed to load module via V2: ");
                        sb.append(string);
                        Log.w("DynamiteModule", sb.toString());
                        bool = Boolean.FALSE;
                    }
                    synchronized (declaredField.getDeclaringClass()) {
                        ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                        if (classLoader == ClassLoader.getSystemClassLoader()) {
                            bool = Boolean.FALSE;
                        } else if (classLoader != null) {
                            try {
                                zzf(classLoader);
                            } catch (LoadingException unused) {
                            }
                            bool = Boolean.TRUE;
                        } else {
                            if (!zzb(context)) {
                                return 0;
                            }
                            if (zze || Boolean.TRUE.equals(null)) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                                bool = Boolean.FALSE;
                            } else {
                                try {
                                    int iZzc = zzc(context, str, z, true);
                                    String str2 = zzd;
                                    if (str2 != null && !str2.isEmpty()) {
                                        ClassLoader classLoaderZza = zzb.zza();
                                        if (classLoaderZza == null) {
                                            if (Build.VERSION.SDK_INT >= 29) {
                                                String str3 = zzd;
                                                Preconditions.checkNotNull(str3);
                                                classLoaderZza = new DelegateLastClassLoader(str3, ClassLoader.getSystemClassLoader());
                                            } else {
                                                String str4 = zzd;
                                                Preconditions.checkNotNull(str4);
                                                classLoaderZza = new zzd(str4, ClassLoader.getSystemClassLoader());
                                            }
                                        }
                                        zzf(classLoaderZza);
                                        declaredField.set(null, classLoaderZza);
                                        zzc = Boolean.TRUE;
                                        return iZzc;
                                    }
                                    return iZzc;
                                } catch (LoadingException unused2) {
                                    declaredField.set(null, ClassLoader.getSystemClassLoader());
                                    bool = Boolean.FALSE;
                                }
                            }
                        }
                        zzc = bool;
                    }
                }
                if (bool.booleanValue()) {
                    try {
                        return zzc(context, str, z, false);
                    } catch (LoadingException e2) {
                        String message = e2.getMessage();
                        StringBuilder sb2 = new StringBuilder(String.valueOf(message).length() + 42);
                        sb2.append("Failed to retrieve remote module version: ");
                        sb2.append(message);
                        Log.w("DynamiteModule", sb2.toString());
                        return 0;
                    }
                }
                zzp zzpVarZzg = zzg(context);
                try {
                    if (zzpVarZzg != null) {
                        try {
                            int iZzi = zzpVarZzg.zzi();
                            if (iZzi >= 3) {
                                zzn zznVar = (zzn) zzh.get();
                                if (zznVar == null || (cursor = zznVar.zza) == null) {
                                    Cursor cursor2 = (Cursor) ObjectWrapper.unwrap(zzpVarZzg.zzj(ObjectWrapper.wrap(context), str, z, ((Long) zzi.get()).longValue()));
                                    if (cursor2 != null) {
                                        try {
                                            if (cursor2.moveToFirst()) {
                                                int i = cursor2.getInt(0);
                                                cursor = (i <= 0 || !zzd(cursor2)) ? cursor2 : null;
                                                if (cursor != null) {
                                                    cursor.close();
                                                }
                                                iZzf = i;
                                            } else {
                                                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                                if (cursor2 != null) {
                                                    cursor2.close();
                                                }
                                            }
                                        } catch (RemoteException e3) {
                                            remoteException = e3;
                                            cursor = cursor2;
                                            String message2 = remoteException.getMessage();
                                            StringBuilder sb3 = new StringBuilder(String.valueOf(message2).length() + 42);
                                            sb3.append("Failed to retrieve remote module version: ");
                                            sb3.append(message2);
                                            Log.w("DynamiteModule", sb3.toString());
                                            if (cursor != null) {
                                                cursor.close();
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            cursor = cursor2;
                                            if (cursor == null) {
                                                throw th;
                                            }
                                            cursor.close();
                                            throw th;
                                        }
                                    } else {
                                        Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                                        if (cursor2 != null) {
                                        }
                                    }
                                } else {
                                    iZzf = cursor.getInt(0);
                                }
                            } else if (iZzi == 2) {
                                Log.w("DynamiteModule", "IDynamite loader version = 2, no high precision latency measurement.");
                                iZzf = zzpVarZzg.zzh(ObjectWrapper.wrap(context), str, z);
                            } else {
                                Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
                                iZzf = zzpVarZzg.zzf(ObjectWrapper.wrap(context), str, z);
                            }
                        } catch (RemoteException e4) {
                            remoteException = e4;
                        }
                    }
                    return iZzf;
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        } catch (Throwable th4) {
            CrashUtils.addDynamiteErrorToDropBox(context, th4);
            throw th4;
        }
    }

    private static boolean zzb(Context context) {
        if (Boolean.TRUE.equals(null) || Boolean.TRUE.equals(zzg)) {
            return true;
        }
        boolean zBooleanValue = false;
        if (zzg == null) {
            ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", true != PlatformVersion.isAtLeastQ() ? 0 : 268435456);
            if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, DefaultEncoderProfilesProvider.DEFAULT_VIDEO_BITRATE_FHD) == 0 && providerInfoResolveContentProvider != null && "com.google.android.gms".equals(providerInfoResolveContentProvider.packageName)) {
                zBooleanValue = true;
            }
            Boolean boolValueOf = Boolean.valueOf(zBooleanValue);
            zzg = boolValueOf;
            zBooleanValue = boolValueOf.booleanValue();
            if (zBooleanValue && providerInfoResolveContentProvider.applicationInfo != null && (providerInfoResolveContentProvider.applicationInfo.flags & 129) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                zze = true;
            }
        }
        if (!zBooleanValue) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return zBooleanValue;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x017d: MOVE (r2 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY]), block:B:101:0x017c */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x00e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:124:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int zzc(Context context, String str, boolean z, boolean z2) throws Throwable {
        Throwable th;
        Exception exc;
        Cursor cursor;
        Cursor cursorQuery;
        MatrixCursor matrixCursor;
        Cursor cursor2 = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        try {
            try {
                boolean z3 = true;
                Uri uriBuild = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").path(true != z ? "api" : "api_force_staging").appendPath(str).appendQueryParameter("requestStartUptime", String.valueOf(((Long) zzi.get()).longValue())).build();
                ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(uriBuild);
                boolean z4 = false;
                if (contentProviderClientAcquireUnstableContentProviderClient == null) {
                    matrixCursor = null;
                    if (matrixCursor != null) {
                        try {
                            if (matrixCursor.moveToFirst()) {
                                int i = matrixCursor.getInt(0);
                                if (i > 0) {
                                    synchronized (DynamiteModule.class) {
                                        zzd = matrixCursor.getString(2);
                                        int columnIndex = matrixCursor.getColumnIndex("loaderVersion");
                                        if (columnIndex >= 0) {
                                            zzf = matrixCursor.getInt(columnIndex);
                                        }
                                        int columnIndex2 = matrixCursor.getColumnIndex("disableStandaloneDynamiteLoader2");
                                        if (columnIndex2 >= 0) {
                                            if (matrixCursor.getInt(columnIndex2) == 0) {
                                                z3 = false;
                                            }
                                            zze = z3;
                                            z4 = z3;
                                        }
                                    }
                                    if (zzd(matrixCursor)) {
                                        matrixCursor = null;
                                    }
                                }
                                if (z2 && z4) {
                                    throw new LoadingException("forcing fallback to container DynamiteLoader impl", objArr2 == true ? 1 : 0);
                                }
                                if (matrixCursor != null) {
                                    matrixCursor.close();
                                }
                                return i;
                            }
                        } catch (Exception e) {
                            exc = e;
                        }
                    }
                    Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                    throw new LoadingException("Failed to connect to dynamite module ContentResolver.", objArr3 == true ? 1 : 0);
                }
                try {
                    cursorQuery = contentProviderClientAcquireUnstableContentProviderClient.query(uriBuild, null, null, null, null);
                } catch (RemoteException unused) {
                    contentProviderClientAcquireUnstableContentProviderClient.release();
                    matrixCursor = null;
                } catch (Throwable th2) {
                    contentProviderClientAcquireUnstableContentProviderClient.release();
                    throw th2;
                }
                if (cursorQuery != null) {
                    try {
                        int count = cursorQuery.getCount();
                        int columnCount = cursorQuery.getColumnCount();
                        matrixCursor = new MatrixCursor(cursorQuery.getColumnNames(), count);
                        for (int i2 = 0; i2 < count; i2++) {
                            if (!cursorQuery.moveToPosition(i2)) {
                                throw new RemoteException("Cursor read incomplete (ContentProvider dead?)");
                            }
                            Object[] objArr4 = new Object[columnCount];
                            for (int i3 = 0; i3 < columnCount; i3++) {
                                int type = cursorQuery.getType(i3);
                                if (type == 0) {
                                    objArr4[i3] = null;
                                } else if (type == 1) {
                                    objArr4[i3] = Long.valueOf(cursorQuery.getLong(i3));
                                } else if (type == 2) {
                                    objArr4[i3] = Double.valueOf(cursorQuery.getDouble(i3));
                                } else if (type == 3) {
                                    objArr4[i3] = cursorQuery.getString(i3);
                                } else {
                                    if (type != 4) {
                                        throw new RemoteException("Unknown column type");
                                    }
                                    objArr4[i3] = cursorQuery.getBlob(i3);
                                }
                            }
                            matrixCursor.addRow(objArr4);
                        }
                        cursorQuery.close();
                        contentProviderClientAcquireUnstableContentProviderClient.release();
                        if (matrixCursor != null) {
                        }
                        Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                        throw new LoadingException("Failed to connect to dynamite module ContentResolver.", objArr3 == true ? 1 : 0);
                    } finally {
                    }
                }
                contentProviderClientAcquireUnstableContentProviderClient.release();
                matrixCursor = null;
                if (matrixCursor != null) {
                }
                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                throw new LoadingException("Failed to connect to dynamite module ContentResolver.", objArr3 == true ? 1 : 0);
            } catch (Throwable th3) {
                th = th3;
                cursor2 = cursor;
                if (cursor2 != null) {
                    throw th;
                }
                cursor2.close();
                throw th;
            }
        } catch (Exception e2) {
            exc = e2;
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
            }
        }
        if (exc instanceof LoadingException) {
            throw exc;
        }
        String message = exc.getMessage();
        StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 25);
        sb.append("V2 version check failed: ");
        sb.append(message);
        throw new LoadingException(sb.toString(), exc, objArr == true ? 1 : 0);
    }

    private static boolean zzd(Cursor cursor) {
        zzn zznVar = (zzn) zzh.get();
        if (zznVar == null || zznVar.zza != null) {
            return false;
        }
        zznVar.zza = cursor;
        return true;
    }

    private static DynamiteModule zze(Context context, String str) {
        String.valueOf(str);
        Log.i("DynamiteModule", "Selected local version of ".concat(String.valueOf(str)));
        return new DynamiteModule(context);
    }

    private static void zzf(ClassLoader classLoader) throws LoadingException {
        zzq zzqVar;
        byte[] bArr = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                zzqVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zzqVar = iInterfaceQueryLocalInterface instanceof zzq ? (zzq) iInterfaceQueryLocalInterface : new zzq(iBinder);
            }
            zzm = zzqVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, bArr);
        }
    }

    private static zzp zzg(Context context) {
        zzp zzpVar;
        synchronized (DynamiteModule.class) {
            zzp zzpVar2 = zzl;
            if (zzpVar2 != null) {
                return zzpVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzpVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzpVar = iInterfaceQueryLocalInterface instanceof zzp ? (zzp) iInterfaceQueryLocalInterface : new zzp(iBinder);
                }
                if (zzpVar != null) {
                    zzl = zzpVar;
                    return zzpVar;
                }
            } catch (Exception e) {
                String message = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 45);
                sb.append("Failed to load IDynamiteLoader from GmsCore: ");
                sb.append(message);
                Log.e("DynamiteModule", sb.toString());
            }
            return null;
        }
    }

    public Context getModuleContext() {
        return this.zzk;
    }

    public IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzk.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String.valueOf(str);
            throw new LoadingException("Failed to instantiate module class: ".concat(String.valueOf(str)), e, null);
        }
    }
}
