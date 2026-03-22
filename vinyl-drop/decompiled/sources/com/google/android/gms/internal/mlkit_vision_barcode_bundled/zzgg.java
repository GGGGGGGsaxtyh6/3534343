package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: com.google.mlkit:barcode-scanning@@17.3.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgg {
    public static final /* synthetic */ int zza = 0;
    private static final zzgs zzb;

    static {
        int i = zzfu.zza;
        zzb = new zzgu();
    }

    public static void zzA(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzC(i, list, z);
    }

    public static void zzB(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzE(i, list, z);
    }

    public static void zzC(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzJ(i, list, z);
    }

    public static void zzD(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzL(i, list, z);
    }

    static boolean zzE(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int zza(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzei)) {
            int iZzB = 0;
            while (i < size) {
                iZzB += zzdn.zzB(((Integer) list.get(i)).intValue());
                i++;
            }
            return iZzB;
        }
        zzei zzeiVar = (zzei) list;
        int iZzB2 = 0;
        while (i < size) {
            iZzB2 += zzdn.zzB(zzeiVar.zze(i));
            i++;
        }
        return iZzB2;
    }

    static int zzb(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzdn.zzA(i << 3) + 4);
    }

    static int zzc(List list) {
        return list.size() * 4;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzdn.zzA(i << 3) + 8);
    }

    static int zze(List list) {
        return list.size() * 8;
    }

    static int zzf(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzei)) {
            int iZzB = 0;
            while (i < size) {
                iZzB += zzdn.zzB(((Integer) list.get(i)).intValue());
                i++;
            }
            return iZzB;
        }
        zzei zzeiVar = (zzei) list;
        int iZzB2 = 0;
        while (i < size) {
            iZzB2 += zzdn.zzB(zzeiVar.zze(i));
            i++;
        }
        return iZzB2;
    }

    static int zzg(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzfb)) {
            int iZzB = 0;
            while (i < size) {
                iZzB += zzdn.zzB(((Long) list.get(i)).longValue());
                i++;
            }
            return iZzB;
        }
        zzfb zzfbVar = (zzfb) list;
        int iZzB2 = 0;
        while (i < size) {
            iZzB2 += zzdn.zzB(zzfbVar.zze(i));
            i++;
        }
        return iZzB2;
    }

    static int zzh(int i, Object obj, zzge zzgeVar) {
        int i2 = i << 3;
        if (!(obj instanceof zzex)) {
            return zzdn.zzA(i2) + zzdn.zzy((zzfm) obj, zzgeVar);
        }
        int iZzA = zzdn.zzA(i2);
        int iZza = ((zzex) obj).zza();
        return iZzA + zzdn.zzA(iZza) + iZza;
    }

    static int zzi(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzei)) {
            int iZzA = 0;
            while (i < size) {
                int iIntValue = ((Integer) list.get(i)).intValue();
                iZzA += zzdn.zzA((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i++;
            }
            return iZzA;
        }
        zzei zzeiVar = (zzei) list;
        int iZzA2 = 0;
        while (i < size) {
            int iZze = zzeiVar.zze(i);
            iZzA2 += zzdn.zzA((iZze >> 31) ^ (iZze + iZze));
            i++;
        }
        return iZzA2;
    }

    static int zzj(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzfb)) {
            int iZzB = 0;
            while (i < size) {
                long jLongValue = ((Long) list.get(i)).longValue();
                iZzB += zzdn.zzB((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i++;
            }
            return iZzB;
        }
        zzfb zzfbVar = (zzfb) list;
        int iZzB2 = 0;
        while (i < size) {
            long jZze = zzfbVar.zze(i);
            iZzB2 += zzdn.zzB((jZze >> 63) ^ (jZze + jZze));
            i++;
        }
        return iZzB2;
    }

    static int zzk(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzei)) {
            int iZzA = 0;
            while (i < size) {
                iZzA += zzdn.zzA(((Integer) list.get(i)).intValue());
                i++;
            }
            return iZzA;
        }
        zzei zzeiVar = (zzei) list;
        int iZzA2 = 0;
        while (i < size) {
            iZzA2 += zzdn.zzA(zzeiVar.zze(i));
            i++;
        }
        return iZzA2;
    }

    static int zzl(List list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzfb)) {
            int iZzB = 0;
            while (i < size) {
                iZzB += zzdn.zzB(((Long) list.get(i)).longValue());
                i++;
            }
            return iZzB;
        }
        zzfb zzfbVar = (zzfb) list;
        int iZzB2 = 0;
        while (i < size) {
            iZzB2 += zzdn.zzB(zzfbVar.zze(i));
            i++;
        }
        return iZzB2;
    }

    public static zzgs zzm() {
        return zzb;
    }

    static Object zzn(Object obj, int i, int i2, Object obj2, zzgs zzgsVar) {
        Object obj3 = obj2;
        if (obj2 == null) {
            zzeh zzehVar = (zzeh) obj;
            zzgt zzgtVar = zzehVar.zzc;
            obj3 = zzgtVar;
            if (zzgtVar == zzgt.zzc()) {
                zzgt zzgtVarZzf = zzgt.zzf();
                zzehVar.zzc = zzgtVarZzf;
                obj3 = zzgtVarZzf;
            }
        }
        ((zzgt) obj3).zzj(i << 3, Long.valueOf(i2));
        return obj3;
    }

    static void zzo(zzdt zzdtVar, Object obj, Object obj2) {
        zzdx zzdxVar = ((zzed) obj2).zzb;
        if (zzdxVar.zza.isEmpty()) {
            return;
        }
        ((zzed) obj).zzc().zzh(zzdxVar);
    }

    static void zzp(zzgs zzgsVar, Object obj, Object obj2) {
        zzeh zzehVar = (zzeh) obj;
        zzgt zzgtVarZze = zzehVar.zzc;
        zzgt zzgtVar = ((zzeh) obj2).zzc;
        if (!zzgt.zzc().equals(zzgtVar)) {
            if (zzgt.zzc().equals(zzgtVarZze)) {
                zzgtVarZze = zzgt.zze(zzgtVarZze, zzgtVar);
            } else {
                zzgtVarZze.zzd(zzgtVar);
            }
        }
        zzehVar.zzc = zzgtVarZze;
    }

    public static void zzq(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzc(i, list, z);
    }

    public static void zzr(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzg(i, list, z);
    }

    public static void zzs(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzj(i, list, z);
    }

    public static void zzt(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzl(i, list, z);
    }

    public static void zzu(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzn(i, list, z);
    }

    public static void zzv(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzp(i, list, z);
    }

    public static void zzw(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzs(i, list, z);
    }

    public static void zzx(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzu(i, list, z);
    }

    public static void zzy(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzy(i, list, z);
    }

    public static void zzz(int i, List list, zzhh zzhhVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzhhVar.zzA(i, list, z);
    }
}
