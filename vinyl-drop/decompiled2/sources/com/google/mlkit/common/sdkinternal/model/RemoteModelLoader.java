package com.google.mlkit.common.sdkinternal.model;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzsk;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.mlkit:common@@18.11.0 */
/* JADX INFO: loaded from: classes3.dex */
public class RemoteModelLoader {
    private static final GmsLogger zza = new GmsLogger("RemoteModelLoader", "");
    private static final Map zzb = new HashMap();
    private final MlKitContext zzc;
    private final RemoteModel zzd;
    private final RemoteModelDownloadManager zze;
    private final RemoteModelFileManager zzf;
    private final RemoteModelLoaderHelper zzg;
    private final zzsh zzh;
    private boolean zzi;

    private RemoteModelLoader(MlKitContext mlKitContext, RemoteModel remoteModel, ModelValidator modelValidator, RemoteModelLoaderHelper remoteModelLoaderHelper, RemoteModelFileMover remoteModelFileMover) {
        RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(mlKitContext, remoteModel, modelValidator, new ModelFileHelper(mlKitContext), remoteModelFileMover);
        this.zzf = remoteModelFileManager;
        this.zzi = true;
        this.zze = RemoteModelDownloadManager.getInstance(mlKitContext, remoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
        this.zzg = remoteModelLoaderHelper;
        this.zzc = mlKitContext;
        this.zzd = remoteModel;
        this.zzh = zzss.zzb("common");
    }

    public static synchronized RemoteModelLoader getInstance(MlKitContext mlKitContext, RemoteModel remoteModel, ModelValidator modelValidator, RemoteModelLoaderHelper remoteModelLoaderHelper, RemoteModelFileMover remoteModelFileMover) {
        String uniqueModelNameForPersist;
        Map map;
        uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
        map = zzb;
        if (!map.containsKey(uniqueModelNameForPersist)) {
            map.put(uniqueModelNameForPersist, new RemoteModelLoader(mlKitContext, remoteModel, modelValidator, remoteModelLoaderHelper, remoteModelFileMover));
        }
        return (RemoteModelLoader) map.get(uniqueModelNameForPersist);
    }

    private final MappedByteBuffer zza(String str) throws MlKitException {
        return this.zzg.loadModelAtPath(str);
    }

    private final MappedByteBuffer zzb(File file) throws MlKitException {
        try {
            return zza(file.getAbsolutePath());
        } catch (Exception e) {
            this.zzf.zzc(file);
            throw new MlKitException("Failed to load newly downloaded model.", 14, e);
        }
    }

    public RemoteModel getRemoteModel() {
        return this.zzd;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00bd A[Catch: all -> 0x00fe, TryCatch #1 {, blocks: (B:4:0x0003, B:8:0x0020, B:10:0x0028, B:27:0x00bd, B:29:0x00cc, B:31:0x00d4, B:34:0x00da, B:35:0x00f8, B:36:0x00f9, B:11:0x002f, B:13:0x004e, B:16:0x0057, B:18:0x0075, B:20:0x007d, B:21:0x008f, B:23:0x0097, B:24:0x00ae), top: B:45:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f9 A[Catch: all -> 0x00fe, TRY_LEAVE, TryCatch #1 {, blocks: (B:4:0x0003, B:8:0x0020, B:10:0x0028, B:27:0x00bd, B:29:0x00cc, B:31:0x00d4, B:34:0x00da, B:35:0x00f8, B:36:0x00f9, B:11:0x002f, B:13:0x004e, B:16:0x0057, B:18:0x0075, B:20:0x007d, B:21:0x008f, B:23:0x0097, B:24:0x00ae), top: B:45:0x0003, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized MappedByteBuffer load() throws MlKitException {
        MappedByteBuffer mappedByteBufferZza;
        MappedByteBuffer mappedByteBufferZzb;
        GmsLogger gmsLogger = zza;
        gmsLogger.d("RemoteModelLoader", "Try to load newly downloaded model file.");
        RemoteModelDownloadManager remoteModelDownloadManager = this.zze;
        boolean z = this.zzi;
        Long downloadingId = remoteModelDownloadManager.getDownloadingId();
        String downloadingModelHash = remoteModelDownloadManager.getDownloadingModelHash();
        mappedByteBufferZza = null;
        if (downloadingId == null || downloadingModelHash == null) {
            gmsLogger.d("RemoteModelLoader", "No new model is downloading.");
            this.zze.removeOrCancelDownload();
        } else {
            Integer downloadingModelStatusCode = this.zze.getDownloadingModelStatusCode();
            if (downloadingModelStatusCode == null) {
                this.zze.removeOrCancelDownload();
            } else {
                new StringBuilder("Download Status code: ").append(downloadingModelStatusCode);
                gmsLogger.d("RemoteModelLoader", "Download Status code: ".concat(downloadingModelStatusCode.toString()));
                if (downloadingModelStatusCode.intValue() == 8) {
                    File fileZzi = this.zze.zzi(downloadingModelHash);
                    if (fileZzi != null) {
                        mappedByteBufferZzb = zzb(fileZzi);
                        gmsLogger.d("RemoteModelLoader", "Moved the downloaded model to private folder successfully: ".concat(String.valueOf(fileZzi.getParent())));
                        this.zze.updateLatestModelHashAndType(downloadingModelHash);
                        if (z && this.zzf.zzd(fileZzi)) {
                            gmsLogger.d("RemoteModelLoader", "All old models are deleted.");
                            mappedByteBufferZzb = zzb(this.zzf.zza(fileZzi));
                        }
                        if (mappedByteBufferZzb != null) {
                            gmsLogger.d("RemoteModelLoader", "Loading existing model file.");
                            String strZzb = this.zzf.zzb();
                            if (strZzb == null) {
                                gmsLogger.d("RemoteModelLoader", "No existing model file");
                            } else {
                                try {
                                    mappedByteBufferZza = zza(strZzb);
                                } catch (Exception e) {
                                    this.zzf.zzc(new File(strZzb));
                                    SharedPrefManager.getInstance(this.zzc).clearLatestModelHash(this.zzd);
                                    throw new MlKitException("Failed to load an already downloaded model.", 14, e);
                                }
                            }
                        } else {
                            this.zzi = false;
                            mappedByteBufferZza = mappedByteBufferZzb;
                        }
                    }
                } else if (downloadingModelStatusCode.intValue() == 16) {
                    this.zzh.zze(zzsk.zzg(), this.zzd, false, this.zze.getFailureReason(downloadingId));
                    this.zze.removeOrCancelDownload();
                }
            }
        }
        mappedByteBufferZzb = null;
        if (mappedByteBufferZzb != null) {
        }
        return mappedByteBufferZza;
    }
}
