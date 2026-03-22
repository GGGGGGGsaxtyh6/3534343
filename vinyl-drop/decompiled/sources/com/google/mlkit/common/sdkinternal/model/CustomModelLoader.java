package com.google.mlkit.common.sdkinternal.model;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzsh;
import com.google.android.gms.internal.mlkit_common.zzss;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.Constants;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.mlkit:common@@18.11.0 */
/* JADX INFO: loaded from: classes3.dex */
public class CustomModelLoader {
    private static final GmsLogger zza = new GmsLogger("CustomModelLoader", "");
    private static final Map zzb = new HashMap();
    private final MlKitContext zzc;
    private final LocalModel zzd;
    private final CustomRemoteModel zze;
    private final RemoteModelDownloadManager zzf;
    private final RemoteModelFileManager zzg;
    private final zzsh zzh;
    private boolean zzi;

    /* JADX INFO: compiled from: com.google.mlkit:common@@18.11.0 */
    public interface CustomModelLoaderHelper {
        void logLoad() throws MlKitException;

        boolean tryLoad(LocalModel localModel) throws MlKitException;
    }

    private CustomModelLoader(MlKitContext mlKitContext, LocalModel localModel, CustomRemoteModel customRemoteModel) {
        MlKitContext mlKitContext2;
        CustomRemoteModel customRemoteModel2;
        if (customRemoteModel != null) {
            mlKitContext2 = mlKitContext;
            customRemoteModel2 = customRemoteModel;
            RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(mlKitContext2, customRemoteModel2, null, new ModelFileHelper(mlKitContext), new com.google.mlkit.common.internal.model.zza(mlKitContext, customRemoteModel.getUniqueModelNameForPersist()));
            this.zzg = remoteModelFileManager;
            this.zzf = RemoteModelDownloadManager.getInstance(mlKitContext2, customRemoteModel2, new ModelFileHelper(mlKitContext2), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext2.get(ModelInfoRetrieverInterop.class));
            this.zzi = true;
        } else {
            mlKitContext2 = mlKitContext;
            customRemoteModel2 = customRemoteModel;
            this.zzg = null;
            this.zzf = null;
        }
        this.zzc = mlKitContext2;
        this.zzd = localModel;
        this.zze = customRemoteModel2;
        this.zzh = zzss.zzb("common");
    }

    public static synchronized CustomModelLoader getInstance(MlKitContext mlKitContext, LocalModel localModel, CustomRemoteModel customRemoteModel) {
        String string;
        Map map;
        string = customRemoteModel == null ? ((LocalModel) Preconditions.checkNotNull(localModel)).toString() : customRemoteModel.getUniqueModelNameForPersist();
        map = zzb;
        if (!map.containsKey(string)) {
            map.put(string, new CustomModelLoader(mlKitContext, localModel, customRemoteModel));
        }
        return (CustomModelLoader) map.get(string);
    }

    private final File zza() throws MlKitException {
        String strZzb = ((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzb();
        if (strZzb == null) {
            zza.d("CustomModelLoader", "No existing model file");
            return null;
        }
        File file = new File(strZzb);
        File[] fileArrListFiles = file.listFiles();
        return ((File[]) Preconditions.checkNotNull(fileArrListFiles)).length == 1 ? fileArrListFiles[0] : file;
    }

    private final void zzb() throws MlKitException {
        ((RemoteModelDownloadManager) Preconditions.checkNotNull(this.zzf)).removeOrCancelDownload();
    }

    private static final LocalModel zzc(File file) {
        if (file.isDirectory()) {
            LocalModel.Builder builder = new LocalModel.Builder();
            builder.setAbsoluteManifestFilePath(new File(file.getAbsolutePath(), Constants.AUTOML_IMAGE_LABELING_MANIFEST_JSON_FILE_NAME).toString());
            return builder.build();
        }
        LocalModel.Builder builder2 = new LocalModel.Builder();
        builder2.setAbsoluteFilePath(file.getAbsolutePath());
        return builder2.build();
    }

    public synchronized LocalModel createLocalModelByLatestExistingModel() throws MlKitException {
        zza.d("CustomModelLoader", "Try to get the latest existing model file.");
        File fileZza = zza();
        if (fileZza == null) {
            return null;
        }
        return zzc(fileZza);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00a3 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a5 A[Catch: all -> 0x00ab, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:8:0x0025, B:10:0x002d, B:25:0x00a5, B:11:0x0031, B:13:0x0050, B:16:0x0059, B:17:0x0072, B:19:0x007a, B:20:0x0096), top: B:31:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized com.google.mlkit.common.model.LocalModel createLocalModelByNewlyDownloadedModel() throws com.google.mlkit.common.MlKitException {
        /*
            r7 = this;
            java.lang.String r0 = "Download Status code: "
            monitor-enter(r7)
            com.google.android.gms.common.internal.GmsLogger r1 = com.google.mlkit.common.sdkinternal.model.CustomModelLoader.zza     // Catch: java.lang.Throwable -> Lab
            java.lang.String r2 = "CustomModelLoader"
            java.lang.String r3 = "Try to get newly downloaded model file."
            r1.d(r2, r3)     // Catch: java.lang.Throwable -> Lab
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r2 = r7.zzf     // Catch: java.lang.Throwable -> Lab
            java.lang.Object r2 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch: java.lang.Throwable -> Lab
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r2 = (com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager) r2     // Catch: java.lang.Throwable -> Lab
            java.lang.Long r2 = r2.getDownloadingId()     // Catch: java.lang.Throwable -> Lab
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r3 = r7.zzf     // Catch: java.lang.Throwable -> Lab
            java.lang.String r3 = r3.getDownloadingModelHash()     // Catch: java.lang.Throwable -> Lab
            r4 = 0
            if (r2 == 0) goto L96
            if (r3 != 0) goto L25
            goto L96
        L25:
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r5 = r7.zzf     // Catch: java.lang.Throwable -> Lab
            java.lang.Integer r5 = r5.getDownloadingModelStatusCode()     // Catch: java.lang.Throwable -> Lab
            if (r5 != 0) goto L31
            r7.zzb()     // Catch: java.lang.Throwable -> Lab
            goto La0
        L31:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lab
            r6.<init>(r0)     // Catch: java.lang.Throwable -> Lab
            java.lang.String r0 = "Download Status code: "
            r6.append(r5)     // Catch: java.lang.Throwable -> Lab
            java.lang.String r6 = r5.toString()     // Catch: java.lang.Throwable -> Lab
            java.lang.String r0 = r0.concat(r6)     // Catch: java.lang.Throwable -> Lab
            java.lang.String r6 = "CustomModelLoader"
            r1.d(r6, r0)     // Catch: java.lang.Throwable -> Lab
            int r0 = r5.intValue()     // Catch: java.lang.Throwable -> Lab
            r6 = 8
            if (r0 != r6) goto L72
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r0 = r7.zzf     // Catch: java.lang.Throwable -> Lab
            java.io.File r0 = r0.zzi(r3)     // Catch: java.lang.Throwable -> Lab
            if (r0 != 0) goto L59
            goto La0
        L59:
            java.lang.String r2 = r0.getParent()     // Catch: java.lang.Throwable -> Lab
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch: java.lang.Throwable -> Lab
            java.lang.String r5 = "Moved the downloaded model to private folder successfully: "
            java.lang.String r6 = "CustomModelLoader"
            java.lang.String r2 = r5.concat(r2)     // Catch: java.lang.Throwable -> Lab
            r1.d(r6, r2)     // Catch: java.lang.Throwable -> Lab
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r1 = r7.zzf     // Catch: java.lang.Throwable -> Lab
            r1.updateLatestModelHashAndType(r3)     // Catch: java.lang.Throwable -> Lab
            goto La1
        L72:
            int r0 = r5.intValue()     // Catch: java.lang.Throwable -> Lab
            r1 = 16
            if (r0 != r1) goto La0
            com.google.android.gms.internal.mlkit_common.zzsh r0 = r7.zzh     // Catch: java.lang.Throwable -> Lab
            com.google.mlkit.common.model.CustomRemoteModel r1 = r7.zze     // Catch: java.lang.Throwable -> Lab
            com.google.android.gms.internal.mlkit_common.zzry r3 = com.google.android.gms.internal.mlkit_common.zzsk.zzg()     // Catch: java.lang.Throwable -> Lab
            java.lang.Object r1 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r1)     // Catch: java.lang.Throwable -> Lab
            com.google.mlkit.common.model.RemoteModel r1 = (com.google.mlkit.common.model.RemoteModel) r1     // Catch: java.lang.Throwable -> Lab
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r5 = r7.zzf     // Catch: java.lang.Throwable -> Lab
            int r2 = r5.getFailureReason(r2)     // Catch: java.lang.Throwable -> Lab
            r5 = 0
            r0.zze(r3, r1, r5, r2)     // Catch: java.lang.Throwable -> Lab
            r7.zzb()     // Catch: java.lang.Throwable -> Lab
            goto La0
        L96:
            java.lang.String r0 = "CustomModelLoader"
            java.lang.String r2 = "No new model is downloading."
            r1.d(r0, r2)     // Catch: java.lang.Throwable -> Lab
            r7.zzb()     // Catch: java.lang.Throwable -> Lab
        La0:
            r0 = r4
        La1:
            if (r0 != 0) goto La5
            monitor-exit(r7)
            return r4
        La5:
            com.google.mlkit.common.model.LocalModel r0 = zzc(r0)     // Catch: java.lang.Throwable -> Lab
            monitor-exit(r7)
            return r0
        Lab:
            r0 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> Lab
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.CustomModelLoader.createLocalModelByNewlyDownloadedModel():com.google.mlkit.common.model.LocalModel");
    }

    public void deleteLatestExistingModel() throws MlKitException {
        File fileZza = zza();
        if (fileZza != null) {
            ((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzc(fileZza);
            SharedPrefManager.getInstance(this.zzc).clearLatestModelHash((RemoteModel) Preconditions.checkNotNull(this.zze));
        }
    }

    public void deleteOldModels(LocalModel localModel) throws MlKitException {
        File parentFile = new File((String) Preconditions.checkNotNull(localModel.getAbsoluteFilePath())).getParentFile();
        if (!((RemoteModelFileManager) Preconditions.checkNotNull(this.zzg)).zzd((File) Preconditions.checkNotNull(parentFile))) {
            zza.e("CustomModelLoader", "Failed to delete old models");
        } else {
            zza.d("CustomModelLoader", "All old models are deleted.");
            this.zzg.zza(parentFile);
        }
    }

    public synchronized void load(CustomModelLoaderHelper customModelLoaderHelper) throws MlKitException {
        LocalModel localModelCreateLocalModelByLatestExistingModel = this.zzd;
        if (localModelCreateLocalModelByLatestExistingModel == null) {
            localModelCreateLocalModelByLatestExistingModel = createLocalModelByNewlyDownloadedModel();
        }
        if (localModelCreateLocalModelByLatestExistingModel == null) {
            localModelCreateLocalModelByLatestExistingModel = createLocalModelByLatestExistingModel();
        }
        if (localModelCreateLocalModelByLatestExistingModel == null) {
            throw new MlKitException("Model is not available.", 14);
        }
        do {
            boolean zTryLoad = customModelLoaderHelper.tryLoad(localModelCreateLocalModelByLatestExistingModel);
            CustomRemoteModel customRemoteModel = this.zze;
            if (zTryLoad) {
                if (customRemoteModel != null && this.zzi) {
                    deleteOldModels((LocalModel) Preconditions.checkNotNull(localModelCreateLocalModelByLatestExistingModel));
                    this.zzi = false;
                }
                customModelLoaderHelper.logLoad();
                return;
            }
            if (customRemoteModel != null) {
                deleteLatestExistingModel();
                localModelCreateLocalModelByLatestExistingModel = createLocalModelByLatestExistingModel();
            } else {
                localModelCreateLocalModelByLatestExistingModel = null;
            }
        } while (localModelCreateLocalModelByLatestExistingModel != null);
        customModelLoaderHelper.logLoad();
    }
}
