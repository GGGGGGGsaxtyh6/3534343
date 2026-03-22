package com.google.re2j;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.camera.core.processing.util.GLUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.PointerIconCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.google.android.material.internal.ViewUtils;
import com.google.mlkit.common.MlKitException;
import com.google.zxing.pdf417.PDF417Common;
import io.grpc.internal.GrpcUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.Utf8;

/* JADX INFO: loaded from: classes3.dex */
final class UnicodeTables {
    static final int[][] Arabic;
    static final int[][] Armenian;
    static final int[][] Avestan;
    static final int[][] Balinese;
    static final int[][] Bamum;
    static final int[][] Batak;
    static final int[][] Bengali;
    static final int[][] Bopomofo;
    static final int[][] Brahmi;
    static final int[][] Braille;
    static final int[][] Buginese;
    static final int[][] Buhid;
    static final int[][] C;
    static final char[] CASE_ORBIT;
    static final Map<String, int[][]> CATEGORIES;
    static final int[][] Canadian_Aboriginal;
    static final int[][] Carian;
    static final int[][] Cc;
    static final int[][] Cf;
    static final int[][] Cham;
    static final int[][] Cherokee;
    static final int[][] Co;
    static final int[][] Common;
    static final int[][] Coptic;
    static final int[][] Cs;
    static final int[][] Cuneiform;
    static final int[][] Cypriot;
    static final int[][] Cyrillic;
    static final int[][] Deseret;
    static final int[][] Devanagari;
    static final int[][] Egyptian_Hieroglyphs;
    static final int[][] Ethiopic;
    static final Map<String, int[][]> FOLD_CATEGORIES;
    static final Map<String, int[][]> FOLD_SCRIPT;
    static final int[][] Georgian;
    static final int[][] Glagolitic;
    static final int[][] Gothic;
    static final int[][] Greek;
    static final int[][] Gujarati;
    static final int[][] Gurmukhi;
    static final int[][] Han;
    static final int[][] Hangul;
    static final int[][] Hanunoo;
    static final int[][] Hebrew;
    static final int[][] Hiragana;
    static final int[][] Imperial_Aramaic;
    static final int[][] Inherited;
    static final int[][] Inscriptional_Pahlavi;
    static final int[][] Inscriptional_Parthian;
    static final int[][] Javanese;
    static final int[][] Kaithi;
    static final int[][] Kannada;
    static final int[][] Katakana;
    static final int[][] Kayah_Li;
    static final int[][] Kharoshthi;
    static final int[][] Khmer;
    static final int[][] L;
    static final int[][] Lao;
    static final int[][] Latin;
    static final int[][] Lepcha;
    static final int[][] Limbu;
    static final int[][] Linear_B;
    static final int[][] Lisu;
    static final int[][] Ll;
    static final int[][] Lm;
    static final int[][] Lo;
    static final int[][] Lt;
    static final int[][] Lu;
    static final int[][] Lycian;
    static final int[][] Lydian;
    static final int[][] M;
    static final int[][] Malayalam;
    static final int[][] Mandaic;
    static final int[][] Mc;
    static final int[][] Me;
    static final int[][] Meetei_Mayek;
    static final int[][] Mn;
    static final int[][] Mongolian;
    static final int[][] Myanmar;
    static final int[][] N;
    static final int[][] Nd;
    static final int[][] New_Tai_Lue;
    static final int[][] Nko;
    static final int[][] Nl;
    static final int[][] No;
    static final int[][] Ogham;
    static final int[][] Ol_Chiki;
    static final int[][] Old_Italic;
    static final int[][] Old_Persian;
    static final int[][] Old_South_Arabian;
    static final int[][] Old_Turkic;
    static final int[][] Oriya;
    static final int[][] Osmanya;
    static final int[][] P;
    static final int[][] Pc;
    static final int[][] Pd;
    static final int[][] Pe;
    static final int[][] Pf;
    static final int[][] Phags_Pa;
    static final int[][] Phoenician;
    static final int[][] Pi;
    static final int[][] Po;
    static final int[][] Ps;
    static final int[][] Rejang;
    static final int[][] Runic;
    static final int[][] S;
    static final Map<String, int[][]> SCRIPTS;
    static final int[][] Samaritan;
    static final int[][] Saurashtra;
    static final int[][] Sc;
    static final int[][] Shavian;
    static final int[][] Sinhala;
    static final int[][] Sk;
    static final int[][] Sm;
    static final int[][] So;
    static final int[][] Sundanese;
    static final int[][] Syloti_Nagri;
    static final int[][] Syriac;
    static final int[][] Tagalog;
    static final int[][] Tagbanwa;
    static final int[][] Tai_Le;
    static final int[][] Tai_Tham;
    static final int[][] Tai_Viet;
    static final int[][] Tamil;
    static final int[][] Telugu;
    static final int[][] Thaana;
    static final int[][] Thai;
    static final int[][] Tibetan;
    static final int[][] Tifinagh;
    static final int[][] Ugaritic;
    static final int[][] Unknown;
    static final int[][] Upper;
    static final int[][] Vai;
    static final int[][] Yi;
    static final int[][] Z;
    static final int[][] Zl;
    static final int[][] Zp;
    static final int[][] Zs;
    private static final int[][] foldCommon;
    private static final int[][] foldGreek;
    private static final int[][] foldInherited;
    private static final int[][] foldLl;
    private static final int[][] foldLt;
    private static final int[][] foldLu;
    private static final int[][] foldMn;

    static {
        int[][] iArrMake_Lu = make_Lu();
        Lu = iArrMake_Lu;
        Ll = make_Ll();
        Lt = make_Lt();
        Lm = make_Lm();
        Lo = make_Lo();
        Mn = make_Mn();
        Me = make_Me();
        Mc = make_Mc();
        Nd = make_Nd();
        Nl = make_Nl();
        No = make_No();
        Zs = make_Zs();
        Zl = make_Zl();
        Zp = make_Zp();
        Cc = make_Cc();
        Cf = make_Cf();
        Co = make_Co();
        Cs = make_Cs();
        Pd = make_Pd();
        Ps = make_Ps();
        Pe = make_Pe();
        Pc = make_Pc();
        Po = make_Po();
        Sm = make_Sm();
        Sc = make_Sc();
        Sk = make_Sk();
        So = make_So();
        Pi = make_Pi();
        Pf = make_Pf();
        P = make_P();
        S = make_S();
        C = make_C();
        Z = make_Z();
        L = make_L();
        M = make_M();
        N = make_N();
        Common = make_Common();
        Inherited = make_Inherited();
        Bamum = make_Bamum();
        Arabic = make_Arabic();
        Lisu = make_Lisu();
        Armenian = make_Armenian();
        Bengali = make_Bengali();
        Old_South_Arabian = make_Old_South_Arabian();
        Bopomofo = make_Bopomofo();
        Cherokee = make_Cherokee();
        Coptic = make_Coptic();
        Cyrillic = make_Cyrillic();
        Deseret = make_Deseret();
        Devanagari = make_Devanagari();
        Ethiopic = make_Ethiopic();
        Georgian = make_Georgian();
        Gothic = make_Gothic();
        Greek = make_Greek();
        Gujarati = make_Gujarati();
        Gurmukhi = make_Gurmukhi();
        Han = make_Han();
        Hangul = make_Hangul();
        Hebrew = make_Hebrew();
        Hiragana = make_Hiragana();
        Kannada = make_Kannada();
        Katakana = make_Katakana();
        Khmer = make_Khmer();
        Lao = make_Lao();
        Latin = make_Latin();
        Malayalam = make_Malayalam();
        Mongolian = make_Mongolian();
        Myanmar = make_Myanmar();
        Ogham = make_Ogham();
        Old_Italic = make_Old_Italic();
        Oriya = make_Oriya();
        Runic = make_Runic();
        Sinhala = make_Sinhala();
        Syriac = make_Syriac();
        Tamil = make_Tamil();
        Telugu = make_Telugu();
        Thaana = make_Thaana();
        Thai = make_Thai();
        Tibetan = make_Tibetan();
        Canadian_Aboriginal = make_Canadian_Aboriginal();
        Yi = make_Yi();
        Tagalog = make_Tagalog();
        Hanunoo = make_Hanunoo();
        Buhid = make_Buhid();
        Tagbanwa = make_Tagbanwa();
        Braille = make_Braille();
        Cypriot = make_Cypriot();
        Limbu = make_Limbu();
        Linear_B = make_Linear_B();
        Osmanya = make_Osmanya();
        Shavian = make_Shavian();
        Tai_Le = make_Tai_Le();
        Ugaritic = make_Ugaritic();
        Buginese = make_Buginese();
        Glagolitic = make_Glagolitic();
        Kharoshthi = make_Kharoshthi();
        Syloti_Nagri = make_Syloti_Nagri();
        New_Tai_Lue = make_New_Tai_Lue();
        Tifinagh = make_Tifinagh();
        Old_Persian = make_Old_Persian();
        Balinese = make_Balinese();
        Batak = make_Batak();
        Brahmi = make_Brahmi();
        Cham = make_Cham();
        Egyptian_Hieroglyphs = make_Egyptian_Hieroglyphs();
        Javanese = make_Javanese();
        Kayah_Li = make_Kayah_Li();
        Lepcha = make_Lepcha();
        Mandaic = make_Mandaic();
        Nko = make_Nko();
        Old_Turkic = make_Old_Turkic();
        Phags_Pa = make_Phags_Pa();
        Phoenician = make_Phoenician();
        Vai = make_Vai();
        Cuneiform = make_Cuneiform();
        Unknown = make_Unknown();
        Carian = make_Carian();
        Tai_Tham = make_Tai_Tham();
        Lycian = make_Lycian();
        Lydian = make_Lydian();
        Ol_Chiki = make_Ol_Chiki();
        Rejang = make_Rejang();
        Saurashtra = make_Saurashtra();
        Sundanese = make_Sundanese();
        Meetei_Mayek = make_Meetei_Mayek();
        Imperial_Aramaic = make_Imperial_Aramaic();
        Avestan = make_Avestan();
        Kaithi = make_Kaithi();
        Inscriptional_Pahlavi = make_Inscriptional_Pahlavi();
        Inscriptional_Parthian = make_Inscriptional_Parthian();
        Samaritan = make_Samaritan();
        Tai_Viet = make_Tai_Viet();
        Upper = iArrMake_Lu;
        CATEGORIES = Categories();
        SCRIPTS = Scripts();
        foldCommon = make_foldCommon();
        foldInherited = make_foldInherited();
        foldGreek = make_foldGreek();
        FOLD_SCRIPT = FoldScript();
        foldLu = make_foldLu();
        foldLl = make_foldLl();
        foldLt = make_foldLt();
        foldMn = make_foldMn();
        FOLD_CATEGORIES = FoldCategory();
        char[] cArr = new char[8492];
        CASE_ORBIT = cArr;
        cArr[75] = 'k';
        cArr[83] = 's';
        cArr[107] = 8490;
        cArr[115] = 383;
        cArr[181] = 924;
        cArr[197] = 229;
        cArr[223] = 7838;
        cArr[229] = 8491;
        cArr[304] = 304;
        cArr[305] = 305;
        cArr[383] = 'S';
        cArr[452] = 453;
        cArr[453] = 454;
        cArr[454] = 452;
        cArr[455] = 456;
        cArr[456] = 457;
        cArr[457] = 455;
        cArr[458] = 459;
        cArr[459] = 460;
        cArr[460] = 458;
        cArr[497] = 498;
        cArr[498] = 499;
        cArr[499] = 497;
        cArr[837] = 921;
        cArr[914] = 946;
        cArr[917] = 949;
        cArr[920] = 952;
        cArr[921] = 953;
        cArr[922] = 954;
        cArr[924] = 956;
        cArr[928] = 960;
        cArr[929] = 961;
        cArr[931] = 962;
        cArr[934] = 966;
        cArr[937] = 969;
        cArr[946] = 976;
        cArr[949] = 1013;
        cArr[952] = 977;
        cArr[953] = 8126;
        cArr[954] = 1008;
        cArr[956] = 181;
        cArr[960] = 982;
        cArr[961] = 1009;
        cArr[962] = 963;
        cArr[963] = 931;
        cArr[966] = 981;
        cArr[969] = 8486;
        cArr[976] = 914;
        cArr[977] = 1012;
        cArr[981] = 934;
        cArr[982] = 928;
        cArr[1008] = 922;
        cArr[1009] = 929;
        cArr[1012] = 920;
        cArr[1013] = 917;
        cArr[7776] = 7777;
        cArr[7777] = 7835;
        cArr[7835] = 7776;
        cArr[7838] = 223;
        cArr[8126] = 837;
        cArr[8486] = 937;
        cArr[8490] = 'K';
        cArr[8491] = 197;
    }

    private UnicodeTables() {
    }

    private static int[][] make_Lu() {
        return new int[][]{new int[]{65, 90, 1}, new int[]{192, 214, 1}, new int[]{216, 222, 1}, new int[]{256, 310, 2}, new int[]{313, 327, 2}, new int[]{330, 376, 2}, new int[]{377, 381, 2}, new int[]{385, 386, 1}, new int[]{388, 390, 2}, new int[]{391, 393, 2}, new int[]{394, 395, 1}, new int[]{398, TypedValues.CycleType.TYPE_CURVE_FIT, 1}, new int[]{TypedValues.CycleType.TYPE_ALPHA, 404, 1}, new int[]{406, 408, 1}, new int[]{412, 413, 1}, new int[]{415, TypedValues.CycleType.TYPE_PATH_ROTATE, 1}, new int[]{418, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 2}, new int[]{TypedValues.CycleType.TYPE_WAVE_PERIOD, TypedValues.CycleType.TYPE_WAVE_PHASE, 2}, new int[]{428, 430, 2}, new int[]{431, 433, 2}, new int[]{434, 435, 1}, new int[]{437, 439, 2}, new int[]{440, 444, 4}, new int[]{452, 461, 3}, new int[]{463, 475, 2}, new int[]{478, 494, 2}, new int[]{497, 500, 3}, new int[]{TypedValues.PositionType.TYPE_DRAWPATH, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, 1}, new int[]{TypedValues.PositionType.TYPE_PERCENT_X, 562, 2}, new int[]{570, 571, 1}, new int[]{573, 574, 1}, new int[]{577, 579, 2}, new int[]{580, 582, 1}, new int[]{584, 590, 2}, new int[]{880, 882, 2}, new int[]{886, TypedValues.Custom.TYPE_COLOR, 16}, new int[]{TypedValues.Custom.TYPE_BOOLEAN, TypedValues.Custom.TYPE_REFERENCE, 1}, new int[]{908, 910, 2}, new int[]{911, 913, 2}, new int[]{914, PDF417Common.NUMBER_OF_CODEWORDS, 1}, new int[]{931, 939, 1}, new int[]{975, 978, 3}, new int[]{979, 980, 1}, new int[]{984, PointerIconCompat.TYPE_CELL, 2}, new int[]{PointerIconCompat.TYPE_NO_DROP, PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, 3}, new int[]{PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW, PointerIconCompat.TYPE_ZOOM_IN, 1}, new int[]{PointerIconCompat.TYPE_GRABBING, 1071, 1}, new int[]{1120, 1152, 2}, new int[]{1162, 1216, 2}, new int[]{1217, 1229, 2}, new int[]{1232, 1318, 2}, new int[]{1329, 1366, 1}, new int[]{4256, 4293, 1}, new int[]{7680, 7828, 2}, new int[]{7838, 7934, 2}, new int[]{7944, 7951, 1}, new int[]{7960, 7965, 1}, new int[]{7976, 7983, 1}, new int[]{7992, 7999, 1}, new int[]{8008, 8013, 1}, new int[]{8025, 8031, 2}, new int[]{8040, 8047, 1}, new int[]{8120, 8123, 1}, new int[]{8136, 8139, 1}, new int[]{8152, 8155, 1}, new int[]{8168, 8172, 1}, new int[]{8184, 8187, 1}, new int[]{8450, 8455, 5}, new int[]{8459, 8461, 1}, new int[]{8464, 8466, 1}, new int[]{8469, 8473, 4}, new int[]{8474, 8477, 1}, new int[]{8484, 8490, 2}, new int[]{8491, 8493, 1}, new int[]{8496, 8499, 1}, new int[]{8510, 8511, 1}, new int[]{8517, 8579, 62}, new int[]{11264, 11310, 1}, new int[]{11360, 11362, 2}, new int[]{11363, 11364, 1}, new int[]{11367, 11373, 2}, new int[]{11374, 11376, 1}, new int[]{11378, 11381, 3}, new int[]{11390, 11392, 1}, new int[]{11394, 11490, 2}, new int[]{11499, 11501, 2}, new int[]{42560, 42604, 2}, new int[]{42624, 42646, 2}, new int[]{42786, 42798, 2}, new int[]{42802, 42862, 2}, new int[]{42873, 42877, 2}, new int[]{42878, 42886, 2}, new int[]{42891, 42893, 2}, new int[]{42896, 42912, 16}, new int[]{42914, 42920, 2}, new int[]{65313, 65338, 1}, new int[]{66560, 66599, 1}, new int[]{119808, 119833, 1}, new int[]{119860, 119885, 1}, new int[]{119912, 119937, 1}, new int[]{119964, 119966, 2}, new int[]{119967, 119973, 3}, new int[]{119974, 119977, 3}, new int[]{119978, 119980, 1}, new int[]{119982, 119989, 1}, new int[]{120016, 120041, 1}, new int[]{120068, 120069, 1}, new int[]{120071, 120074, 1}, new int[]{120077, 120084, 1}, new int[]{120086, 120092, 1}, new int[]{120120, 120121, 1}, new int[]{120123, 120126, 1}, new int[]{120128, 120132, 1}, new int[]{120134, 120138, 4}, new int[]{120139, 120144, 1}, new int[]{120172, 120197, 1}, new int[]{120224, 120249, 1}, new int[]{120276, 120301, 1}, new int[]{120328, 120353, 1}, new int[]{120380, 120405, 1}, new int[]{120432, 120457, 1}, new int[]{120488, 120512, 1}, new int[]{120546, 120570, 1}, new int[]{120604, 120628, 1}, new int[]{120662, 120686, 1}, new int[]{120720, 120744, 1}, new int[]{120778, 120778, 1}};
    }

    private static int[][] make_Ll() {
        return new int[][]{new int[]{97, 122, 1}, new int[]{170, 181, 11}, new int[]{186, 223, 37}, new int[]{224, 246, 1}, new int[]{248, 255, 1}, new int[]{257, 311, 2}, new int[]{312, 328, 2}, new int[]{329, 375, 2}, new int[]{378, 382, 2}, new int[]{383, 384, 1}, new int[]{387, 389, 2}, new int[]{392, 396, 4}, new int[]{397, TypedValues.CycleType.TYPE_VISIBILITY, 5}, new int[]{405, 409, 4}, new int[]{410, 411, 1}, new int[]{414, 417, 3}, new int[]{419, 421, 2}, new int[]{TypedValues.CycleType.TYPE_WAVE_OFFSET, 426, 2}, new int[]{427, 429, 2}, new int[]{432, 436, 4}, new int[]{438, 441, 3}, new int[]{442, 445, 3}, new int[]{446, 447, 1}, new int[]{454, 460, 3}, new int[]{462, 476, 2}, new int[]{477, 495, 2}, new int[]{496, 499, 3}, new int[]{TypedValues.PositionType.TYPE_TRANSITION_EASING, TypedValues.PositionType.TYPE_SIZE_PERCENT, 4}, new int[]{TypedValues.PositionType.TYPE_PERCENT_Y, 563, 2}, new int[]{564, 569, 1}, new int[]{572, 575, 3}, new int[]{576, 578, 2}, new int[]{583, 591, 2}, new int[]{592, 659, 1}, new int[]{661, 687, 1}, new int[]{881, 883, 2}, new int[]{887, 891, 4}, new int[]{892, 893, 1}, new int[]{912, 940, 28}, new int[]{941, 974, 1}, new int[]{976, 977, 1}, new int[]{981, 983, 1}, new int[]{985, PointerIconCompat.TYPE_CROSSHAIR, 2}, new int[]{PointerIconCompat.TYPE_TEXT, PointerIconCompat.TYPE_COPY, 1}, new int[]{PointerIconCompat.TYPE_ALL_SCROLL, PointerIconCompat.TYPE_ZOOM_OUT, 3}, new int[]{PointerIconCompat.TYPE_GRAB, 1072, 52}, new int[]{1073, 1119, 1}, new int[]{1121, 1153, 2}, new int[]{1163, 1215, 2}, new int[]{1218, 1230, 2}, new int[]{1231, 1319, 2}, new int[]{1377, 1415, 1}, new int[]{7424, 7467, 1}, new int[]{7522, 7543, 1}, new int[]{7545, 7578, 1}, new int[]{7681, 7829, 2}, new int[]{7830, 7837, 1}, new int[]{7839, 7935, 2}, new int[]{7936, 7943, 1}, new int[]{7952, 7957, 1}, new int[]{7968, 7975, 1}, new int[]{7984, 7991, 1}, new int[]{8000, 8005, 1}, new int[]{8016, 8023, 1}, new int[]{8032, 8039, 1}, new int[]{8048, 8061, 1}, new int[]{8064, 8071, 1}, new int[]{8080, 8087, 1}, new int[]{8096, 8103, 1}, new int[]{8112, 8116, 1}, new int[]{8118, 8119, 1}, new int[]{8126, 8130, 4}, new int[]{8131, 8132, 1}, new int[]{8134, 8135, 1}, new int[]{8144, 8147, 1}, new int[]{8150, 8151, 1}, new int[]{8160, 8167, 1}, new int[]{8178, 8180, 1}, new int[]{8182, 8183, 1}, new int[]{8458, 8462, 4}, new int[]{8463, 8467, 4}, new int[]{8495, 8505, 5}, new int[]{8508, 8509, 1}, new int[]{8518, 8521, 1}, new int[]{8526, 8580, 54}, new int[]{11312, 11358, 1}, new int[]{11361, 11365, 4}, new int[]{11366, 11372, 2}, new int[]{11377, 11379, 2}, new int[]{11380, 11382, 2}, new int[]{11383, 11388, 1}, new int[]{11393, 11491, 2}, new int[]{11492, 11500, 8}, new int[]{11502, 11520, 18}, new int[]{11521, 11557, 1}, new int[]{42561, 42605, 2}, new int[]{42625, 42647, 2}, new int[]{42787, 42799, 2}, new int[]{42800, 42801, 1}, new int[]{42803, 42865, 2}, new int[]{42866, 42872, 1}, new int[]{42874, 42876, 2}, new int[]{42879, 42887, 2}, new int[]{42892, 42894, 2}, new int[]{42897, 42913, 16}, new int[]{42915, 42921, 2}, new int[]{43002, 64256, 21254}, new int[]{64257, 64262, 1}, new int[]{64275, 64279, 1}, new int[]{65345, 65370, 1}, new int[]{66600, 66639, 1}, new int[]{119834, 119859, 1}, new int[]{119886, 119892, 1}, new int[]{119894, 119911, 1}, new int[]{119938, 119963, 1}, new int[]{119990, 119993, 1}, new int[]{119995, 119997, 2}, new int[]{119998, 120003, 1}, new int[]{120005, 120015, 1}, new int[]{120042, 120067, 1}, new int[]{120094, 120119, 1}, new int[]{120146, 120171, 1}, new int[]{120198, 120223, 1}, new int[]{120250, 120275, 1}, new int[]{120302, 120327, 1}, new int[]{120354, 120379, 1}, new int[]{120406, 120431, 1}, new int[]{120458, 120485, 1}, new int[]{120514, 120538, 1}, new int[]{120540, 120545, 1}, new int[]{120572, 120596, 1}, new int[]{120598, 120603, 1}, new int[]{120630, 120654, 1}, new int[]{120656, 120661, 1}, new int[]{120688, 120712, 1}, new int[]{120714, 120719, 1}, new int[]{120746, 120770, 1}, new int[]{120772, 120777, 1}, new int[]{120779, 120779, 1}};
    }

    private static int[][] make_Lt() {
        return new int[][]{new int[]{453, 459, 3}, new int[]{498, 8072, 7574}, new int[]{8073, 8079, 1}, new int[]{8088, 8095, 1}, new int[]{8104, 8111, 1}, new int[]{8124, 8140, 16}, new int[]{8188, 8188, 1}};
    }

    private static int[][] make_Lm() {
        return new int[][]{new int[]{688, TypedValues.TransitionType.TYPE_INTERPOLATOR, 1}, new int[]{710, 721, 1}, new int[]{736, 740, 1}, new int[]{748, 750, 2}, new int[]{884, 890, 6}, new int[]{1369, 1600, 231}, new int[]{1765, 1766, 1}, new int[]{2036, 2037, 1}, new int[]{2042, 2074, 32}, new int[]{2084, 2088, 4}, new int[]{2417, 3654, 1237}, new int[]{3782, 4348, 566}, new int[]{6103, 6211, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR}, new int[]{6823, 7288, 465}, new int[]{7289, 7293, 1}, new int[]{7468, 7521, 1}, new int[]{7544, 7579, 35}, new int[]{7580, 7615, 1}, new int[]{8305, 8319, 14}, new int[]{8336, 8348, 1}, new int[]{11389, 11631, 242}, new int[]{11823, 12293, 470}, new int[]{12337, 12341, 1}, new int[]{12347, GLUtils.EGL_GL_COLORSPACE_KHR, 98}, new int[]{12446, 12540, 94}, new int[]{12541, 12542, 1}, new int[]{40981, 42232, 1251}, new int[]{42233, 42237, 1}, new int[]{42508, 42623, 115}, new int[]{42775, 42783, 1}, new int[]{42864, 42888, 24}, new int[]{43471, 43632, 161}, new int[]{43741, 65392, 21651}, new int[]{65438, 65439, 1}};
    }

    private static int[][] make_Lo() {
        return new int[][]{new int[]{GrpcUtil.DEFAULT_PORT_SSL, 448, 5}, new int[]{449, 451, 1}, new int[]{660, 1488, 828}, new int[]{1489, 1514, 1}, new int[]{1520, 1522, 1}, new int[]{1568, 1599, 1}, new int[]{1601, 1610, 1}, new int[]{1646, 1647, 1}, new int[]{1649, 1747, 1}, new int[]{1749, 1774, 25}, new int[]{1775, 1786, 11}, new int[]{1787, 1788, 1}, new int[]{1791, 1808, 17}, new int[]{1810, 1839, 1}, new int[]{1869, 1957, 1}, new int[]{1969, 1994, 25}, new int[]{1995, 2026, 1}, new int[]{2048, 2069, 1}, new int[]{2112, 2136, 1}, new int[]{2308, 2361, 1}, new int[]{2365, 2384, 19}, new int[]{2392, 2401, 1}, new int[]{2418, 2423, 1}, new int[]{2425, 2431, 1}, new int[]{2437, 2444, 1}, new int[]{2447, 2448, 1}, new int[]{2451, 2472, 1}, new int[]{2474, 2480, 1}, new int[]{2482, 2486, 4}, new int[]{2487, 2489, 1}, new int[]{2493, 2510, 17}, new int[]{2524, 2525, 1}, new int[]{2527, 2529, 1}, new int[]{2544, 2545, 1}, new int[]{2565, 2570, 1}, new int[]{2575, 2576, 1}, new int[]{2579, 2600, 1}, new int[]{2602, 2608, 1}, new int[]{2610, 2611, 1}, new int[]{2613, 2614, 1}, new int[]{2616, 2617, 1}, new int[]{2649, 2652, 1}, new int[]{2654, 2674, 20}, new int[]{2675, 2676, 1}, new int[]{2693, 2701, 1}, new int[]{2703, 2705, 1}, new int[]{2707, 2728, 1}, new int[]{2730, 2736, 1}, new int[]{2738, 2739, 1}, new int[]{2741, 2745, 1}, new int[]{2749, 2768, 19}, new int[]{2784, 2785, 1}, new int[]{2821, 2828, 1}, new int[]{2831, 2832, 1}, new int[]{2835, 2856, 1}, new int[]{2858, 2864, 1}, new int[]{2866, 2867, 1}, new int[]{2869, 2873, 1}, new int[]{2877, 2908, 31}, new int[]{2909, 2911, 2}, new int[]{2912, 2913, 1}, new int[]{2929, 2947, 18}, new int[]{2949, 2954, 1}, new int[]{2958, 2960, 1}, new int[]{2962, 2965, 1}, new int[]{2969, 2970, 1}, new int[]{2972, 2974, 2}, new int[]{2975, 2979, 4}, new int[]{2980, 2984, 4}, new int[]{2985, 2986, 1}, new int[]{2990, AuthApiStatusCodes.AUTH_API_ACCESS_FORBIDDEN, 1}, new int[]{3024, 3077, 53}, new int[]{3078, 3084, 1}, new int[]{3086, 3088, 1}, new int[]{3090, 3112, 1}, new int[]{3114, 3123, 1}, new int[]{3125, 3129, 1}, new int[]{3133, 3160, 27}, new int[]{3161, 3168, 7}, new int[]{3169, 3205, 36}, new int[]{3206, 3212, 1}, new int[]{3214, 3216, 1}, new int[]{3218, 3240, 1}, new int[]{3242, 3251, 1}, new int[]{3253, 3257, 1}, new int[]{3261, 3294, 33}, new int[]{3296, 3297, 1}, new int[]{3313, 3314, 1}, new int[]{3333, 3340, 1}, new int[]{3342, 3344, 1}, new int[]{3346, 3386, 1}, new int[]{3389, 3406, 17}, new int[]{3424, 3425, 1}, new int[]{3450, 3455, 1}, new int[]{3461, 3478, 1}, new int[]{3482, 3505, 1}, new int[]{3507, 3515, 1}, new int[]{3517, 3520, 3}, new int[]{3521, 3526, 1}, new int[]{3585, 3632, 1}, new int[]{3634, 3635, 1}, new int[]{3648, 3653, 1}, new int[]{3713, 3714, 1}, new int[]{3716, 3719, 3}, new int[]{3720, 3722, 2}, new int[]{3725, 3732, 7}, new int[]{3733, 3735, 1}, new int[]{3737, 3743, 1}, new int[]{3745, 3747, 1}, new int[]{3749, 3751, 2}, new int[]{3754, 3755, 1}, new int[]{3757, 3760, 1}, new int[]{3762, 3763, 1}, new int[]{3773, 3776, 3}, new int[]{3777, 3780, 1}, new int[]{3804, 3805, 1}, new int[]{3840, 3904, 64}, new int[]{3905, 3911, 1}, new int[]{3913, 3948, 1}, new int[]{3976, 3980, 1}, new int[]{4096, 4138, 1}, new int[]{4159, 4176, 17}, new int[]{4177, 4181, 1}, new int[]{4186, 4189, 1}, new int[]{4193, 4197, 4}, new int[]{4198, 4206, 8}, new int[]{4207, 4208, 1}, new int[]{4213, 4225, 1}, new int[]{4238, 4304, 66}, new int[]{4305, 4346, 1}, new int[]{4352, 4680, 1}, new int[]{4682, 4685, 1}, new int[]{4688, 4694, 1}, new int[]{4696, 4698, 2}, new int[]{4699, 4701, 1}, new int[]{4704, 4744, 1}, new int[]{4746, 4749, 1}, new int[]{4752, 4784, 1}, new int[]{4786, 4789, 1}, new int[]{4792, 4798, 1}, new int[]{4800, 4802, 2}, new int[]{4803, 4805, 1}, new int[]{4808, 4822, 1}, new int[]{4824, 4880, 1}, new int[]{4882, 4885, 1}, new int[]{4888, 4954, 1}, new int[]{4992, 5007, 1}, new int[]{5024, 5108, 1}, new int[]{5121, 5740, 1}, new int[]{5743, 5759, 1}, new int[]{5761, 5786, 1}, new int[]{5792, 5866, 1}, new int[]{5888, 5900, 1}, new int[]{5902, 5905, 1}, new int[]{5920, 5937, 1}, new int[]{5952, 5969, 1}, new int[]{5984, 5996, 1}, new int[]{5998, 6000, 1}, new int[]{6016, 6067, 1}, new int[]{6108, 6176, 68}, new int[]{6177, 6210, 1}, new int[]{6212, 6263, 1}, new int[]{6272, 6312, 1}, new int[]{6314, 6320, 6}, new int[]{6321, 6389, 1}, new int[]{6400, 6428, 1}, new int[]{6480, 6509, 1}, new int[]{6512, 6516, 1}, new int[]{6528, 6571, 1}, new int[]{6593, 6599, 1}, new int[]{6656, 6678, 1}, new int[]{6688, 6740, 1}, new int[]{6917, 6963, 1}, new int[]{6981, 6987, 1}, new int[]{7043, 7072, 1}, new int[]{7086, 7087, 1}, new int[]{7104, 7141, 1}, new int[]{7168, 7203, 1}, new int[]{7245, 7247, 1}, new int[]{7258, 7287, 1}, new int[]{7401, 7404, 1}, new int[]{7406, 7409, 1}, new int[]{8501, 8504, 1}, new int[]{11568, 11621, 1}, new int[]{11648, 11670, 1}, new int[]{11680, 11686, 1}, new int[]{11688, 11694, 1}, new int[]{11696, 11702, 1}, new int[]{11704, 11710, 1}, new int[]{11712, 11718, 1}, new int[]{11720, 11726, 1}, new int[]{11728, 11734, 1}, new int[]{11736, 11742, 1}, new int[]{12294, 12348, 54}, new int[]{12353, 12438, 1}, new int[]{12447, 12449, 2}, new int[]{12450, 12538, 1}, new int[]{12543, 12549, 6}, new int[]{12550, 12589, 1}, new int[]{12593, 12686, 1}, new int[]{12704, 12730, 1}, new int[]{12784, 12799, 1}, new int[]{13312, 19893, 1}, new int[]{19968, 40907, 1}, new int[]{40960, 40980, 1}, new int[]{40982, 42124, 1}, new int[]{42192, 42231, 1}, new int[]{42240, 42507, 1}, new int[]{42512, 42527, 1}, new int[]{42538, 42539, 1}, new int[]{42606, 42656, 50}, new int[]{42657, 42725, 1}, new int[]{43003, 43009, 1}, new int[]{43011, 43013, 1}, new int[]{43015, 43018, 1}, new int[]{43020, 43042, 1}, new int[]{43072, 43123, 1}, new int[]{43138, 43187, 1}, new int[]{43250, 43255, 1}, new int[]{43259, 43274, 15}, new int[]{43275, 43301, 1}, new int[]{43312, 43334, 1}, new int[]{43360, 43388, 1}, new int[]{43396, 43442, 1}, new int[]{43520, 43560, 1}, new int[]{43584, 43586, 1}, new int[]{43588, 43595, 1}, new int[]{43616, 43631, 1}, new int[]{43633, 43638, 1}, new int[]{43642, 43648, 6}, new int[]{43649, 43695, 1}, new int[]{43697, 43701, 4}, new int[]{43702, 43705, 3}, new int[]{43706, 43709, 1}, new int[]{43712, 43714, 2}, new int[]{43739, 43740, 1}, new int[]{43777, 43782, 1}, new int[]{43785, 43790, 1}, new int[]{43793, 43798, 1}, new int[]{43808, 43814, 1}, new int[]{43816, 43822, 1}, new int[]{43968, 44002, 1}, new int[]{44032, 55203, 1}, new int[]{55216, 55238, 1}, new int[]{55243, 55291, 1}, new int[]{63744, 64045, 1}, new int[]{64048, 64109, 1}, new int[]{64112, 64217, 1}, new int[]{64285, 64287, 2}, new int[]{64288, 64296, 1}, new int[]{64298, 64310, 1}, new int[]{64312, 64316, 1}, new int[]{64318, 64320, 2}, new int[]{64321, 64323, 2}, new int[]{64324, 64326, 2}, new int[]{64327, 64433, 1}, new int[]{64467, 64829, 1}, new int[]{64848, 64911, 1}, new int[]{64914, 64967, 1}, new int[]{65008, 65019, 1}, new int[]{65136, 65140, 1}, new int[]{65142, 65276, 1}, new int[]{65382, 65391, 1}, new int[]{65393, 65437, 1}, new int[]{65440, 65470, 1}, new int[]{65474, 65479, 1}, new int[]{65482, 65487, 1}, new int[]{65490, 65495, 1}, new int[]{65498, 65500, 1}, new int[]{65536, 65547, 1}, new int[]{65549, 65574, 1}, new int[]{65576, 65594, 1}, new int[]{65596, 65597, 1}, new int[]{65599, 65613, 1}, new int[]{65616, 65629, 1}, new int[]{65664, 65786, 1}, new int[]{66176, 66204, 1}, new int[]{66208, 66256, 1}, new int[]{66304, 66334, 1}, new int[]{66352, 66368, 1}, new int[]{66370, 66377, 1}, new int[]{66432, 66461, 1}, new int[]{66464, 66499, 1}, new int[]{66504, 66511, 1}, new int[]{66640, 66717, 1}, new int[]{67584, 67589, 1}, new int[]{67592, 67594, 2}, new int[]{67595, 67637, 1}, new int[]{67639, 67640, 1}, new int[]{67644, 67647, 3}, new int[]{67648, 67669, 1}, new int[]{67840, 67861, 1}, new int[]{67872, 67897, 1}, new int[]{68096, 68112, 16}, new int[]{68113, 68115, 1}, new int[]{68117, 68119, 1}, new int[]{68121, 68147, 1}, new int[]{68192, 68220, 1}, new int[]{68352, 68405, 1}, new int[]{68416, 68437, 1}, new int[]{68448, 68466, 1}, new int[]{68608, 68680, 1}, new int[]{69635, 69687, 1}, new int[]{69763, 69807, 1}, new int[]{73728, 74606, 1}, new int[]{77824, 78894, 1}, new int[]{92160, 92728, 1}, new int[]{110592, 110593, 1}, new int[]{131072, 173782, 1}, new int[]{173824, 177972, 1}, new int[]{177984, 178205, 1}, new int[]{194560, 195101, 1}};
    }

    private static int[][] make_Mn() {
        return new int[][]{new int[]{ViewUtils.EDGE_TO_EDGE_FLAGS, 879, 1}, new int[]{1155, 1159, 1}, new int[]{1425, 1469, 1}, new int[]{1471, 1473, 2}, new int[]{1474, 1476, 2}, new int[]{1477, 1479, 2}, new int[]{1552, 1562, 1}, new int[]{1611, 1631, 1}, new int[]{1648, 1750, 102}, new int[]{1751, 1756, 1}, new int[]{1759, 1764, 1}, new int[]{1767, 1768, 1}, new int[]{1770, 1773, 1}, new int[]{1809, 1840, 31}, new int[]{1841, 1866, 1}, new int[]{1958, 1968, 1}, new int[]{2027, 2035, 1}, new int[]{2070, 2073, 1}, new int[]{2075, 2083, 1}, new int[]{2085, 2087, 1}, new int[]{2089, 2093, 1}, new int[]{2137, 2139, 1}, new int[]{2304, 2306, 1}, new int[]{2362, 2364, 2}, new int[]{2369, 2376, 1}, new int[]{2381, 2385, 4}, new int[]{2386, 2391, 1}, new int[]{2402, 2403, 1}, new int[]{2433, 2492, 59}, new int[]{2497, 2500, 1}, new int[]{2509, 2530, 21}, new int[]{2531, 2561, 30}, new int[]{2562, 2620, 58}, new int[]{2625, 2626, 1}, new int[]{2631, 2632, 1}, new int[]{2635, 2637, 1}, new int[]{2641, 2672, 31}, new int[]{2673, 2677, 4}, new int[]{2689, 2690, 1}, new int[]{2748, 2753, 5}, new int[]{2754, 2757, 1}, new int[]{2759, 2760, 1}, new int[]{2765, 2786, 21}, new int[]{2787, 2817, 30}, new int[]{2876, 2879, 3}, new int[]{2881, 2884, 1}, new int[]{2893, 2902, 9}, new int[]{2914, 2915, 1}, new int[]{2946, 3008, 62}, new int[]{3021, 3134, 113}, new int[]{3135, 3136, 1}, new int[]{3142, 3144, 1}, new int[]{3146, 3149, 1}, new int[]{3157, 3158, 1}, new int[]{3170, 3171, 1}, new int[]{3260, 3263, 3}, new int[]{3270, 3276, 6}, new int[]{3277, 3298, 21}, new int[]{3299, 3393, 94}, new int[]{3394, 3396, 1}, new int[]{3405, 3426, 21}, new int[]{3427, 3530, 103}, new int[]{3538, 3540, 1}, new int[]{3542, 3633, 91}, new int[]{3636, 3642, 1}, new int[]{3655, 3662, 1}, new int[]{3761, 3764, 3}, new int[]{3765, 3769, 1}, new int[]{3771, 3772, 1}, new int[]{3784, 3789, 1}, new int[]{3864, 3865, 1}, new int[]{3893, 3897, 2}, new int[]{3953, 3966, 1}, new int[]{Utf8.MASK_2BYTES, 3972, 1}, new int[]{3974, 3975, 1}, new int[]{3981, 3991, 1}, new int[]{3993, 4028, 1}, new int[]{4038, 4141, 103}, new int[]{4142, 4144, 1}, new int[]{4146, 4151, 1}, new int[]{4153, 4154, 1}, new int[]{4157, 4158, 1}, new int[]{4184, 4185, 1}, new int[]{4190, 4192, 1}, new int[]{4209, 4212, 1}, new int[]{4226, 4229, 3}, new int[]{4230, 4237, 7}, new int[]{4253, 4957, TypedValues.TransitionType.TYPE_AUTO_TRANSITION}, new int[]{4958, 4959, 1}, new int[]{5906, 5908, 1}, new int[]{5938, 5940, 1}, new int[]{5970, 5971, 1}, new int[]{6002, 6003, 1}, new int[]{6071, 6077, 1}, new int[]{6086, 6089, 3}, new int[]{6090, 6099, 1}, new int[]{6109, 6155, 46}, new int[]{6156, 6157, 1}, new int[]{6313, 6432, 119}, new int[]{6433, 6434, 1}, new int[]{6439, 6440, 1}, new int[]{6450, 6457, 7}, new int[]{6458, 6459, 1}, new int[]{6679, 6680, 1}, new int[]{6742, 6744, 2}, new int[]{6745, 6750, 1}, new int[]{6752, 6754, 2}, new int[]{6757, 6764, 1}, new int[]{6771, 6780, 1}, new int[]{6783, 6912, 129}, new int[]{6913, 6915, 1}, new int[]{6964, 6966, 2}, new int[]{6967, 6970, 1}, new int[]{6972, 6978, 6}, new int[]{7019, 7027, 1}, new int[]{7040, 7041, 1}, new int[]{7074, 7077, 1}, new int[]{7080, 7081, 1}, new int[]{7142, 7144, 2}, new int[]{7145, 7149, 4}, new int[]{7151, 7153, 1}, new int[]{7212, 7219, 1}, new int[]{7222, 7223, 1}, new int[]{7376, 7378, 1}, new int[]{7380, 7392, 1}, new int[]{7394, 7400, 1}, new int[]{7405, 7616, 211}, new int[]{7617, 7654, 1}, new int[]{7676, 7679, 1}, new int[]{8400, 8412, 1}, new int[]{8417, 8421, 4}, new int[]{8422, 8432, 1}, new int[]{11503, 11505, 1}, new int[]{11647, 11744, 97}, new int[]{11745, 11775, 1}, new int[]{12330, 12335, 1}, new int[]{12441, 12442, 1}, new int[]{42607, 42620, 13}, new int[]{42621, 42736, 115}, new int[]{42737, 43010, 273}, new int[]{43014, 43019, 5}, new int[]{43045, 43046, 1}, new int[]{43204, 43232, 28}, new int[]{43233, 43249, 1}, new int[]{43302, 43309, 1}, new int[]{43335, 43345, 1}, new int[]{43392, 43394, 1}, new int[]{43443, 43446, 3}, new int[]{43447, 43449, 1}, new int[]{43452, 43561, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY}, new int[]{43562, 43566, 1}, new int[]{43569, 43570, 1}, new int[]{43573, 43574, 1}, new int[]{43587, 43596, 9}, new int[]{43696, 43698, 2}, new int[]{43699, 43700, 1}, new int[]{43703, 43704, 1}, new int[]{43710, 43711, 1}, new int[]{43713, 44005, 292}, new int[]{44008, 44013, 5}, new int[]{64286, 65024, 738}, new int[]{65025, 65039, 1}, new int[]{65056, 65062, 1}, new int[]{66045, 68097, 2052}, new int[]{68098, 68099, 1}, new int[]{68101, 68102, 1}, new int[]{68108, 68111, 1}, new int[]{68152, 68154, 1}, new int[]{68159, 69633, 1474}, new int[]{69688, 69702, 1}, new int[]{69760, 69761, 1}, new int[]{69811, 69814, 1}, new int[]{69817, 69818, 1}, new int[]{119143, 119145, 1}, new int[]{119163, 119170, 1}, new int[]{119173, 119179, 1}, new int[]{119210, 119213, 1}, new int[]{119362, 119364, 1}, new int[]{917760, 917999, 1}};
    }

    private static int[][] make_Me() {
        return new int[][]{new int[]{1160, 1161, 1}, new int[]{8413, 8416, 1}, new int[]{8418, 8420, 1}, new int[]{42608, 42610, 1}};
    }

    private static int[][] make_Mc() {
        return new int[][]{new int[]{2307, 2363, 56}, new int[]{2366, 2368, 1}, new int[]{2377, 2380, 1}, new int[]{2382, 2383, 1}, new int[]{2434, 2435, 1}, new int[]{2494, 2496, 1}, new int[]{2503, 2504, 1}, new int[]{2507, 2508, 1}, new int[]{2519, 2563, 44}, new int[]{2622, 2624, 1}, new int[]{2691, 2750, 59}, new int[]{2751, 2752, 1}, new int[]{2761, 2763, 2}, new int[]{2764, 2818, 54}, new int[]{2819, 2878, 59}, new int[]{2880, 2887, 7}, new int[]{2888, 2891, 3}, new int[]{2892, 2903, 11}, new int[]{AuthApiStatusCodes.AUTH_APP_CERT_ERROR, 3007, 1}, new int[]{3009, 3010, 1}, new int[]{3014, 3016, 1}, new int[]{3018, 3020, 1}, new int[]{3031, 3073, 42}, new int[]{3074, 3075, 1}, new int[]{3137, 3140, 1}, new int[]{3202, 3203, 1}, new int[]{3262, 3264, 2}, new int[]{3265, 3268, 1}, new int[]{3271, 3272, 1}, new int[]{3274, 3275, 1}, new int[]{3285, 3286, 1}, new int[]{3330, 3331, 1}, new int[]{3390, 3392, 1}, new int[]{3398, 3400, 1}, new int[]{3402, 3404, 1}, new int[]{3415, 3458, 43}, new int[]{3459, 3535, 76}, new int[]{3536, 3537, 1}, new int[]{3544, 3551, 1}, new int[]{3570, 3571, 1}, new int[]{3902, 3903, 1}, new int[]{3967, 4139, 172}, new int[]{4140, 4145, 5}, new int[]{4152, 4155, 3}, new int[]{4156, 4182, 26}, new int[]{4183, 4194, 11}, new int[]{4195, 4196, 1}, new int[]{4199, 4205, 1}, new int[]{4227, 4228, 1}, new int[]{4231, 4236, 1}, new int[]{4239, 4250, 11}, new int[]{4251, 4252, 1}, new int[]{6070, 6078, 8}, new int[]{6079, 6085, 1}, new int[]{6087, 6088, 1}, new int[]{6435, 6438, 1}, new int[]{6441, 6443, 1}, new int[]{6448, 6449, 1}, new int[]{6451, 6456, 1}, new int[]{6576, 6592, 1}, new int[]{6600, 6601, 1}, new int[]{6681, 6683, 1}, new int[]{6741, 6743, 2}, new int[]{6753, 6755, 2}, new int[]{6756, 6765, 9}, new int[]{6766, 6770, 1}, new int[]{6916, 6965, 49}, new int[]{6971, 6973, 2}, new int[]{6974, 6977, 1}, new int[]{6979, 6980, 1}, new int[]{7042, 7073, 31}, new int[]{7078, 7079, 1}, new int[]{7082, 7143, 61}, new int[]{7146, 7148, 1}, new int[]{7150, 7154, 4}, new int[]{7155, 7204, 49}, new int[]{7205, 7211, 1}, new int[]{7220, 7221, 1}, new int[]{7393, 7410, 17}, new int[]{43043, 43044, 1}, new int[]{43047, 43136, 89}, new int[]{43137, 43188, 51}, new int[]{43189, 43203, 1}, new int[]{43346, 43347, 1}, new int[]{43395, 43444, 49}, new int[]{43445, 43450, 5}, new int[]{43451, 43453, 2}, new int[]{43454, 43456, 1}, new int[]{43567, 43568, 1}, new int[]{43571, 43572, 1}, new int[]{43597, 43643, 46}, new int[]{44003, 44004, 1}, new int[]{44006, 44007, 1}, new int[]{44009, 44010, 1}, new int[]{44012, 69632, 25620}, new int[]{69634, 69762, 128}, new int[]{69808, 69810, 1}, new int[]{69815, 69816, 1}, new int[]{119141, 119142, 1}, new int[]{119149, 119154, 1}};
    }

    private static int[][] make_Nd() {
        return new int[][]{new int[]{48, 57, 1}, new int[]{1632, 1641, 1}, new int[]{1776, 1785, 1}, new int[]{1984, 1993, 1}, new int[]{2406, 2415, 1}, new int[]{2534, 2543, 1}, new int[]{2662, 2671, 1}, new int[]{2790, 2799, 1}, new int[]{2918, 2927, 1}, new int[]{3046, 3055, 1}, new int[]{3174, 3183, 1}, new int[]{3302, 3311, 1}, new int[]{3430, 3439, 1}, new int[]{3664, 3673, 1}, new int[]{3792, 3801, 1}, new int[]{3872, 3881, 1}, new int[]{4160, 4169, 1}, new int[]{4240, 4249, 1}, new int[]{6112, 6121, 1}, new int[]{6160, 6169, 1}, new int[]{6470, 6479, 1}, new int[]{6608, 6617, 1}, new int[]{6784, 6793, 1}, new int[]{6800, 6809, 1}, new int[]{6992, 7001, 1}, new int[]{7088, 7097, 1}, new int[]{7232, 7241, 1}, new int[]{7248, 7257, 1}, new int[]{42528, 42537, 1}, new int[]{43216, 43225, 1}, new int[]{43264, 43273, 1}, new int[]{43472, 43481, 1}, new int[]{43600, 43609, 1}, new int[]{44016, 44025, 1}, new int[]{65296, 65305, 1}, new int[]{66720, 66729, 1}, new int[]{69734, 69743, 1}, new int[]{120782, 120831, 1}};
    }

    private static int[][] make_Nl() {
        return new int[][]{new int[]{5870, 5872, 1}, new int[]{8544, 8578, 1}, new int[]{8581, 8584, 1}, new int[]{12295, 12321, 26}, new int[]{12322, 12329, 1}, new int[]{12344, 12346, 1}, new int[]{42726, 42735, 1}, new int[]{65856, 65908, 1}, new int[]{66369, 66378, 9}, new int[]{66513, 66517, 1}, new int[]{74752, 74850, 1}};
    }

    private static int[][] make_No() {
        return new int[][]{new int[]{178, 179, 1}, new int[]{185, 188, 3}, new int[]{189, 190, 1}, new int[]{2548, 2553, 1}, new int[]{2930, 2935, 1}, new int[]{3056, 3058, 1}, new int[]{3192, 3198, 1}, new int[]{3440, 3445, 1}, new int[]{3882, 3891, 1}, new int[]{4969, 4988, 1}, new int[]{6128, 6137, 1}, new int[]{6618, 8304, 1686}, new int[]{8308, 8313, 1}, new int[]{8320, 8329, 1}, new int[]{8528, 8543, 1}, new int[]{8585, 9312, 727}, new int[]{9313, 9371, 1}, new int[]{9450, 9471, 1}, new int[]{10102, 10131, 1}, new int[]{11517, 12690, 1173}, new int[]{12691, 12693, 1}, new int[]{12832, 12841, 1}, new int[]{12881, 12895, 1}, new int[]{12928, 12937, 1}, new int[]{12977, 12991, 1}, new int[]{43056, 43061, 1}, new int[]{65799, 65843, 1}, new int[]{65909, 65912, 1}, new int[]{65930, 66336, 406}, new int[]{66337, 66339, 1}, new int[]{67672, 67679, 1}, new int[]{67862, 67867, 1}, new int[]{68160, 68167, 1}, new int[]{68221, 68222, 1}, new int[]{68440, 68447, 1}, new int[]{68472, 68479, 1}, new int[]{69216, 69246, 1}, new int[]{69714, 69733, 1}, new int[]{119648, 119665, 1}, new int[]{127232, 127242, 1}};
    }

    private static int[][] make_Zs() {
        return new int[][]{new int[]{32, 160, 128}, new int[]{5760, 6158, 398}, new int[]{8192, 8202, 1}, new int[]{8239, 8287, 48}, new int[]{12288, 12288, 1}};
    }

    private static int[][] make_Zl() {
        return new int[][]{new int[]{8232, 8232, 1}};
    }

    private static int[][] make_Zp() {
        return new int[][]{new int[]{8233, 8233, 1}};
    }

    private static int[][] make_Cc() {
        return new int[][]{new int[]{0, 31, 1}, new int[]{127, 159, 1}};
    }

    private static int[][] make_Cf() {
        return new int[][]{new int[]{173, 1536, 1363}, new int[]{1537, 1539, 1}, new int[]{1757, 1807, 50}, new int[]{6068, 6069, 1}, new int[]{8203, 8207, 1}, new int[]{8234, 8238, 1}, new int[]{8288, 8292, 1}, new int[]{8298, 8303, 1}, new int[]{65279, 65529, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION}, new int[]{65530, 65531, 1}, new int[]{69821, 119155, 49334}, new int[]{119156, 119162, 1}, new int[]{917505, 917536, 31}, new int[]{917537, 917631, 1}};
    }

    private static int[][] make_Co() {
        return new int[][]{new int[]{57344, 63743, 1}, new int[]{983040, 1048573, 1}, new int[]{1048576, 1114109, 1}};
    }

    private static int[][] make_Cs() {
        return new int[][]{new int[]{55296, 57343, 1}};
    }

    private static int[][] make_Pd() {
        return new int[][]{new int[]{45, 1418, 1373}, new int[]{1470, 5120, 3650}, new int[]{6150, 8208, 2058}, new int[]{8209, 8213, 1}, new int[]{11799, 11802, 3}, new int[]{12316, 12336, 20}, new int[]{12448, 65073, 52625}, new int[]{65074, 65112, 38}, new int[]{65123, 65293, 170}};
    }

    private static int[][] make_Ps() {
        return new int[][]{new int[]{40, 91, 51}, new int[]{123, 3898, 3775}, new int[]{3900, 5787, 1887}, new int[]{8218, 8222, 4}, new int[]{8261, 8317, 56}, new int[]{8333, 9001, 668}, new int[]{10088, 10100, 2}, new int[]{10181, 10214, 33}, new int[]{10216, 10222, 2}, new int[]{10627, 10647, 2}, new int[]{10712, 10714, 2}, new int[]{10748, 11810, 1062}, new int[]{11812, 11816, 2}, new int[]{12296, 12304, 2}, new int[]{12308, 12314, 2}, new int[]{12317, 64830, 52513}, new int[]{65047, 65077, 30}, new int[]{65079, 65091, 2}, new int[]{65095, 65113, 18}, new int[]{65115, 65117, 2}, new int[]{65288, 65339, 51}, new int[]{65371, 65375, 4}, new int[]{65378, 65378, 1}};
    }

    private static int[][] make_Pe() {
        return new int[][]{new int[]{41, 93, 52}, new int[]{125, 3899, 3774}, new int[]{3901, 5788, 1887}, new int[]{8262, 8318, 56}, new int[]{8334, 9002, 668}, new int[]{10089, 10101, 2}, new int[]{10182, 10215, 33}, new int[]{10217, 10223, 2}, new int[]{10628, 10648, 2}, new int[]{10713, 10715, 2}, new int[]{10749, 11811, 1062}, new int[]{11813, 11817, 2}, new int[]{12297, 12305, 2}, new int[]{12309, 12315, 2}, new int[]{12318, 12319, 1}, new int[]{64831, 65048, 217}, new int[]{65078, 65092, 2}, new int[]{65096, 65114, 18}, new int[]{65116, 65118, 2}, new int[]{65289, 65341, 52}, new int[]{65373, 65379, 3}};
    }

    private static int[][] make_Pc() {
        return new int[][]{new int[]{95, 8255, 8160}, new int[]{8256, 8276, 20}, new int[]{65075, 65076, 1}, new int[]{65101, 65103, 1}, new int[]{65343, 65343, 1}};
    }

    private static int[][] make_Po() {
        return new int[][]{new int[]{33, 35, 1}, new int[]{37, 39, 1}, new int[]{42, 46, 2}, new int[]{47, 58, 11}, new int[]{59, 63, 4}, new int[]{64, 92, 28}, new int[]{161, 183, 22}, new int[]{191, 894, 703}, new int[]{TypedValues.Custom.TYPE_STRING, 1370, 467}, new int[]{1371, 1375, 1}, new int[]{1417, 1472, 55}, new int[]{1475, 1478, 3}, new int[]{1523, 1524, 1}, new int[]{1545, 1546, 1}, new int[]{1548, 1549, 1}, new int[]{1563, 1566, 3}, new int[]{1567, 1642, 75}, new int[]{1643, 1645, 1}, new int[]{1748, 1792, 44}, new int[]{1793, 1805, 1}, new int[]{2039, 2041, 1}, new int[]{2096, 2110, 1}, new int[]{2142, 2404, 262}, new int[]{2405, 2416, 11}, new int[]{3572, 3663, 91}, new int[]{3674, 3675, 1}, new int[]{3844, 3858, 1}, new int[]{3973, 4048, 75}, new int[]{4049, 4052, 1}, new int[]{4057, 4058, 1}, new int[]{4170, 4175, 1}, new int[]{4347, 4961, 614}, new int[]{4962, 4968, 1}, new int[]{5741, 5742, 1}, new int[]{5867, 5869, 1}, new int[]{5941, 5942, 1}, new int[]{6100, 6102, 1}, new int[]{6104, 6106, 1}, new int[]{6144, 6149, 1}, new int[]{6151, 6154, 1}, new int[]{6468, 6469, 1}, new int[]{6686, 6687, 1}, new int[]{6816, 6822, 1}, new int[]{6824, 6829, 1}, new int[]{7002, 7008, 1}, new int[]{7164, 7167, 1}, new int[]{7227, 7231, 1}, new int[]{7294, 7295, 1}, new int[]{7379, 8214, 835}, new int[]{8215, 8224, 9}, new int[]{8225, 8231, 1}, new int[]{8240, 8248, 1}, new int[]{8251, 8254, 1}, new int[]{8257, 8259, 1}, new int[]{8263, 8273, 1}, new int[]{8275, 8277, 2}, new int[]{8278, 8286, 1}, new int[]{11513, 11516, 1}, new int[]{11518, 11519, 1}, new int[]{11632, 11776, 144}, new int[]{11777, 11782, 5}, new int[]{11783, 11784, 1}, new int[]{11787, 11790, 3}, new int[]{11791, 11798, 1}, new int[]{11800, 11801, 1}, new int[]{11803, 11806, 3}, new int[]{11807, 11818, 11}, new int[]{11819, 11822, 1}, new int[]{11824, 11825, 1}, new int[]{12289, 12291, 1}, new int[]{12349, 12539, 190}, new int[]{42238, 42239, 1}, new int[]{42509, 42511, 1}, new int[]{42611, 42622, 11}, new int[]{42738, 42743, 1}, new int[]{43124, 43127, 1}, new int[]{43214, 43215, 1}, new int[]{43256, 43258, 1}, new int[]{43310, 43311, 1}, new int[]{43359, 43457, 98}, new int[]{43458, 43469, 1}, new int[]{43486, 43487, 1}, new int[]{43612, 43615, 1}, new int[]{43742, 43743, 1}, new int[]{44011, 65040, 21029}, new int[]{65041, 65046, 1}, new int[]{65049, 65072, 23}, new int[]{65093, 65094, 1}, new int[]{65097, 65100, 1}, new int[]{65104, 65106, 1}, new int[]{65108, 65111, 1}, new int[]{65119, 65121, 1}, new int[]{65128, 65130, 2}, new int[]{65131, 65281, 150}, new int[]{65282, 65283, 1}, new int[]{65285, 65287, 1}, new int[]{65290, 65294, 2}, new int[]{65295, 65306, 11}, new int[]{65307, 65311, 4}, new int[]{65312, 65340, 28}, new int[]{65377, 65380, 3}, new int[]{65381, 65792, 411}, new int[]{65793, 66463, 670}, new int[]{66512, 67671, 1159}, new int[]{67871, 67903, 32}, new int[]{68176, 68184, 1}, new int[]{68223, 68409, 186}, new int[]{68410, 68415, 1}, new int[]{69703, 69709, 1}, new int[]{69819, 69820, 1}, new int[]{69822, 69825, 1}, new int[]{74864, 74867, 1}};
    }

    private static int[][] make_Sm() {
        return new int[][]{new int[]{43, 60, 17}, new int[]{61, 62, 1}, new int[]{124, WebSocketProtocol.PAYLOAD_SHORT, 2}, new int[]{172, 177, 5}, new int[]{215, 247, 32}, new int[]{PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, 1542, 528}, new int[]{1543, 1544, 1}, new int[]{8260, 8274, 14}, new int[]{8314, 8316, 1}, new int[]{8330, 8332, 1}, new int[]{8472, 8512, 40}, new int[]{8513, 8516, 1}, new int[]{8523, 8592, 69}, new int[]{8593, 8596, 1}, new int[]{8602, 8603, 1}, new int[]{8608, 8614, 3}, new int[]{8622, 8654, 32}, new int[]{8655, 8658, 3}, new int[]{8660, 8692, 32}, new int[]{8693, 8959, 1}, new int[]{8968, 8971, 1}, new int[]{8992, 8993, 1}, new int[]{9084, 9115, 31}, new int[]{9116, 9139, 1}, new int[]{9180, 9185, 1}, new int[]{9655, 9665, 10}, new int[]{9720, 9727, 1}, new int[]{9839, 10176, 337}, new int[]{10177, 10180, 1}, new int[]{10183, 10186, 1}, new int[]{10188, 10190, 2}, new int[]{10191, 10213, 1}, new int[]{10224, 10239, 1}, new int[]{10496, 10626, 1}, new int[]{10649, 10711, 1}, new int[]{10716, 10747, 1}, new int[]{10750, 11007, 1}, new int[]{11056, 11076, 1}, new int[]{11079, 11084, 1}, new int[]{64297, 65122, 825}, new int[]{65124, 65126, 1}, new int[]{65291, 65308, 17}, new int[]{65309, 65310, 1}, new int[]{65372, 65374, 2}, new int[]{65506, 65513, 7}, new int[]{65514, 65516, 1}, new int[]{120513, 120539, 26}, new int[]{120571, 120597, 26}, new int[]{120629, 120655, 26}, new int[]{120687, 120713, 26}, new int[]{120745, 120771, 26}};
    }

    private static int[][] make_Sc() {
        return new int[][]{new int[]{36, 162, WebSocketProtocol.PAYLOAD_SHORT}, new int[]{163, 165, 1}, new int[]{1547, 2546, 999}, new int[]{2547, 2555, 8}, new int[]{2801, 3065, 264}, new int[]{3647, 6107, 2460}, new int[]{8352, 8377, 1}, new int[]{43064, 65020, 21956}, new int[]{65129, 65284, 155}, new int[]{65504, 65505, 1}, new int[]{65509, 65510, 1}};
    }

    private static int[][] make_Sk() {
        return new int[][]{new int[]{94, 96, 2}, new int[]{168, 175, 7}, new int[]{180, 184, 4}, new int[]{TypedValues.TransitionType.TYPE_STAGGERED, 709, 1}, new int[]{722, 735, 1}, new int[]{741, 747, 1}, new int[]{749, 751, 2}, new int[]{752, 767, 1}, new int[]{885, 900, 15}, new int[]{TypedValues.Custom.TYPE_FLOAT, 8125, 7224}, new int[]{8127, 8129, 1}, new int[]{8141, 8143, 1}, new int[]{8157, 8159, 1}, new int[]{8173, 8175, 1}, new int[]{8189, 8190, 1}, new int[]{12443, 12444, 1}, new int[]{42752, 42774, 1}, new int[]{42784, 42785, 1}, new int[]{42889, 42890, 1}, new int[]{64434, 64449, 1}, new int[]{65342, 65344, 2}, new int[]{65507, 65507, 1}};
    }

    private static int[][] make_So() {
        return new int[][]{new int[]{166, 167, 1}, new int[]{169, 174, 5}, new int[]{176, 182, 6}, new int[]{1154, 1550, 396}, new int[]{1551, 1758, MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD}, new int[]{1769, 1789, 20}, new int[]{1790, 2038, 248}, new int[]{2554, 2928, 374}, new int[]{3059, 3064, 1}, new int[]{3066, 3199, 133}, new int[]{3449, 3841, 392}, new int[]{3842, 3843, 1}, new int[]{3859, 3863, 1}, new int[]{3866, 3871, 1}, new int[]{3892, 3896, 2}, new int[]{4030, 4037, 1}, new int[]{4039, 4044, 1}, new int[]{4046, 4047, 1}, new int[]{4053, 4056, 1}, new int[]{4254, 4255, 1}, new int[]{4960, 5008, 48}, new int[]{5009, 5017, 1}, new int[]{6464, 6622, 158}, new int[]{6623, 6655, 1}, new int[]{7009, 7018, 1}, new int[]{7028, 7036, 1}, new int[]{8448, 8449, 1}, new int[]{8451, 8454, 1}, new int[]{8456, 8457, 1}, new int[]{8468, 8470, 2}, new int[]{8471, 8478, 7}, new int[]{8479, 8483, 1}, new int[]{8485, 8489, 2}, new int[]{8494, 8506, 12}, new int[]{8507, 8522, 15}, new int[]{8524, 8525, 1}, new int[]{8527, 8597, 70}, new int[]{8598, 8601, 1}, new int[]{8604, 8607, 1}, new int[]{8609, 8610, 1}, new int[]{8612, 8613, 1}, new int[]{8615, 8621, 1}, new int[]{8623, 8653, 1}, new int[]{8656, 8657, 1}, new int[]{8659, 8661, 2}, new int[]{8662, 8691, 1}, new int[]{8960, 8967, 1}, new int[]{8972, 8991, 1}, new int[]{8994, 9000, 1}, new int[]{9003, 9083, 1}, new int[]{9085, 9114, 1}, new int[]{9140, 9179, 1}, new int[]{9186, 9203, 1}, new int[]{9216, 9254, 1}, new int[]{9280, 9290, 1}, new int[]{9372, 9449, 1}, new int[]{9472, 9654, 1}, new int[]{9656, 9664, 1}, new int[]{9666, 9719, 1}, new int[]{9728, 9838, 1}, new int[]{9840, 9983, 1}, new int[]{9985, 10087, 1}, new int[]{10132, 10175, 1}, new int[]{10240, 10495, 1}, new int[]{11008, 11055, 1}, new int[]{11077, 11078, 1}, new int[]{11088, 11097, 1}, new int[]{11493, 11498, 1}, new int[]{11904, 11929, 1}, new int[]{11931, 12019, 1}, new int[]{12032, 12245, 1}, new int[]{12272, 12283, 1}, new int[]{12292, 12306, 14}, new int[]{12307, 12320, 13}, new int[]{12342, 12343, 1}, new int[]{12350, 12351, 1}, new int[]{12688, 12689, 1}, new int[]{12694, 12703, 1}, new int[]{12736, 12771, 1}, new int[]{12800, 12830, 1}, new int[]{12842, 12880, 1}, new int[]{12896, 12927, 1}, new int[]{12938, 12976, 1}, new int[]{12992, 13054, 1}, new int[]{13056, 13311, 1}, new int[]{19904, 19967, 1}, new int[]{42128, 42182, 1}, new int[]{43048, 43051, 1}, new int[]{43062, 43063, 1}, new int[]{43065, 43639, 574}, new int[]{43640, 43641, 1}, new int[]{65021, 65508, 487}, new int[]{65512, 65517, 5}, new int[]{65518, 65532, 14}, new int[]{Utf8.REPLACEMENT_CODE_POINT, 65794, 261}, new int[]{65847, 65855, 1}, new int[]{65913, 65929, 1}, new int[]{65936, 65947, 1}, new int[]{66000, 66044, 1}, new int[]{118784, 119029, 1}, new int[]{119040, 119078, 1}, new int[]{119081, 119140, 1}, new int[]{119146, 119148, 1}, new int[]{119171, 119172, 1}, new int[]{119180, 119209, 1}, new int[]{119214, 119261, 1}, new int[]{119296, 119361, 1}, new int[]{119365, 119552, 187}, new int[]{119553, 119638, 1}, new int[]{126976, 127019, 1}, new int[]{127024, 127123, 1}, new int[]{127136, 127150, 1}, new int[]{127153, 127166, 1}, new int[]{127169, 127183, 1}, new int[]{127185, 127199, 1}, new int[]{127248, 127278, 1}, new int[]{127280, 127337, 1}, new int[]{127344, 127386, 1}, new int[]{127462, 127490, 1}, new int[]{127504, 127546, 1}, new int[]{127552, 127560, 1}, new int[]{127568, 127569, 1}, new int[]{127744, 127776, 1}, new int[]{127792, 127797, 1}, new int[]{127799, 127868, 1}, new int[]{127872, 127891, 1}, new int[]{127904, 127940, 1}, new int[]{127942, 127946, 1}, new int[]{127968, 127984, 1}, new int[]{128000, 128062, 1}, new int[]{128064, 128066, 2}, new int[]{128067, 128247, 1}, new int[]{128249, 128252, 1}, new int[]{128256, 128317, 1}, new int[]{128336, 128359, 1}, new int[]{128507, 128511, 1}, new int[]{128513, 128528, 1}, new int[]{128530, 128532, 1}, new int[]{128534, 128540, 2}, new int[]{128541, 128542, 1}, new int[]{128544, 128549, 1}, new int[]{128552, 128555, 1}, new int[]{128557, 128560, 3}, new int[]{128561, 128563, 1}, new int[]{128565, 128576, 1}, new int[]{128581, 128591, 1}, new int[]{128640, 128709, 1}, new int[]{128768, 128883, 1}};
    }

    private static int[][] make_Pi() {
        return new int[][]{new int[]{171, 8216, 8045}, new int[]{8219, 8220, 1}, new int[]{8223, 8249, 26}, new int[]{11778, 11780, 2}, new int[]{11785, 11788, 3}, new int[]{11804, 11808, 4}};
    }

    private static int[][] make_Pf() {
        return new int[][]{new int[]{187, 8217, 8030}, new int[]{8221, 8250, 29}, new int[]{11779, 11781, 2}, new int[]{11786, 11789, 3}, new int[]{11805, 11809, 4}};
    }

    private static int[][] make_P() {
        return new int[][]{new int[]{33, 35, 1}, new int[]{37, 42, 1}, new int[]{44, 47, 1}, new int[]{58, 59, 1}, new int[]{63, 64, 1}, new int[]{91, 93, 1}, new int[]{95, 123, 28}, new int[]{125, 161, 36}, new int[]{171, 183, 12}, new int[]{187, 191, 4}, new int[]{894, TypedValues.Custom.TYPE_STRING, 9}, new int[]{1370, 1375, 1}, new int[]{1417, 1418, 1}, new int[]{1470, 1472, 2}, new int[]{1475, 1478, 3}, new int[]{1523, 1524, 1}, new int[]{1545, 1546, 1}, new int[]{1548, 1549, 1}, new int[]{1563, 1566, 3}, new int[]{1567, 1642, 75}, new int[]{1643, 1645, 1}, new int[]{1748, 1792, 44}, new int[]{1793, 1805, 1}, new int[]{2039, 2041, 1}, new int[]{2096, 2110, 1}, new int[]{2142, 2404, 262}, new int[]{2405, 2416, 11}, new int[]{3572, 3663, 91}, new int[]{3674, 3675, 1}, new int[]{3844, 3858, 1}, new int[]{3898, 3901, 1}, new int[]{3973, 4048, 75}, new int[]{4049, 4052, 1}, new int[]{4057, 4058, 1}, new int[]{4170, 4175, 1}, new int[]{4347, 4961, 614}, new int[]{4962, 4968, 1}, new int[]{5120, 5741, 621}, new int[]{5742, 5787, 45}, new int[]{5788, 5867, 79}, new int[]{5868, 5869, 1}, new int[]{5941, 5942, 1}, new int[]{6100, 6102, 1}, new int[]{6104, 6106, 1}, new int[]{6144, 6154, 1}, new int[]{6468, 6469, 1}, new int[]{6686, 6687, 1}, new int[]{6816, 6822, 1}, new int[]{6824, 6829, 1}, new int[]{7002, 7008, 1}, new int[]{7164, 7167, 1}, new int[]{7227, 7231, 1}, new int[]{7294, 7295, 1}, new int[]{7379, 8208, 829}, new int[]{8209, 8231, 1}, new int[]{8240, 8259, 1}, new int[]{8261, 8273, 1}, new int[]{8275, 8286, 1}, new int[]{8317, 8318, 1}, new int[]{8333, 8334, 1}, new int[]{9001, 9002, 1}, new int[]{10088, 10101, 1}, new int[]{10181, 10182, 1}, new int[]{10214, 10223, 1}, new int[]{10627, 10648, 1}, new int[]{10712, 10715, 1}, new int[]{10748, 10749, 1}, new int[]{11513, 11516, 1}, new int[]{11518, 11519, 1}, new int[]{11632, 11776, 144}, new int[]{11777, 11822, 1}, new int[]{11824, 11825, 1}, new int[]{12289, 12291, 1}, new int[]{12296, 12305, 1}, new int[]{12308, 12319, 1}, new int[]{12336, 12349, 13}, new int[]{12448, 12539, 91}, new int[]{42238, 42239, 1}, new int[]{42509, 42511, 1}, new int[]{42611, 42622, 11}, new int[]{42738, 42743, 1}, new int[]{43124, 43127, 1}, new int[]{43214, 43215, 1}, new int[]{43256, 43258, 1}, new int[]{43310, 43311, 1}, new int[]{43359, 43457, 98}, new int[]{43458, 43469, 1}, new int[]{43486, 43487, 1}, new int[]{43612, 43615, 1}, new int[]{43742, 43743, 1}, new int[]{44011, 64830, 20819}, new int[]{64831, 65040, 209}, new int[]{65041, 65049, 1}, new int[]{65072, 65106, 1}, new int[]{65108, 65121, 1}, new int[]{65123, 65128, 5}, new int[]{65130, 65131, 1}, new int[]{65281, 65283, 1}, new int[]{65285, 65290, 1}, new int[]{65292, 65295, 1}, new int[]{65306, 65307, 1}, new int[]{65311, 65312, 1}, new int[]{65339, 65341, 1}, new int[]{65343, 65371, 28}, new int[]{65373, 65375, 2}, new int[]{65376, 65381, 1}, new int[]{65792, 65793, 1}, new int[]{66463, 66512, 49}, new int[]{67671, 67871, 200}, new int[]{67903, 68176, 273}, new int[]{68177, 68184, 1}, new int[]{68223, 68409, 186}, new int[]{68410, 68415, 1}, new int[]{69703, 69709, 1}, new int[]{69819, 69820, 1}, new int[]{69822, 69825, 1}, new int[]{74864, 74867, 1}};
    }

    private static int[][] make_S() {
        return new int[][]{new int[]{36, 43, 7}, new int[]{60, 62, 1}, new int[]{94, 96, 2}, new int[]{124, WebSocketProtocol.PAYLOAD_SHORT, 2}, new int[]{162, 169, 1}, new int[]{172, 174, 2}, new int[]{175, 177, 1}, new int[]{180, 184, 2}, new int[]{215, 247, 32}, new int[]{TypedValues.TransitionType.TYPE_STAGGERED, 709, 1}, new int[]{722, 735, 1}, new int[]{741, 747, 1}, new int[]{749, 751, 2}, new int[]{752, 767, 1}, new int[]{885, 900, 15}, new int[]{TypedValues.Custom.TYPE_FLOAT, PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW, 113}, new int[]{1154, 1542, 388}, new int[]{1543, 1544, 1}, new int[]{1547, 1550, 3}, new int[]{1551, 1758, MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD}, new int[]{1769, 1789, 20}, new int[]{1790, 2038, 248}, new int[]{2546, 2547, 1}, new int[]{2554, 2555, 1}, new int[]{2801, 2928, 127}, new int[]{3059, 3066, 1}, new int[]{3199, 3449, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION}, new int[]{3647, 3841, 194}, new int[]{3842, 3843, 1}, new int[]{3859, 3863, 1}, new int[]{3866, 3871, 1}, new int[]{3892, 3896, 2}, new int[]{4030, 4037, 1}, new int[]{4039, 4044, 1}, new int[]{4046, 4047, 1}, new int[]{4053, 4056, 1}, new int[]{4254, 4255, 1}, new int[]{4960, 5008, 48}, new int[]{5009, 5017, 1}, new int[]{6107, 6464, 357}, new int[]{6622, 6655, 1}, new int[]{7009, 7018, 1}, new int[]{7028, 7036, 1}, new int[]{8125, 8127, 2}, new int[]{8128, 8129, 1}, new int[]{8141, 8143, 1}, new int[]{8157, 8159, 1}, new int[]{8173, 8175, 1}, new int[]{8189, 8190, 1}, new int[]{8260, 8274, 14}, new int[]{8314, 8316, 1}, new int[]{8330, 8332, 1}, new int[]{8352, 8377, 1}, new int[]{8448, 8449, 1}, new int[]{8451, 8454, 1}, new int[]{8456, 8457, 1}, new int[]{8468, 8470, 2}, new int[]{8471, 8472, 1}, new int[]{8478, 8483, 1}, new int[]{8485, 8489, 2}, new int[]{8494, 8506, 12}, new int[]{8507, 8512, 5}, new int[]{8513, 8516, 1}, new int[]{8522, 8525, 1}, new int[]{8527, 8592, 65}, new int[]{8593, 9000, 1}, new int[]{9003, 9203, 1}, new int[]{9216, 9254, 1}, new int[]{9280, 9290, 1}, new int[]{9372, 9449, 1}, new int[]{9472, 9983, 1}, new int[]{9985, 10087, 1}, new int[]{10132, 10180, 1}, new int[]{10183, 10186, 1}, new int[]{10188, 10190, 2}, new int[]{10191, 10213, 1}, new int[]{10224, 10626, 1}, new int[]{10649, 10711, 1}, new int[]{10716, 10747, 1}, new int[]{10750, 11084, 1}, new int[]{11088, 11097, 1}, new int[]{11493, 11498, 1}, new int[]{11904, 11929, 1}, new int[]{11931, 12019, 1}, new int[]{12032, 12245, 1}, new int[]{12272, 12283, 1}, new int[]{12292, 12306, 14}, new int[]{12307, 12320, 13}, new int[]{12342, 12343, 1}, new int[]{12350, 12351, 1}, new int[]{12443, 12444, 1}, new int[]{12688, 12689, 1}, new int[]{12694, 12703, 1}, new int[]{12736, 12771, 1}, new int[]{12800, 12830, 1}, new int[]{12842, 12880, 1}, new int[]{12896, 12927, 1}, new int[]{12938, 12976, 1}, new int[]{12992, 13054, 1}, new int[]{13056, 13311, 1}, new int[]{19904, 19967, 1}, new int[]{42128, 42182, 1}, new int[]{42752, 42774, 1}, new int[]{42784, 42785, 1}, new int[]{42889, 42890, 1}, new int[]{43048, 43051, 1}, new int[]{43062, 43065, 1}, new int[]{43639, 43641, 1}, new int[]{64297, 64434, 137}, new int[]{64435, 64449, 1}, new int[]{65020, 65021, 1}, new int[]{65122, 65124, 2}, new int[]{65125, 65126, 1}, new int[]{65129, 65284, 155}, new int[]{65291, 65308, 17}, new int[]{65309, 65310, 1}, new int[]{65342, 65344, 2}, new int[]{65372, 65374, 2}, new int[]{65504, 65510, 1}, new int[]{65512, 65518, 1}, new int[]{65532, Utf8.REPLACEMENT_CODE_POINT, 1}, new int[]{65794, 65847, 53}, new int[]{65848, 65855, 1}, new int[]{65913, 65929, 1}, new int[]{65936, 65947, 1}, new int[]{66000, 66044, 1}, new int[]{118784, 119029, 1}, new int[]{119040, 119078, 1}, new int[]{119081, 119140, 1}, new int[]{119146, 119148, 1}, new int[]{119171, 119172, 1}, new int[]{119180, 119209, 1}, new int[]{119214, 119261, 1}, new int[]{119296, 119361, 1}, new int[]{119365, 119552, 187}, new int[]{119553, 119638, 1}, new int[]{120513, 120539, 26}, new int[]{120571, 120597, 26}, new int[]{120629, 120655, 26}, new int[]{120687, 120713, 26}, new int[]{120745, 120771, 26}, new int[]{126976, 127019, 1}, new int[]{127024, 127123, 1}, new int[]{127136, 127150, 1}, new int[]{127153, 127166, 1}, new int[]{127169, 127183, 1}, new int[]{127185, 127199, 1}, new int[]{127248, 127278, 1}, new int[]{127280, 127337, 1}, new int[]{127344, 127386, 1}, new int[]{127462, 127490, 1}, new int[]{127504, 127546, 1}, new int[]{127552, 127560, 1}, new int[]{127568, 127569, 1}, new int[]{127744, 127776, 1}, new int[]{127792, 127797, 1}, new int[]{127799, 127868, 1}, new int[]{127872, 127891, 1}, new int[]{127904, 127940, 1}, new int[]{127942, 127946, 1}, new int[]{127968, 127984, 1}, new int[]{128000, 128062, 1}, new int[]{128064, 128066, 2}, new int[]{128067, 128247, 1}, new int[]{128249, 128252, 1}, new int[]{128256, 128317, 1}, new int[]{128336, 128359, 1}, new int[]{128507, 128511, 1}, new int[]{128513, 128528, 1}, new int[]{128530, 128532, 1}, new int[]{128534, 128540, 2}, new int[]{128541, 128542, 1}, new int[]{128544, 128549, 1}, new int[]{128552, 128555, 1}, new int[]{128557, 128560, 3}, new int[]{128561, 128563, 1}, new int[]{128565, 128576, 1}, new int[]{128581, 128591, 1}, new int[]{128640, 128709, 1}, new int[]{128768, 128883, 1}};
    }

    private static int[][] make_C() {
        return new int[][]{new int[]{0, 31, 1}, new int[]{127, 159, 1}, new int[]{173, 1536, 1363}, new int[]{1537, 1539, 1}, new int[]{1757, 1807, 50}, new int[]{6068, 6069, 1}, new int[]{8203, 8207, 1}, new int[]{8234, 8238, 1}, new int[]{8288, 8292, 1}, new int[]{8298, 8303, 1}, new int[]{55296, 63743, 1}, new int[]{65279, 65529, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION}, new int[]{65530, 65531, 1}, new int[]{69821, 119155, 49334}, new int[]{119156, 119162, 1}, new int[]{917505, 917536, 31}, new int[]{917537, 917631, 1}, new int[]{983040, 1048573, 1}, new int[]{1048576, 1114109, 1}};
    }

    private static int[][] make_Z() {
        return new int[][]{new int[]{32, 160, 128}, new int[]{5760, 6158, 398}, new int[]{8192, 8202, 1}, new int[]{8232, 8233, 1}, new int[]{8239, 8287, 48}, new int[]{12288, 12288, 1}};
    }

    private static int[][] make_L() {
        return new int[][]{new int[]{65, 90, 1}, new int[]{97, 122, 1}, new int[]{170, 181, 11}, new int[]{186, 192, 6}, new int[]{193, 214, 1}, new int[]{216, 246, 1}, new int[]{248, TypedValues.TransitionType.TYPE_INTERPOLATOR, 1}, new int[]{710, 721, 1}, new int[]{736, 740, 1}, new int[]{748, 750, 2}, new int[]{880, 884, 1}, new int[]{886, 887, 1}, new int[]{890, 893, 1}, new int[]{TypedValues.Custom.TYPE_COLOR, TypedValues.Custom.TYPE_BOOLEAN, 2}, new int[]{TypedValues.Custom.TYPE_DIMENSION, TypedValues.Custom.TYPE_REFERENCE, 1}, new int[]{908, 910, 2}, new int[]{911, PDF417Common.NUMBER_OF_CODEWORDS, 1}, new int[]{931, PointerIconCompat.TYPE_ALL_SCROLL, 1}, new int[]{PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, 1153, 1}, new int[]{1162, 1319, 1}, new int[]{1329, 1366, 1}, new int[]{1369, 1377, 8}, new int[]{1378, 1415, 1}, new int[]{1488, 1514, 1}, new int[]{1520, 1522, 1}, new int[]{1568, 1610, 1}, new int[]{1646, 1647, 1}, new int[]{1649, 1747, 1}, new int[]{1749, 1765, 16}, new int[]{1766, 1774, 8}, new int[]{1775, 1786, 11}, new int[]{1787, 1788, 1}, new int[]{1791, 1808, 17}, new int[]{1810, 1839, 1}, new int[]{1869, 1957, 1}, new int[]{1969, 1994, 25}, new int[]{1995, 2026, 1}, new int[]{2036, 2037, 1}, new int[]{2042, 2048, 6}, new int[]{2049, 2069, 1}, new int[]{2074, 2084, 10}, new int[]{2088, 2112, 24}, new int[]{2113, 2136, 1}, new int[]{2308, 2361, 1}, new int[]{2365, 2384, 19}, new int[]{2392, 2401, 1}, new int[]{2417, 2423, 1}, new int[]{2425, 2431, 1}, new int[]{2437, 2444, 1}, new int[]{2447, 2448, 1}, new int[]{2451, 2472, 1}, new int[]{2474, 2480, 1}, new int[]{2482, 2486, 4}, new int[]{2487, 2489, 1}, new int[]{2493, 2510, 17}, new int[]{2524, 2525, 1}, new int[]{2527, 2529, 1}, new int[]{2544, 2545, 1}, new int[]{2565, 2570, 1}, new int[]{2575, 2576, 1}, new int[]{2579, 2600, 1}, new int[]{2602, 2608, 1}, new int[]{2610, 2611, 1}, new int[]{2613, 2614, 1}, new int[]{2616, 2617, 1}, new int[]{2649, 2652, 1}, new int[]{2654, 2674, 20}, new int[]{2675, 2676, 1}, new int[]{2693, 2701, 1}, new int[]{2703, 2705, 1}, new int[]{2707, 2728, 1}, new int[]{2730, 2736, 1}, new int[]{2738, 2739, 1}, new int[]{2741, 2745, 1}, new int[]{2749, 2768, 19}, new int[]{2784, 2785, 1}, new int[]{2821, 2828, 1}, new int[]{2831, 2832, 1}, new int[]{2835, 2856, 1}, new int[]{2858, 2864, 1}, new int[]{2866, 2867, 1}, new int[]{2869, 2873, 1}, new int[]{2877, 2908, 31}, new int[]{2909, 2911, 2}, new int[]{2912, 2913, 1}, new int[]{2929, 2947, 18}, new int[]{2949, 2954, 1}, new int[]{2958, 2960, 1}, new int[]{2962, 2965, 1}, new int[]{2969, 2970, 1}, new int[]{2972, 2974, 2}, new int[]{2975, 2979, 4}, new int[]{2980, 2984, 4}, new int[]{2985, 2986, 1}, new int[]{2990, AuthApiStatusCodes.AUTH_API_ACCESS_FORBIDDEN, 1}, new int[]{3024, 3077, 53}, new int[]{3078, 3084, 1}, new int[]{3086, 3088, 1}, new int[]{3090, 3112, 1}, new int[]{3114, 3123, 1}, new int[]{3125, 3129, 1}, new int[]{3133, 3160, 27}, new int[]{3161, 3168, 7}, new int[]{3169, 3205, 36}, new int[]{3206, 3212, 1}, new int[]{3214, 3216, 1}, new int[]{3218, 3240, 1}, new int[]{3242, 3251, 1}, new int[]{3253, 3257, 1}, new int[]{3261, 3294, 33}, new int[]{3296, 3297, 1}, new int[]{3313, 3314, 1}, new int[]{3333, 3340, 1}, new int[]{3342, 3344, 1}, new int[]{3346, 3386, 1}, new int[]{3389, 3406, 17}, new int[]{3424, 3425, 1}, new int[]{3450, 3455, 1}, new int[]{3461, 3478, 1}, new int[]{3482, 3505, 1}, new int[]{3507, 3515, 1}, new int[]{3517, 3520, 3}, new int[]{3521, 3526, 1}, new int[]{3585, 3632, 1}, new int[]{3634, 3635, 1}, new int[]{3648, 3654, 1}, new int[]{3713, 3714, 1}, new int[]{3716, 3719, 3}, new int[]{3720, 3722, 2}, new int[]{3725, 3732, 7}, new int[]{3733, 3735, 1}, new int[]{3737, 3743, 1}, new int[]{3745, 3747, 1}, new int[]{3749, 3751, 2}, new int[]{3754, 3755, 1}, new int[]{3757, 3760, 1}, new int[]{3762, 3763, 1}, new int[]{3773, 3776, 3}, new int[]{3777, 3780, 1}, new int[]{3782, 3804, 22}, new int[]{3805, 3840, 35}, new int[]{3904, 3911, 1}, new int[]{3913, 3948, 1}, new int[]{3976, 3980, 1}, new int[]{4096, 4138, 1}, new int[]{4159, 4176, 17}, new int[]{4177, 4181, 1}, new int[]{4186, 4189, 1}, new int[]{4193, 4197, 4}, new int[]{4198, 4206, 8}, new int[]{4207, 4208, 1}, new int[]{4213, 4225, 1}, new int[]{4238, 4256, 18}, new int[]{4257, 4293, 1}, new int[]{4304, 4346, 1}, new int[]{4348, 4352, 4}, new int[]{4353, 4680, 1}, new int[]{4682, 4685, 1}, new int[]{4688, 4694, 1}, new int[]{4696, 4698, 2}, new int[]{4699, 4701, 1}, new int[]{4704, 4744, 1}, new int[]{4746, 4749, 1}, new int[]{4752, 4784, 1}, new int[]{4786, 4789, 1}, new int[]{4792, 4798, 1}, new int[]{4800, 4802, 2}, new int[]{4803, 4805, 1}, new int[]{4808, 4822, 1}, new int[]{4824, 4880, 1}, new int[]{4882, 4885, 1}, new int[]{4888, 4954, 1}, new int[]{4992, 5007, 1}, new int[]{5024, 5108, 1}, new int[]{5121, 5740, 1}, new int[]{5743, 5759, 1}, new int[]{5761, 5786, 1}, new int[]{5792, 5866, 1}, new int[]{5888, 5900, 1}, new int[]{5902, 5905, 1}, new int[]{5920, 5937, 1}, new int[]{5952, 5969, 1}, new int[]{5984, 5996, 1}, new int[]{5998, 6000, 1}, new int[]{6016, 6067, 1}, new int[]{6103, 6108, 5}, new int[]{6176, 6263, 1}, new int[]{6272, 6312, 1}, new int[]{6314, 6320, 6}, new int[]{6321, 6389, 1}, new int[]{6400, 6428, 1}, new int[]{6480, 6509, 1}, new int[]{6512, 6516, 1}, new int[]{6528, 6571, 1}, new int[]{6593, 6599, 1}, new int[]{6656, 6678, 1}, new int[]{6688, 6740, 1}, new int[]{6823, 6917, 94}, new int[]{6918, 6963, 1}, new int[]{6981, 6987, 1}, new int[]{7043, 7072, 1}, new int[]{7086, 7087, 1}, new int[]{7104, 7141, 1}, new int[]{7168, 7203, 1}, new int[]{7245, 7247, 1}, new int[]{7258, 7293, 1}, new int[]{7401, 7404, 1}, new int[]{7406, 7409, 1}, new int[]{7424, 7615, 1}, new int[]{7680, 7957, 1}, new int[]{7960, 7965, 1}, new int[]{7968, 8005, 1}, new int[]{8008, 8013, 1}, new int[]{8016, 8023, 1}, new int[]{8025, 8031, 2}, new int[]{8032, 8061, 1}, new int[]{8064, 8116, 1}, new int[]{8118, 8124, 1}, new int[]{8126, 8130, 4}, new int[]{8131, 8132, 1}, new int[]{8134, 8140, 1}, new int[]{8144, 8147, 1}, new int[]{8150, 8155, 1}, new int[]{8160, 8172, 1}, new int[]{8178, 8180, 1}, new int[]{8182, 8188, 1}, new int[]{8305, 8319, 14}, new int[]{8336, 8348, 1}, new int[]{8450, 8455, 5}, new int[]{8458, 8467, 1}, new int[]{8469, 8473, 4}, new int[]{8474, 8477, 1}, new int[]{8484, 8490, 2}, new int[]{8491, 8493, 1}, new int[]{8495, 8505, 1}, new int[]{8508, 8511, 1}, new int[]{8517, 8521, 1}, new int[]{8526, 8579, 53}, new int[]{8580, 11264, 2684}, new int[]{11265, 11310, 1}, new int[]{11312, 11358, 1}, new int[]{11360, 11492, 1}, new int[]{11499, 11502, 1}, new int[]{11520, 11557, 1}, new int[]{11568, 11621, 1}, new int[]{11631, 11648, 17}, new int[]{11649, 11670, 1}, new int[]{11680, 11686, 1}, new int[]{11688, 11694, 1}, new int[]{11696, 11702, 1}, new int[]{11704, 11710, 1}, new int[]{11712, 11718, 1}, new int[]{11720, 11726, 1}, new int[]{11728, 11734, 1}, new int[]{11736, 11742, 1}, new int[]{11823, 12293, 470}, new int[]{12294, 12337, 43}, new int[]{12338, 12341, 1}, new int[]{12347, 12348, 1}, new int[]{12353, 12438, 1}, new int[]{GLUtils.EGL_GL_COLORSPACE_KHR, 12447, 1}, new int[]{12449, 12538, 1}, new int[]{12540, 12543, 1}, new int[]{12549, 12589, 1}, new int[]{12593, 12686, 1}, new int[]{12704, 12730, 1}, new int[]{12784, 12799, 1}, new int[]{13312, 19893, 1}, new int[]{19968, 40907, 1}, new int[]{40960, 42124, 1}, new int[]{42192, 42237, 1}, new int[]{42240, 42508, 1}, new int[]{42512, 42527, 1}, new int[]{42538, 42539, 1}, new int[]{42560, 42606, 1}, new int[]{42623, 42647, 1}, new int[]{42656, 42725, 1}, new int[]{42775, 42783, 1}, new int[]{42786, 42888, 1}, new int[]{42891, 42894, 1}, new int[]{42896, 42897, 1}, new int[]{42912, 42921, 1}, new int[]{43002, 43009, 1}, new int[]{43011, 43013, 1}, new int[]{43015, 43018, 1}, new int[]{43020, 43042, 1}, new int[]{43072, 43123, 1}, new int[]{43138, 43187, 1}, new int[]{43250, 43255, 1}, new int[]{43259, 43274, 15}, new int[]{43275, 43301, 1}, new int[]{43312, 43334, 1}, new int[]{43360, 43388, 1}, new int[]{43396, 43442, 1}, new int[]{43471, 43520, 49}, new int[]{43521, 43560, 1}, new int[]{43584, 43586, 1}, new int[]{43588, 43595, 1}, new int[]{43616, 43638, 1}, new int[]{43642, 43648, 6}, new int[]{43649, 43695, 1}, new int[]{43697, 43701, 4}, new int[]{43702, 43705, 3}, new int[]{43706, 43709, 1}, new int[]{43712, 43714, 2}, new int[]{43739, 43741, 1}, new int[]{43777, 43782, 1}, new int[]{43785, 43790, 1}, new int[]{43793, 43798, 1}, new int[]{43808, 43814, 1}, new int[]{43816, 43822, 1}, new int[]{43968, 44002, 1}, new int[]{44032, 55203, 1}, new int[]{55216, 55238, 1}, new int[]{55243, 55291, 1}, new int[]{63744, 64045, 1}, new int[]{64048, 64109, 1}, new int[]{64112, 64217, 1}, new int[]{64256, 64262, 1}, new int[]{64275, 64279, 1}, new int[]{64285, 64287, 2}, new int[]{64288, 64296, 1}, new int[]{64298, 64310, 1}, new int[]{64312, 64316, 1}, new int[]{64318, 64320, 2}, new int[]{64321, 64323, 2}, new int[]{64324, 64326, 2}, new int[]{64327, 64433, 1}, new int[]{64467, 64829, 1}, new int[]{64848, 64911, 1}, new int[]{64914, 64967, 1}, new int[]{65008, 65019, 1}, new int[]{65136, 65140, 1}, new int[]{65142, 65276, 1}, new int[]{65313, 65338, 1}, new int[]{65345, 65370, 1}, new int[]{65382, 65470, 1}, new int[]{65474, 65479, 1}, new int[]{65482, 65487, 1}, new int[]{65490, 65495, 1}, new int[]{65498, 65500, 1}, new int[]{65536, 65547, 1}, new int[]{65549, 65574, 1}, new int[]{65576, 65594, 1}, new int[]{65596, 65597, 1}, new int[]{65599, 65613, 1}, new int[]{65616, 65629, 1}, new int[]{65664, 65786, 1}, new int[]{66176, 66204, 1}, new int[]{66208, 66256, 1}, new int[]{66304, 66334, 1}, new int[]{66352, 66368, 1}, new int[]{66370, 66377, 1}, new int[]{66432, 66461, 1}, new int[]{66464, 66499, 1}, new int[]{66504, 66511, 1}, new int[]{66560, 66717, 1}, new int[]{67584, 67589, 1}, new int[]{67592, 67594, 2}, new int[]{67595, 67637, 1}, new int[]{67639, 67640, 1}, new int[]{67644, 67647, 3}, new int[]{67648, 67669, 1}, new int[]{67840, 67861, 1}, new int[]{67872, 67897, 1}, new int[]{68096, 68112, 16}, new int[]{68113, 68115, 1}, new int[]{68117, 68119, 1}, new int[]{68121, 68147, 1}, new int[]{68192, 68220, 1}, new int[]{68352, 68405, 1}, new int[]{68416, 68437, 1}, new int[]{68448, 68466, 1}, new int[]{68608, 68680, 1}, new int[]{69635, 69687, 1}, new int[]{69763, 69807, 1}, new int[]{73728, 74606, 1}, new int[]{77824, 78894, 1}, new int[]{92160, 92728, 1}, new int[]{110592, 110593, 1}, new int[]{119808, 119892, 1}, new int[]{119894, 119964, 1}, new int[]{119966, 119967, 1}, new int[]{119970, 119973, 3}, new int[]{119974, 119977, 3}, new int[]{119978, 119980, 1}, new int[]{119982, 119993, 1}, new int[]{119995, 119997, 2}, new int[]{119998, 120003, 1}, new int[]{120005, 120069, 1}, new int[]{120071, 120074, 1}, new int[]{120077, 120084, 1}, new int[]{120086, 120092, 1}, new int[]{120094, 120121, 1}, new int[]{120123, 120126, 1}, new int[]{120128, 120132, 1}, new int[]{120134, 120138, 4}, new int[]{120139, 120144, 1}, new int[]{120146, 120485, 1}, new int[]{120488, 120512, 1}, new int[]{120514, 120538, 1}, new int[]{120540, 120570, 1}, new int[]{120572, 120596, 1}, new int[]{120598, 120628, 1}, new int[]{120630, 120654, 1}, new int[]{120656, 120686, 1}, new int[]{120688, 120712, 1}, new int[]{120714, 120744, 1}, new int[]{120746, 120770, 1}, new int[]{120772, 120779, 1}, new int[]{131072, 173782, 1}, new int[]{173824, 177972, 1}, new int[]{177984, 178205, 1}, new int[]{194560, 195101, 1}};
    }

    private static int[][] make_M() {
        return new int[][]{new int[]{ViewUtils.EDGE_TO_EDGE_FLAGS, 879, 1}, new int[]{1155, 1161, 1}, new int[]{1425, 1469, 1}, new int[]{1471, 1473, 2}, new int[]{1474, 1476, 2}, new int[]{1477, 1479, 2}, new int[]{1552, 1562, 1}, new int[]{1611, 1631, 1}, new int[]{1648, 1750, 102}, new int[]{1751, 1756, 1}, new int[]{1759, 1764, 1}, new int[]{1767, 1768, 1}, new int[]{1770, 1773, 1}, new int[]{1809, 1840, 31}, new int[]{1841, 1866, 1}, new int[]{1958, 1968, 1}, new int[]{2027, 2035, 1}, new int[]{2070, 2073, 1}, new int[]{2075, 2083, 1}, new int[]{2085, 2087, 1}, new int[]{2089, 2093, 1}, new int[]{2137, 2139, 1}, new int[]{2304, 2307, 1}, new int[]{2362, 2364, 1}, new int[]{2366, 2383, 1}, new int[]{2385, 2391, 1}, new int[]{2402, 2403, 1}, new int[]{2433, 2435, 1}, new int[]{2492, 2494, 2}, new int[]{2495, 2500, 1}, new int[]{2503, 2504, 1}, new int[]{2507, 2509, 1}, new int[]{2519, 2530, 11}, new int[]{2531, 2561, 30}, new int[]{2562, 2563, 1}, new int[]{2620, 2622, 2}, new int[]{2623, 2626, 1}, new int[]{2631, 2632, 1}, new int[]{2635, 2637, 1}, new int[]{2641, 2672, 31}, new int[]{2673, 2677, 4}, new int[]{2689, 2691, 1}, new int[]{2748, 2750, 2}, new int[]{2751, 2757, 1}, new int[]{2759, 2761, 1}, new int[]{2763, 2765, 1}, new int[]{2786, 2787, 1}, new int[]{2817, 2819, 1}, new int[]{2876, 2878, 2}, new int[]{2879, 2884, 1}, new int[]{2887, 2888, 1}, new int[]{2891, 2893, 1}, new int[]{2902, 2903, 1}, new int[]{2914, 2915, 1}, new int[]{2946, AuthApiStatusCodes.AUTH_APP_CERT_ERROR, 60}, new int[]{3007, 3010, 1}, new int[]{3014, 3016, 1}, new int[]{3018, 3021, 1}, new int[]{3031, 3073, 42}, new int[]{3074, 3075, 1}, new int[]{3134, 3140, 1}, new int[]{3142, 3144, 1}, new int[]{3146, 3149, 1}, new int[]{3157, 3158, 1}, new int[]{3170, 3171, 1}, new int[]{3202, 3203, 1}, new int[]{3260, 3262, 2}, new int[]{3263, 3268, 1}, new int[]{3270, 3272, 1}, new int[]{3274, 3277, 1}, new int[]{3285, 3286, 1}, new int[]{3298, 3299, 1}, new int[]{3330, 3331, 1}, new int[]{3390, 3396, 1}, new int[]{3398, 3400, 1}, new int[]{3402, 3405, 1}, new int[]{3415, 3426, 11}, new int[]{3427, 3458, 31}, new int[]{3459, 3530, 71}, new int[]{3535, 3540, 1}, new int[]{3542, 3544, 2}, new int[]{3545, 3551, 1}, new int[]{3570, 3571, 1}, new int[]{3633, 3636, 3}, new int[]{3637, 3642, 1}, new int[]{3655, 3662, 1}, new int[]{3761, 3764, 3}, new int[]{3765, 3769, 1}, new int[]{3771, 3772, 1}, new int[]{3784, 3789, 1}, new int[]{3864, 3865, 1}, new int[]{3893, 3897, 2}, new int[]{3902, 3903, 1}, new int[]{3953, 3972, 1}, new int[]{3974, 3975, 1}, new int[]{3981, 3991, 1}, new int[]{3993, 4028, 1}, new int[]{4038, 4139, 101}, new int[]{4140, 4158, 1}, new int[]{4182, 4185, 1}, new int[]{4190, 4192, 1}, new int[]{4194, 4196, 1}, new int[]{4199, 4205, 1}, new int[]{4209, 4212, 1}, new int[]{4226, 4237, 1}, new int[]{4239, 4250, 11}, new int[]{4251, 4253, 1}, new int[]{4957, 4959, 1}, new int[]{5906, 5908, 1}, new int[]{5938, 5940, 1}, new int[]{5970, 5971, 1}, new int[]{6002, 6003, 1}, new int[]{6070, 6099, 1}, new int[]{6109, 6155, 46}, new int[]{6156, 6157, 1}, new int[]{6313, 6432, 119}, new int[]{6433, 6443, 1}, new int[]{6448, 6459, 1}, new int[]{6576, 6592, 1}, new int[]{6600, 6601, 1}, new int[]{6679, 6683, 1}, new int[]{6741, 6750, 1}, new int[]{6752, 6780, 1}, new int[]{6783, 6912, 129}, new int[]{6913, 6916, 1}, new int[]{6964, 6980, 1}, new int[]{7019, 7027, 1}, new int[]{7040, 7042, 1}, new int[]{7073, 7082, 1}, new int[]{7142, 7155, 1}, new int[]{7204, 7223, 1}, new int[]{7376, 7378, 1}, new int[]{7380, 7400, 1}, new int[]{7405, 7410, 5}, new int[]{7616, 7654, 1}, new int[]{7676, 7679, 1}, new int[]{8400, 8432, 1}, new int[]{11503, 11505, 1}, new int[]{11647, 11744, 97}, new int[]{11745, 11775, 1}, new int[]{12330, 12335, 1}, new int[]{12441, 12442, 1}, new int[]{42607, 42610, 1}, new int[]{42620, 42621, 1}, new int[]{42736, 42737, 1}, new int[]{43010, 43014, 4}, new int[]{43019, 43043, 24}, new int[]{43044, 43047, 1}, new int[]{43136, 43137, 1}, new int[]{43188, 43204, 1}, new int[]{43232, 43249, 1}, new int[]{43302, 43309, 1}, new int[]{43335, 43347, 1}, new int[]{43392, 43395, 1}, new int[]{43443, 43456, 1}, new int[]{43561, 43574, 1}, new int[]{43587, 43596, 9}, new int[]{43597, 43643, 46}, new int[]{43696, 43698, 2}, new int[]{43699, 43700, 1}, new int[]{43703, 43704, 1}, new int[]{43710, 43711, 1}, new int[]{43713, 44003, 290}, new int[]{44004, 44010, 1}, new int[]{44012, 44013, 1}, new int[]{64286, 65024, 738}, new int[]{65025, 65039, 1}, new int[]{65056, 65062, 1}, new int[]{66045, 68097, 2052}, new int[]{68098, 68099, 1}, new int[]{68101, 68102, 1}, new int[]{68108, 68111, 1}, new int[]{68152, 68154, 1}, new int[]{68159, 69632, 1473}, new int[]{69633, 69634, 1}, new int[]{69688, 69702, 1}, new int[]{69760, 69762, 1}, new int[]{69808, 69818, 1}, new int[]{119141, 119145, 1}, new int[]{119149, 119154, 1}, new int[]{119163, 119170, 1}, new int[]{119173, 119179, 1}, new int[]{119210, 119213, 1}, new int[]{119362, 119364, 1}, new int[]{917760, 917999, 1}};
    }

    private static int[][] make_N() {
        return new int[][]{new int[]{48, 57, 1}, new int[]{178, 179, 1}, new int[]{185, 188, 3}, new int[]{189, 190, 1}, new int[]{1632, 1641, 1}, new int[]{1776, 1785, 1}, new int[]{1984, 1993, 1}, new int[]{2406, 2415, 1}, new int[]{2534, 2543, 1}, new int[]{2548, 2553, 1}, new int[]{2662, 2671, 1}, new int[]{2790, 2799, 1}, new int[]{2918, 2927, 1}, new int[]{2930, 2935, 1}, new int[]{3046, 3058, 1}, new int[]{3174, 3183, 1}, new int[]{3192, 3198, 1}, new int[]{3302, 3311, 1}, new int[]{3430, 3445, 1}, new int[]{3664, 3673, 1}, new int[]{3792, 3801, 1}, new int[]{3872, 3891, 1}, new int[]{4160, 4169, 1}, new int[]{4240, 4249, 1}, new int[]{4969, 4988, 1}, new int[]{5870, 5872, 1}, new int[]{6112, 6121, 1}, new int[]{6128, 6137, 1}, new int[]{6160, 6169, 1}, new int[]{6470, 6479, 1}, new int[]{6608, 6618, 1}, new int[]{6784, 6793, 1}, new int[]{6800, 6809, 1}, new int[]{6992, 7001, 1}, new int[]{7088, 7097, 1}, new int[]{7232, 7241, 1}, new int[]{7248, 7257, 1}, new int[]{8304, 8308, 4}, new int[]{8309, 8313, 1}, new int[]{8320, 8329, 1}, new int[]{8528, 8578, 1}, new int[]{8581, 8585, 1}, new int[]{9312, 9371, 1}, new int[]{9450, 9471, 1}, new int[]{10102, 10131, 1}, new int[]{11517, 12295, 778}, new int[]{12321, 12329, 1}, new int[]{12344, 12346, 1}, new int[]{12690, 12693, 1}, new int[]{12832, 12841, 1}, new int[]{12881, 12895, 1}, new int[]{12928, 12937, 1}, new int[]{12977, 12991, 1}, new int[]{42528, 42537, 1}, new int[]{42726, 42735, 1}, new int[]{43056, 43061, 1}, new int[]{43216, 43225, 1}, new int[]{43264, 43273, 1}, new int[]{43472, 43481, 1}, new int[]{43600, 43609, 1}, new int[]{44016, 44025, 1}, new int[]{65296, 65305, 1}, new int[]{65799, 65843, 1}, new int[]{65856, 65912, 1}, new int[]{65930, 66336, 406}, new int[]{66337, 66339, 1}, new int[]{66369, 66378, 9}, new int[]{66513, 66517, 1}, new int[]{66720, 66729, 1}, new int[]{67672, 67679, 1}, new int[]{67862, 67867, 1}, new int[]{68160, 68167, 1}, new int[]{68221, 68222, 1}, new int[]{68440, 68447, 1}, new int[]{68472, 68479, 1}, new int[]{69216, 69246, 1}, new int[]{69714, 69743, 1}, new int[]{74752, 74850, 1}, new int[]{119648, 119665, 1}, new int[]{120782, 120831, 1}, new int[]{127232, 127242, 1}};
    }

    private static int[][] make_Common() {
        return new int[][]{new int[]{0, 64, 1}, new int[]{91, 96, 1}, new int[]{123, 169, 1}, new int[]{171, 185, 1}, new int[]{187, 191, 1}, new int[]{215, 247, 32}, new int[]{697, 735, 1}, new int[]{741, 745, 1}, new int[]{748, 767, 1}, new int[]{884, 894, 10}, new int[]{TypedValues.Custom.TYPE_FLOAT, TypedValues.Custom.TYPE_STRING, 2}, new int[]{1417, 1548, 131}, new int[]{1563, 1567, 4}, new int[]{1600, 1632, 32}, new int[]{1633, 1641, 1}, new int[]{1757, 2404, 647}, new int[]{2405, 2416, 11}, new int[]{3647, 4053, 406}, new int[]{4054, 4056, 1}, new int[]{4347, 5867, 1520}, new int[]{5868, 5869, 1}, new int[]{5941, 5942, 1}, new int[]{6146, 6147, 1}, new int[]{6149, 7379, 1230}, new int[]{7393, 7401, 8}, new int[]{7402, 7404, 1}, new int[]{7406, 7410, 1}, new int[]{8192, 8203, 1}, new int[]{8206, 8292, 1}, new int[]{8298, 8304, 1}, new int[]{8308, 8318, 1}, new int[]{8320, 8334, 1}, new int[]{8352, 8377, 1}, new int[]{8448, 8485, 1}, new int[]{8487, 8489, 1}, new int[]{8492, 8497, 1}, new int[]{8499, 8525, 1}, new int[]{8527, 8543, 1}, new int[]{8585, 8592, 7}, new int[]{8593, 9203, 1}, new int[]{9216, 9254, 1}, new int[]{9280, 9290, 1}, new int[]{9312, 9983, 1}, new int[]{9985, 10186, 1}, new int[]{10188, 10190, 2}, new int[]{10191, 10239, 1}, new int[]{10496, 11084, 1}, new int[]{11088, 11097, 1}, new int[]{11776, 11825, 1}, new int[]{12272, 12283, 1}, new int[]{12288, 12292, 1}, new int[]{12294, 12296, 2}, new int[]{12297, 12320, 1}, new int[]{12336, 12343, 1}, new int[]{12348, 12351, 1}, new int[]{12443, 12444, 1}, new int[]{12448, 12539, 91}, new int[]{12540, 12688, 148}, new int[]{12689, 12703, 1}, new int[]{12736, 12771, 1}, new int[]{12832, 12895, 1}, new int[]{12927, 13007, 1}, new int[]{13144, 13311, 1}, new int[]{19904, 19967, 1}, new int[]{42752, 42785, 1}, new int[]{42888, 42890, 1}, new int[]{43056, 43065, 1}, new int[]{64830, 64831, 1}, new int[]{65021, 65040, 19}, new int[]{65041, 65049, 1}, new int[]{65072, 65106, 1}, new int[]{65108, 65126, 1}, new int[]{65128, 65131, 1}, new int[]{65279, 65281, 2}, new int[]{65282, 65312, 1}, new int[]{65339, 65344, 1}, new int[]{65371, 65381, 1}, new int[]{65392, 65438, 46}, new int[]{65439, 65504, 65}, new int[]{65505, 65510, 1}, new int[]{65512, 65518, 1}, new int[]{65529, Utf8.REPLACEMENT_CODE_POINT, 1}, new int[]{65792, 65794, 1}, new int[]{65799, 65843, 1}, new int[]{65847, 65855, 1}, new int[]{65936, 65947, 1}, new int[]{66000, 66044, 1}, new int[]{118784, 119029, 1}, new int[]{119040, 119078, 1}, new int[]{119081, 119142, 1}, new int[]{119146, 119162, 1}, new int[]{119171, 119172, 1}, new int[]{119180, 119209, 1}, new int[]{119214, 119261, 1}, new int[]{119552, 119638, 1}, new int[]{119648, 119665, 1}, new int[]{119808, 119892, 1}, new int[]{119894, 119964, 1}, new int[]{119966, 119967, 1}, new int[]{119970, 119973, 3}, new int[]{119974, 119977, 3}, new int[]{119978, 119980, 1}, new int[]{119982, 119993, 1}, new int[]{119995, 119997, 2}, new int[]{119998, 120003, 1}, new int[]{120005, 120069, 1}, new int[]{120071, 120074, 1}, new int[]{120077, 120084, 1}, new int[]{120086, 120092, 1}, new int[]{120094, 120121, 1}, new int[]{120123, 120126, 1}, new int[]{120128, 120132, 1}, new int[]{120134, 120138, 4}, new int[]{120139, 120144, 1}, new int[]{120146, 120485, 1}, new int[]{120488, 120779, 1}, new int[]{120782, 120831, 1}, new int[]{126976, 127019, 1}, new int[]{127024, 127123, 1}, new int[]{127136, 127150, 1}, new int[]{127153, 127166, 1}, new int[]{127169, 127183, 1}, new int[]{127185, 127199, 1}, new int[]{127232, 127242, 1}, new int[]{127248, 127278, 1}, new int[]{127280, 127337, 1}, new int[]{127344, 127386, 1}, new int[]{127462, 127487, 1}, new int[]{127489, 127490, 1}, new int[]{127504, 127546, 1}, new int[]{127552, 127560, 1}, new int[]{127568, 127569, 1}, new int[]{127744, 127776, 1}, new int[]{127792, 127797, 1}, new int[]{127799, 127868, 1}, new int[]{127872, 127891, 1}, new int[]{127904, 127940, 1}, new int[]{127942, 127946, 1}, new int[]{127968, 127984, 1}, new int[]{128000, 128062, 1}, new int[]{128064, 128066, 2}, new int[]{128067, 128247, 1}, new int[]{128249, 128252, 1}, new int[]{128256, 128317, 1}, new int[]{128336, 128359, 1}, new int[]{128507, 128511, 1}, new int[]{128513, 128528, 1}, new int[]{128530, 128532, 1}, new int[]{128534, 128540, 2}, new int[]{128541, 128542, 1}, new int[]{128544, 128549, 1}, new int[]{128552, 128555, 1}, new int[]{128557, 128560, 3}, new int[]{128561, 128563, 1}, new int[]{128565, 128576, 1}, new int[]{128581, 128591, 1}, new int[]{128640, 128709, 1}, new int[]{128768, 128883, 1}, new int[]{917505, 917536, 31}, new int[]{917537, 917631, 1}};
    }

    private static int[][] make_Inherited() {
        return new int[][]{new int[]{ViewUtils.EDGE_TO_EDGE_FLAGS, 879, 1}, new int[]{1157, 1158, 1}, new int[]{1611, 1621, 1}, new int[]{1631, 1648, 17}, new int[]{2385, 2386, 1}, new int[]{7376, 7378, 1}, new int[]{7380, 7392, 1}, new int[]{7394, 7400, 1}, new int[]{7405, 7616, 211}, new int[]{7617, 7654, 1}, new int[]{7676, 7679, 1}, new int[]{8204, 8205, 1}, new int[]{8400, 8432, 1}, new int[]{12330, 12333, 1}, new int[]{12441, 12442, 1}, new int[]{65024, 65039, 1}, new int[]{65056, 65062, 1}, new int[]{66045, 119143, 53098}, new int[]{119144, 119145, 1}, new int[]{119163, 119170, 1}, new int[]{119173, 119179, 1}, new int[]{119210, 119213, 1}, new int[]{917760, 917999, 1}};
    }

    private static int[][] make_Bamum() {
        return new int[][]{new int[]{42656, 42743, 1}, new int[]{92160, 92728, 1}};
    }

    private static int[][] make_Arabic() {
        return new int[][]{new int[]{1536, 1539, 1}, new int[]{1542, 1547, 1}, new int[]{1549, 1562, 1}, new int[]{1566, 1568, 2}, new int[]{1569, 1599, 1}, new int[]{1601, 1610, 1}, new int[]{1622, 1630, 1}, new int[]{1642, 1647, 1}, new int[]{1649, 1756, 1}, new int[]{1758, 1791, 1}, new int[]{1872, 1919, 1}, new int[]{64336, 64449, 1}, new int[]{64467, 64829, 1}, new int[]{64848, 64911, 1}, new int[]{64914, 64967, 1}, new int[]{65008, 65020, 1}, new int[]{65136, 65140, 1}, new int[]{65142, 65276, 1}, new int[]{69216, 69246, 1}};
    }

    private static int[][] make_Lisu() {
        return new int[][]{new int[]{42192, 42239, 1}};
    }

    private static int[][] make_Armenian() {
        return new int[][]{new int[]{1329, 1366, 1}, new int[]{1369, 1375, 1}, new int[]{1377, 1415, 1}, new int[]{1418, 64275, 62857}, new int[]{64276, 64279, 1}};
    }

    private static int[][] make_Bengali() {
        return new int[][]{new int[]{2433, 2435, 1}, new int[]{2437, 2444, 1}, new int[]{2447, 2448, 1}, new int[]{2451, 2472, 1}, new int[]{2474, 2480, 1}, new int[]{2482, 2486, 4}, new int[]{2487, 2489, 1}, new int[]{2492, 2500, 1}, new int[]{2503, 2504, 1}, new int[]{2507, 2510, 1}, new int[]{2519, 2524, 5}, new int[]{2525, 2527, 2}, new int[]{2528, 2531, 1}, new int[]{2534, 2555, 1}};
    }

    private static int[][] make_Old_South_Arabian() {
        return new int[][]{new int[]{68192, 68223, 1}};
    }

    private static int[][] make_Bopomofo() {
        return new int[][]{new int[]{746, 747, 1}, new int[]{12549, 12589, 1}, new int[]{12704, 12730, 1}};
    }

    private static int[][] make_Cherokee() {
        return new int[][]{new int[]{5024, 5108, 1}};
    }

    private static int[][] make_Coptic() {
        return new int[][]{new int[]{994, PointerIconCompat.TYPE_CROSSHAIR, 1}, new int[]{11392, 11505, 1}, new int[]{11513, 11519, 1}};
    }

    private static int[][] make_Cyrillic() {
        return new int[][]{new int[]{1024, 1156, 1}, new int[]{1159, 1319, 1}, new int[]{7467, 7544, 77}, new int[]{11744, 11775, 1}, new int[]{42560, 42611, 1}, new int[]{42620, 42647, 1}};
    }

    private static int[][] make_Deseret() {
        return new int[][]{new int[]{66560, 66639, 1}};
    }

    private static int[][] make_Devanagari() {
        return new int[][]{new int[]{2304, 2384, 1}, new int[]{2387, 2403, 1}, new int[]{2406, 2415, 1}, new int[]{2417, 2423, 1}, new int[]{2425, 2431, 1}, new int[]{43232, 43259, 1}};
    }

    private static int[][] make_Ethiopic() {
        return new int[][]{new int[]{4608, 4680, 1}, new int[]{4682, 4685, 1}, new int[]{4688, 4694, 1}, new int[]{4696, 4698, 2}, new int[]{4699, 4701, 1}, new int[]{4704, 4744, 1}, new int[]{4746, 4749, 1}, new int[]{4752, 4784, 1}, new int[]{4786, 4789, 1}, new int[]{4792, 4798, 1}, new int[]{4800, 4802, 2}, new int[]{4803, 4805, 1}, new int[]{4808, 4822, 1}, new int[]{4824, 4880, 1}, new int[]{4882, 4885, 1}, new int[]{4888, 4954, 1}, new int[]{4957, 4988, 1}, new int[]{4992, 5017, 1}, new int[]{11648, 11670, 1}, new int[]{11680, 11686, 1}, new int[]{11688, 11694, 1}, new int[]{11696, 11702, 1}, new int[]{11704, 11710, 1}, new int[]{11712, 11718, 1}, new int[]{11720, 11726, 1}, new int[]{11728, 11734, 1}, new int[]{11736, 11742, 1}, new int[]{43777, 43782, 1}, new int[]{43785, 43790, 1}, new int[]{43793, 43798, 1}, new int[]{43808, 43814, 1}, new int[]{43816, 43822, 1}};
    }

    private static int[][] make_Georgian() {
        return new int[][]{new int[]{4256, 4293, 1}, new int[]{4304, 4346, 1}, new int[]{4348, 11520, 7172}, new int[]{11521, 11557, 1}};
    }

    private static int[][] make_Gothic() {
        return new int[][]{new int[]{66352, 66378, 1}};
    }

    private static int[][] make_Greek() {
        return new int[][]{new int[]{880, 883, 1}, new int[]{885, 887, 1}, new int[]{890, 893, 1}, new int[]{900, TypedValues.Custom.TYPE_BOOLEAN, 2}, new int[]{TypedValues.Custom.TYPE_DIMENSION, TypedValues.Custom.TYPE_REFERENCE, 1}, new int[]{908, 910, 2}, new int[]{911, PDF417Common.NUMBER_OF_CODEWORDS, 1}, new int[]{931, 993, 1}, new int[]{PointerIconCompat.TYPE_TEXT, 1023, 1}, new int[]{7462, 7466, 1}, new int[]{7517, 7521, 1}, new int[]{7526, 7530, 1}, new int[]{7615, 7936, 321}, new int[]{7937, 7957, 1}, new int[]{7960, 7965, 1}, new int[]{7968, 8005, 1}, new int[]{8008, 8013, 1}, new int[]{8016, 8023, 1}, new int[]{8025, 8031, 2}, new int[]{8032, 8061, 1}, new int[]{8064, 8116, 1}, new int[]{8118, 8132, 1}, new int[]{8134, 8147, 1}, new int[]{8150, 8155, 1}, new int[]{8157, 8175, 1}, new int[]{8178, 8180, 1}, new int[]{8182, 8190, 1}, new int[]{8486, 65856, 57370}, new int[]{65857, 65930, 1}, new int[]{119296, 119365, 1}};
    }

    private static int[][] make_Gujarati() {
        return new int[][]{new int[]{2689, 2691, 1}, new int[]{2693, 2701, 1}, new int[]{2703, 2705, 1}, new int[]{2707, 2728, 1}, new int[]{2730, 2736, 1}, new int[]{2738, 2739, 1}, new int[]{2741, 2745, 1}, new int[]{2748, 2757, 1}, new int[]{2759, 2761, 1}, new int[]{2763, 2765, 1}, new int[]{2768, 2784, 16}, new int[]{2785, 2787, 1}, new int[]{2790, 2799, 1}, new int[]{2801, 2801, 1}};
    }

    private static int[][] make_Gurmukhi() {
        return new int[][]{new int[]{2561, 2563, 1}, new int[]{2565, 2570, 1}, new int[]{2575, 2576, 1}, new int[]{2579, 2600, 1}, new int[]{2602, 2608, 1}, new int[]{2610, 2611, 1}, new int[]{2613, 2614, 1}, new int[]{2616, 2617, 1}, new int[]{2620, 2622, 2}, new int[]{2623, 2626, 1}, new int[]{2631, 2632, 1}, new int[]{2635, 2637, 1}, new int[]{2641, 2649, 8}, new int[]{2650, 2652, 1}, new int[]{2654, 2662, 8}, new int[]{2663, 2677, 1}};
    }

    private static int[][] make_Han() {
        return new int[][]{new int[]{11904, 11929, 1}, new int[]{11931, 12019, 1}, new int[]{12032, 12245, 1}, new int[]{12293, 12295, 2}, new int[]{12321, 12329, 1}, new int[]{12344, 12347, 1}, new int[]{13312, 19893, 1}, new int[]{19968, 40907, 1}, new int[]{63744, 64045, 1}, new int[]{64048, 64109, 1}, new int[]{64112, 64217, 1}, new int[]{131072, 173782, 1}, new int[]{173824, 177972, 1}, new int[]{177984, 178205, 1}, new int[]{194560, 195101, 1}};
    }

    private static int[][] make_Hangul() {
        return new int[][]{new int[]{4352, 4607, 1}, new int[]{12334, 12335, 1}, new int[]{12593, 12686, 1}, new int[]{12800, 12830, 1}, new int[]{12896, 12926, 1}, new int[]{43360, 43388, 1}, new int[]{44032, 55203, 1}, new int[]{55216, 55238, 1}, new int[]{55243, 55291, 1}, new int[]{65440, 65470, 1}, new int[]{65474, 65479, 1}, new int[]{65482, 65487, 1}, new int[]{65490, 65495, 1}, new int[]{65498, 65500, 1}};
    }

    private static int[][] make_Hebrew() {
        return new int[][]{new int[]{1425, 1479, 1}, new int[]{1488, 1514, 1}, new int[]{1520, 1524, 1}, new int[]{64285, 64310, 1}, new int[]{64312, 64316, 1}, new int[]{64318, 64320, 2}, new int[]{64321, 64323, 2}, new int[]{64324, 64326, 2}, new int[]{64327, 64335, 1}};
    }

    private static int[][] make_Hiragana() {
        return new int[][]{new int[]{12353, 12438, 1}, new int[]{GLUtils.EGL_GL_COLORSPACE_KHR, 12447, 1}, new int[]{110593, 127488, 16895}};
    }

    private static int[][] make_Kannada() {
        return new int[][]{new int[]{3202, 3203, 1}, new int[]{3205, 3212, 1}, new int[]{3214, 3216, 1}, new int[]{3218, 3240, 1}, new int[]{3242, 3251, 1}, new int[]{3253, 3257, 1}, new int[]{3260, 3268, 1}, new int[]{3270, 3272, 1}, new int[]{3274, 3277, 1}, new int[]{3285, 3286, 1}, new int[]{3294, 3296, 2}, new int[]{3297, 3299, 1}, new int[]{3302, 3311, 1}, new int[]{3313, 3314, 1}};
    }

    private static int[][] make_Katakana() {
        return new int[][]{new int[]{12449, 12538, 1}, new int[]{12541, 12543, 1}, new int[]{12784, 12799, 1}, new int[]{13008, 13054, 1}, new int[]{13056, 13143, 1}, new int[]{65382, 65391, 1}, new int[]{65393, 65437, 1}, new int[]{110592, 110592, 1}};
    }

    private static int[][] make_Khmer() {
        return new int[][]{new int[]{6016, 6109, 1}, new int[]{6112, 6121, 1}, new int[]{6128, 6137, 1}, new int[]{6624, 6655, 1}};
    }

    private static int[][] make_Lao() {
        return new int[][]{new int[]{3713, 3714, 1}, new int[]{3716, 3719, 3}, new int[]{3720, 3722, 2}, new int[]{3725, 3732, 7}, new int[]{3733, 3735, 1}, new int[]{3737, 3743, 1}, new int[]{3745, 3747, 1}, new int[]{3749, 3751, 2}, new int[]{3754, 3755, 1}, new int[]{3757, 3769, 1}, new int[]{3771, 3773, 1}, new int[]{3776, 3780, 1}, new int[]{3782, 3784, 2}, new int[]{3785, 3789, 1}, new int[]{3792, 3801, 1}, new int[]{3804, 3805, 1}};
    }

    private static int[][] make_Latin() {
        return new int[][]{new int[]{65, 90, 1}, new int[]{97, 122, 1}, new int[]{170, 186, 16}, new int[]{192, 214, 1}, new int[]{216, 246, 1}, new int[]{248, 696, 1}, new int[]{736, 740, 1}, new int[]{7424, 7461, 1}, new int[]{7468, 7516, 1}, new int[]{7522, 7525, 1}, new int[]{7531, 7543, 1}, new int[]{7545, 7614, 1}, new int[]{7680, 7935, 1}, new int[]{8305, 8319, 14}, new int[]{8336, 8348, 1}, new int[]{8490, 8491, 1}, new int[]{8498, 8526, 28}, new int[]{8544, 8584, 1}, new int[]{11360, 11391, 1}, new int[]{42786, 42887, 1}, new int[]{42891, 42894, 1}, new int[]{42896, 42897, 1}, new int[]{42912, 42921, 1}, new int[]{43002, 43007, 1}, new int[]{64256, 64262, 1}, new int[]{65313, 65338, 1}, new int[]{65345, 65370, 1}};
    }

    private static int[][] make_Malayalam() {
        return new int[][]{new int[]{3330, 3331, 1}, new int[]{3333, 3340, 1}, new int[]{3342, 3344, 1}, new int[]{3346, 3386, 1}, new int[]{3389, 3396, 1}, new int[]{3398, 3400, 1}, new int[]{3402, 3406, 1}, new int[]{3415, 3424, 9}, new int[]{3425, 3427, 1}, new int[]{3430, 3445, 1}, new int[]{3449, 3455, 1}};
    }

    private static int[][] make_Mongolian() {
        return new int[][]{new int[]{6144, 6145, 1}, new int[]{6148, 6150, 2}, new int[]{6151, 6158, 1}, new int[]{6160, 6169, 1}, new int[]{6176, 6263, 1}, new int[]{6272, 6314, 1}};
    }

    private static int[][] make_Myanmar() {
        return new int[][]{new int[]{4096, 4255, 1}, new int[]{43616, 43643, 1}};
    }

    private static int[][] make_Ogham() {
        return new int[][]{new int[]{5760, 5788, 1}};
    }

    private static int[][] make_Old_Italic() {
        return new int[][]{new int[]{66304, 66334, 1}, new int[]{66336, 66339, 1}};
    }

    private static int[][] make_Oriya() {
        return new int[][]{new int[]{2817, 2819, 1}, new int[]{2821, 2828, 1}, new int[]{2831, 2832, 1}, new int[]{2835, 2856, 1}, new int[]{2858, 2864, 1}, new int[]{2866, 2867, 1}, new int[]{2869, 2873, 1}, new int[]{2876, 2884, 1}, new int[]{2887, 2888, 1}, new int[]{2891, 2893, 1}, new int[]{2902, 2903, 1}, new int[]{2908, 2909, 1}, new int[]{2911, 2915, 1}, new int[]{2918, 2935, 1}};
    }

    private static int[][] make_Runic() {
        return new int[][]{new int[]{5792, 5866, 1}, new int[]{5870, 5872, 1}};
    }

    private static int[][] make_Sinhala() {
        return new int[][]{new int[]{3458, 3459, 1}, new int[]{3461, 3478, 1}, new int[]{3482, 3505, 1}, new int[]{3507, 3515, 1}, new int[]{3517, 3520, 3}, new int[]{3521, 3526, 1}, new int[]{3530, 3535, 5}, new int[]{3536, 3540, 1}, new int[]{3542, 3544, 2}, new int[]{3545, 3551, 1}, new int[]{3570, 3572, 1}};
    }

    private static int[][] make_Syriac() {
        return new int[][]{new int[]{1792, 1805, 1}, new int[]{1807, 1866, 1}, new int[]{1869, 1871, 1}};
    }

    private static int[][] make_Tamil() {
        return new int[][]{new int[]{2946, 2947, 1}, new int[]{2949, 2954, 1}, new int[]{2958, 2960, 1}, new int[]{2962, 2965, 1}, new int[]{2969, 2970, 1}, new int[]{2972, 2974, 2}, new int[]{2975, 2979, 4}, new int[]{2980, 2984, 4}, new int[]{2985, 2986, 1}, new int[]{2990, AuthApiStatusCodes.AUTH_API_ACCESS_FORBIDDEN, 1}, new int[]{AuthApiStatusCodes.AUTH_APP_CERT_ERROR, 3010, 1}, new int[]{3014, 3016, 1}, new int[]{3018, 3021, 1}, new int[]{3024, 3031, 7}, new int[]{3046, 3066, 1}};
    }

    private static int[][] make_Telugu() {
        return new int[][]{new int[]{3073, 3075, 1}, new int[]{3077, 3084, 1}, new int[]{3086, 3088, 1}, new int[]{3090, 3112, 1}, new int[]{3114, 3123, 1}, new int[]{3125, 3129, 1}, new int[]{3133, 3140, 1}, new int[]{3142, 3144, 1}, new int[]{3146, 3149, 1}, new int[]{3157, 3158, 1}, new int[]{3160, 3161, 1}, new int[]{3168, 3171, 1}, new int[]{3174, 3183, 1}, new int[]{3192, 3199, 1}};
    }

    private static int[][] make_Thaana() {
        return new int[][]{new int[]{1920, 1969, 1}};
    }

    private static int[][] make_Thai() {
        return new int[][]{new int[]{3585, 3642, 1}, new int[]{3648, 3675, 1}};
    }

    private static int[][] make_Tibetan() {
        return new int[][]{new int[]{3840, 3911, 1}, new int[]{3913, 3948, 1}, new int[]{3953, 3991, 1}, new int[]{3993, 4028, 1}, new int[]{4030, 4044, 1}, new int[]{4046, 4052, 1}, new int[]{4057, 4058, 1}};
    }

    private static int[][] make_Canadian_Aboriginal() {
        return new int[][]{new int[]{5120, 5759, 1}, new int[]{6320, 6389, 1}};
    }

    private static int[][] make_Yi() {
        return new int[][]{new int[]{40960, 42124, 1}, new int[]{42128, 42182, 1}};
    }

    private static int[][] make_Tagalog() {
        return new int[][]{new int[]{5888, 5900, 1}, new int[]{5902, 5908, 1}};
    }

    private static int[][] make_Hanunoo() {
        return new int[][]{new int[]{5920, 5940, 1}};
    }

    private static int[][] make_Buhid() {
        return new int[][]{new int[]{5952, 5971, 1}};
    }

    private static int[][] make_Tagbanwa() {
        return new int[][]{new int[]{5984, 5996, 1}, new int[]{5998, 6000, 1}, new int[]{6002, 6003, 1}};
    }

    private static int[][] make_Braille() {
        return new int[][]{new int[]{10240, 10495, 1}};
    }

    private static int[][] make_Cypriot() {
        return new int[][]{new int[]{67584, 67589, 1}, new int[]{67592, 67594, 2}, new int[]{67595, 67637, 1}, new int[]{67639, 67640, 1}, new int[]{67644, 67647, 3}};
    }

    private static int[][] make_Limbu() {
        return new int[][]{new int[]{6400, 6428, 1}, new int[]{6432, 6443, 1}, new int[]{6448, 6459, 1}, new int[]{6464, 6468, 4}, new int[]{6469, 6479, 1}};
    }

    private static int[][] make_Linear_B() {
        return new int[][]{new int[]{65536, 65547, 1}, new int[]{65549, 65574, 1}, new int[]{65576, 65594, 1}, new int[]{65596, 65597, 1}, new int[]{65599, 65613, 1}, new int[]{65616, 65629, 1}, new int[]{65664, 65786, 1}};
    }

    private static int[][] make_Osmanya() {
        return new int[][]{new int[]{66688, 66717, 1}, new int[]{66720, 66729, 1}};
    }

    private static int[][] make_Shavian() {
        return new int[][]{new int[]{66640, 66687, 1}};
    }

    private static int[][] make_Tai_Le() {
        return new int[][]{new int[]{6480, 6509, 1}, new int[]{6512, 6516, 1}};
    }

    private static int[][] make_Ugaritic() {
        return new int[][]{new int[]{66432, 66461, 1}, new int[]{66463, 66463, 1}};
    }

    private static int[][] make_Buginese() {
        return new int[][]{new int[]{6656, 6683, 1}, new int[]{6686, 6687, 1}};
    }

    private static int[][] make_Glagolitic() {
        return new int[][]{new int[]{11264, 11310, 1}, new int[]{11312, 11358, 1}};
    }

    private static int[][] make_Kharoshthi() {
        return new int[][]{new int[]{68096, 68099, 1}, new int[]{68101, 68102, 1}, new int[]{68108, 68115, 1}, new int[]{68117, 68119, 1}, new int[]{68121, 68147, 1}, new int[]{68152, 68154, 1}, new int[]{68159, 68167, 1}, new int[]{68176, 68184, 1}};
    }

    private static int[][] make_Syloti_Nagri() {
        return new int[][]{new int[]{43008, 43051, 1}};
    }

    private static int[][] make_New_Tai_Lue() {
        return new int[][]{new int[]{6528, 6571, 1}, new int[]{6576, 6601, 1}, new int[]{6608, 6618, 1}, new int[]{6622, 6623, 1}};
    }

    private static int[][] make_Tifinagh() {
        return new int[][]{new int[]{11568, 11621, 1}, new int[]{11631, 11632, 1}, new int[]{11647, 11647, 1}};
    }

    private static int[][] make_Old_Persian() {
        return new int[][]{new int[]{66464, 66499, 1}, new int[]{66504, 66517, 1}};
    }

    private static int[][] make_Balinese() {
        return new int[][]{new int[]{6912, 6987, 1}, new int[]{6992, 7036, 1}};
    }

    private static int[][] make_Batak() {
        return new int[][]{new int[]{7104, 7155, 1}, new int[]{7164, 7167, 1}};
    }

    private static int[][] make_Brahmi() {
        return new int[][]{new int[]{69632, 69709, 1}, new int[]{69714, 69743, 1}};
    }

    private static int[][] make_Cham() {
        return new int[][]{new int[]{43520, 43574, 1}, new int[]{43584, 43597, 1}, new int[]{43600, 43609, 1}, new int[]{43612, 43615, 1}};
    }

    private static int[][] make_Egyptian_Hieroglyphs() {
        return new int[][]{new int[]{77824, 78894, 1}};
    }

    private static int[][] make_Javanese() {
        return new int[][]{new int[]{43392, 43469, 1}, new int[]{43471, 43481, 1}, new int[]{43486, 43487, 1}};
    }

    private static int[][] make_Kayah_Li() {
        return new int[][]{new int[]{43264, 43311, 1}};
    }

    private static int[][] make_Lepcha() {
        return new int[][]{new int[]{7168, 7223, 1}, new int[]{7227, 7241, 1}, new int[]{7245, 7247, 1}};
    }

    private static int[][] make_Mandaic() {
        return new int[][]{new int[]{2112, 2139, 1}, new int[]{2142, 2142, 1}};
    }

    private static int[][] make_Nko() {
        return new int[][]{new int[]{1984, 2042, 1}};
    }

    private static int[][] make_Old_Turkic() {
        return new int[][]{new int[]{68608, 68680, 1}};
    }

    private static int[][] make_Phags_Pa() {
        return new int[][]{new int[]{43072, 43127, 1}};
    }

    private static int[][] make_Phoenician() {
        return new int[][]{new int[]{67840, 67867, 1}, new int[]{67871, 67871, 1}};
    }

    private static int[][] make_Vai() {
        return new int[][]{new int[]{42240, 42539, 1}};
    }

    private static int[][] make_Cuneiform() {
        return new int[][]{new int[]{73728, 74606, 1}, new int[]{74752, 74850, 1}, new int[]{74864, 74867, 1}};
    }

    private static int[][] make_Unknown() {
        return new int[][]{new int[]{55296, 63743, 1}, new int[]{983040, 1048573, 1}, new int[]{1048576, 1114109, 1}};
    }

    private static int[][] make_Carian() {
        return new int[][]{new int[]{66208, 66256, 1}};
    }

    private static int[][] make_Tai_Tham() {
        return new int[][]{new int[]{6688, 6750, 1}, new int[]{6752, 6780, 1}, new int[]{6783, 6793, 1}, new int[]{6800, 6809, 1}, new int[]{6816, 6829, 1}};
    }

    private static int[][] make_Lycian() {
        return new int[][]{new int[]{66176, 66204, 1}};
    }

    private static int[][] make_Lydian() {
        return new int[][]{new int[]{67872, 67897, 1}, new int[]{67903, 67903, 1}};
    }

    private static int[][] make_Ol_Chiki() {
        return new int[][]{new int[]{7248, 7295, 1}};
    }

    private static int[][] make_Rejang() {
        return new int[][]{new int[]{43312, 43347, 1}, new int[]{43359, 43359, 1}};
    }

    private static int[][] make_Saurashtra() {
        return new int[][]{new int[]{43136, 43204, 1}, new int[]{43214, 43225, 1}};
    }

    private static int[][] make_Sundanese() {
        return new int[][]{new int[]{7040, 7082, 1}, new int[]{7086, 7097, 1}};
    }

    private static int[][] make_Meetei_Mayek() {
        return new int[][]{new int[]{43968, 44013, 1}, new int[]{44016, 44025, 1}};
    }

    private static int[][] make_Imperial_Aramaic() {
        return new int[][]{new int[]{67648, 67669, 1}, new int[]{67671, 67679, 1}};
    }

    private static int[][] make_Avestan() {
        return new int[][]{new int[]{68352, 68405, 1}, new int[]{68409, 68415, 1}};
    }

    private static int[][] make_Kaithi() {
        return new int[][]{new int[]{69760, 69825, 1}};
    }

    private static int[][] make_Inscriptional_Pahlavi() {
        return new int[][]{new int[]{68448, 68466, 1}, new int[]{68472, 68479, 1}};
    }

    private static int[][] make_Inscriptional_Parthian() {
        return new int[][]{new int[]{68416, 68437, 1}, new int[]{68440, 68447, 1}};
    }

    private static int[][] make_Samaritan() {
        return new int[][]{new int[]{2048, 2093, 1}, new int[]{2096, 2110, 1}};
    }

    private static int[][] make_Tai_Viet() {
        return new int[][]{new int[]{43648, 43714, 1}, new int[]{43739, 43743, 1}};
    }

    private static Map<String, int[][]> Categories() {
        HashMap map = new HashMap();
        map.put("Lu", Lu);
        map.put("Ll", Ll);
        map.put("Lt", Lt);
        map.put("Lm", Lm);
        map.put("Lo", Lo);
        map.put("Mn", Mn);
        map.put("Me", Me);
        map.put("Mc", Mc);
        map.put("Nd", Nd);
        map.put("Nl", Nl);
        map.put("No", No);
        map.put("Zs", Zs);
        map.put("Zl", Zl);
        map.put("Zp", Zp);
        map.put("Cc", Cc);
        map.put("Cf", Cf);
        map.put("Co", Co);
        map.put("Cs", Cs);
        map.put("Pd", Pd);
        map.put("Ps", Ps);
        map.put("Pe", Pe);
        map.put("Pc", Pc);
        map.put("Po", Po);
        map.put("Sm", Sm);
        map.put("Sc", Sc);
        map.put("Sk", Sk);
        map.put("So", So);
        map.put("Pi", Pi);
        map.put("Pf", Pf);
        map.put("P", P);
        map.put(ExifInterface.LATITUDE_SOUTH, S);
        map.put("C", C);
        map.put("Z", Z);
        map.put("L", L);
        map.put("M", M);
        map.put("N", N);
        return Collections.unmodifiableMap(map);
    }

    private static Map<String, int[][]> Scripts() {
        HashMap map = new HashMap();
        map.put("Common", Common);
        map.put("Inherited", Inherited);
        map.put("Bamum", Bamum);
        map.put("Arabic", Arabic);
        map.put("Lisu", Lisu);
        map.put("Armenian", Armenian);
        map.put("Bengali", Bengali);
        map.put("Old_South_Arabian", Old_South_Arabian);
        map.put("Bopomofo", Bopomofo);
        map.put("Cherokee", Cherokee);
        map.put("Coptic", Coptic);
        map.put("Cyrillic", Cyrillic);
        map.put("Deseret", Deseret);
        map.put("Devanagari", Devanagari);
        map.put("Ethiopic", Ethiopic);
        map.put("Georgian", Georgian);
        map.put("Gothic", Gothic);
        map.put("Greek", Greek);
        map.put("Gujarati", Gujarati);
        map.put("Gurmukhi", Gurmukhi);
        map.put("Han", Han);
        map.put("Hangul", Hangul);
        map.put("Hebrew", Hebrew);
        map.put("Hiragana", Hiragana);
        map.put("Kannada", Kannada);
        map.put("Katakana", Katakana);
        map.put("Khmer", Khmer);
        map.put("Lao", Lao);
        map.put("Latin", Latin);
        map.put("Malayalam", Malayalam);
        map.put("Mongolian", Mongolian);
        map.put("Myanmar", Myanmar);
        map.put("Ogham", Ogham);
        map.put("Old_Italic", Old_Italic);
        map.put("Oriya", Oriya);
        map.put("Runic", Runic);
        map.put("Sinhala", Sinhala);
        map.put("Syriac", Syriac);
        map.put("Tamil", Tamil);
        map.put("Telugu", Telugu);
        map.put("Thaana", Thaana);
        map.put("Thai", Thai);
        map.put("Tibetan", Tibetan);
        map.put("Canadian_Aboriginal", Canadian_Aboriginal);
        map.put("Yi", Yi);
        map.put("Tagalog", Tagalog);
        map.put("Hanunoo", Hanunoo);
        map.put("Buhid", Buhid);
        map.put("Tagbanwa", Tagbanwa);
        map.put("Braille", Braille);
        map.put("Cypriot", Cypriot);
        map.put("Limbu", Limbu);
        map.put("Linear_B", Linear_B);
        map.put("Osmanya", Osmanya);
        map.put("Shavian", Shavian);
        map.put("Tai_Le", Tai_Le);
        map.put("Ugaritic", Ugaritic);
        map.put("Buginese", Buginese);
        map.put("Glagolitic", Glagolitic);
        map.put("Kharoshthi", Kharoshthi);
        map.put("Syloti_Nagri", Syloti_Nagri);
        map.put("New_Tai_Lue", New_Tai_Lue);
        map.put("Tifinagh", Tifinagh);
        map.put("Old_Persian", Old_Persian);
        map.put("Balinese", Balinese);
        map.put("Batak", Batak);
        map.put("Brahmi", Brahmi);
        map.put("Cham", Cham);
        map.put("Egyptian_Hieroglyphs", Egyptian_Hieroglyphs);
        map.put("Javanese", Javanese);
        map.put("Kayah_Li", Kayah_Li);
        map.put("Lepcha", Lepcha);
        map.put("Mandaic", Mandaic);
        map.put("Nko", Nko);
        map.put("Old_Turkic", Old_Turkic);
        map.put("Phags_Pa", Phags_Pa);
        map.put("Phoenician", Phoenician);
        map.put("Vai", Vai);
        map.put("Cuneiform", Cuneiform);
        map.put("Unknown", Unknown);
        map.put("Carian", Carian);
        map.put("Tai_Tham", Tai_Tham);
        map.put("Lycian", Lycian);
        map.put("Lydian", Lydian);
        map.put("Ol_Chiki", Ol_Chiki);
        map.put("Rejang", Rejang);
        map.put("Saurashtra", Saurashtra);
        map.put("Sundanese", Sundanese);
        map.put("Meetei_Mayek", Meetei_Mayek);
        map.put("Imperial_Aramaic", Imperial_Aramaic);
        map.put("Avestan", Avestan);
        map.put("Kaithi", Kaithi);
        map.put("Inscriptional_Pahlavi", Inscriptional_Pahlavi);
        map.put("Inscriptional_Parthian", Inscriptional_Parthian);
        map.put("Samaritan", Samaritan);
        map.put("Tai_Viet", Tai_Viet);
        return Collections.unmodifiableMap(map);
    }

    private static int[][] make_foldCommon() {
        return new int[][]{new int[]{924, 956, 32}};
    }

    private static int[][] make_foldInherited() {
        return new int[][]{new int[]{921, 953, 32}, new int[]{8126, 8126, 1}};
    }

    private static int[][] make_foldGreek() {
        return new int[][]{new int[]{181, 837, 656}};
    }

    private static Map<String, int[][]> FoldScript() {
        HashMap map = new HashMap();
        map.put("Common", foldCommon);
        map.put("Inherited", foldInherited);
        map.put("Greek", foldGreek);
        return map;
    }

    private static int[][] make_foldLu() {
        return new int[][]{new int[]{97, 122, 1}, new int[]{181, 223, 42}, new int[]{224, 246, 1}, new int[]{248, 255, 1}, new int[]{257, 303, 2}, new int[]{307, 311, 2}, new int[]{314, 328, 2}, new int[]{331, 375, 2}, new int[]{378, 382, 2}, new int[]{383, 384, 1}, new int[]{387, 389, 2}, new int[]{392, 396, 4}, new int[]{TypedValues.CycleType.TYPE_VISIBILITY, 405, 3}, new int[]{409, 410, 1}, new int[]{414, 417, 3}, new int[]{419, 421, 2}, new int[]{TypedValues.CycleType.TYPE_WAVE_OFFSET, 429, 5}, new int[]{432, 436, 4}, new int[]{438, 441, 3}, new int[]{445, 447, 2}, new int[]{453, 454, 1}, new int[]{456, 457, 1}, new int[]{459, 460, 1}, new int[]{462, 476, 2}, new int[]{477, 495, 2}, new int[]{498, 499, 1}, new int[]{TypedValues.PositionType.TYPE_TRANSITION_EASING, TypedValues.PositionType.TYPE_SIZE_PERCENT, 4}, new int[]{TypedValues.PositionType.TYPE_PERCENT_Y, 543, 2}, new int[]{547, 563, 2}, new int[]{572, 575, 3}, new int[]{576, 578, 2}, new int[]{583, 591, 2}, new int[]{592, 596, 1}, new int[]{598, 599, 1}, new int[]{601, TypedValues.MotionType.TYPE_EASING, 2}, new int[]{TypedValues.MotionType.TYPE_DRAW_PATH, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_TYPE, 3}, new int[]{613, 616, 3}, new int[]{617, 619, 2}, new int[]{623, 625, 2}, new int[]{626, 629, 3}, new int[]{637, 643, 3}, new int[]{648, 652, 1}, new int[]{658, 837, 179}, new int[]{881, 883, 2}, new int[]{887, 891, 4}, new int[]{892, 893, 1}, new int[]{940, 943, 1}, new int[]{945, 974, 1}, new int[]{976, 977, 1}, new int[]{981, 983, 1}, new int[]{985, PointerIconCompat.TYPE_CROSSHAIR, 2}, new int[]{PointerIconCompat.TYPE_TEXT, PointerIconCompat.TYPE_ALIAS, 1}, new int[]{PointerIconCompat.TYPE_ALL_SCROLL, PointerIconCompat.TYPE_ZOOM_OUT, 3}, new int[]{1072, 1119, 1}, new int[]{1121, 1153, 2}, new int[]{1163, 1215, 2}, new int[]{1218, 1230, 2}, new int[]{1231, 1319, 2}, new int[]{1377, 1414, 1}, new int[]{7545, 7549, 4}, new int[]{7681, 7829, 2}, new int[]{7835, 7841, 6}, new int[]{7843, 7935, 2}, new int[]{7936, 7943, 1}, new int[]{7952, 7957, 1}, new int[]{7968, 7975, 1}, new int[]{7984, 7991, 1}, new int[]{8000, 8005, 1}, new int[]{8017, 8023, 2}, new int[]{8032, 8039, 1}, new int[]{8048, 8061, 1}, new int[]{8112, 8113, 1}, new int[]{8126, 8144, 18}, new int[]{8145, 8160, 15}, new int[]{8161, 8165, 4}, new int[]{8526, 8580, 54}, new int[]{11312, 11358, 1}, new int[]{11361, 11365, 4}, new int[]{11366, 11372, 2}, new int[]{11379, 11382, 3}, new int[]{11393, 11491, 2}, new int[]{11500, 11502, 2}, new int[]{11520, 11557, 1}, new int[]{42561, 42605, 2}, new int[]{42625, 42647, 2}, new int[]{42787, 42799, 2}, new int[]{42803, 42863, 2}, new int[]{42874, 42876, 2}, new int[]{42879, 42887, 2}, new int[]{42892, 42897, 5}, new int[]{42913, 42921, 2}, new int[]{65345, 65370, 1}, new int[]{66600, 66639, 1}};
    }

    private static int[][] make_foldLl() {
        return new int[][]{new int[]{75, 83, 8}, new int[]{197, 452, 255}, new int[]{453, 455, 2}, new int[]{456, 458, 2}, new int[]{459, 497, 38}, new int[]{498, 837, 339}, new int[]{914, 920, 3}, new int[]{921, 922, 1}, new int[]{924, PDF417Common.MAX_CODEWORDS_IN_BARCODE, 4}, new int[]{PDF417Common.NUMBER_OF_CODEWORDS, 931, 2}, new int[]{934, 937, 3}, new int[]{PointerIconCompat.TYPE_NO_DROP, 7776, 6764}, new int[]{7838, 8486, 648}, new int[]{8490, 8491, 1}};
    }

    private static int[][] make_foldLt() {
        return new int[][]{new int[]{452, 454, 2}, new int[]{455, 457, 2}, new int[]{458, 460, 2}, new int[]{497, 499, 2}, new int[]{8064, 8071, 1}, new int[]{8080, 8087, 1}, new int[]{8096, 8103, 1}, new int[]{8115, 8131, 16}, new int[]{8179, 8179, 1}};
    }

    private static int[][] make_foldMn() {
        return new int[][]{new int[]{921, 953, 32}, new int[]{8126, 8126, 1}};
    }

    private static Map<String, int[][]> FoldCategory() {
        HashMap map = new HashMap();
        map.put("Lu", foldLu);
        map.put("Ll", foldLl);
        map.put("Lt", foldLt);
        map.put("Mn", foldMn);
        return map;
    }
}
