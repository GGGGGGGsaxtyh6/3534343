package androidx.constraintlayout.core.motion.utils;

import androidx.camera.video.AudioStats;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ArcCurveFit extends CurveFit {
    public static final int ARC_ABOVE = 5;
    public static final int ARC_BELOW = 4;
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;
    private static final int DOWN_ARC = 4;
    private static final int START_HORIZONTAL = 2;
    private static final int START_LINEAR = 3;
    private static final int START_VERTICAL = 1;
    private static final int UP_ARC = 5;
    Arc[] mArcs;
    private boolean mExtrapolate = true;
    private final double[] mTime;

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d, double[] dArr) {
        boolean z = this.mExtrapolate;
        Arc[] arcArr = this.mArcs;
        if (z) {
            double d2 = arcArr[0].mTime1;
            Arc[] arcArr2 = this.mArcs;
            if (d < d2) {
                double d3 = arcArr2[0].mTime1;
                double d4 = d - this.mArcs[0].mTime1;
                boolean z2 = this.mArcs[0].mLinear;
                Arc[] arcArr3 = this.mArcs;
                if (z2) {
                    dArr[0] = arcArr3[0].getLinearX(d3) + (this.mArcs[0].getLinearDX(d3) * d4);
                    dArr[1] = this.mArcs[0].getLinearY(d3) + (d4 * this.mArcs[0].getLinearDY(d3));
                    return;
                } else {
                    arcArr3[0].setPoint(d3);
                    dArr[0] = this.mArcs[0].getX() + (this.mArcs[0].getDX() * d4);
                    dArr[1] = this.mArcs[0].getY() + (d4 * this.mArcs[0].getDY());
                    return;
                }
            }
            if (d > arcArr2[arcArr2.length - 1].mTime2) {
                Arc[] arcArr4 = this.mArcs;
                double d5 = arcArr4[arcArr4.length - 1].mTime2;
                double d6 = d - d5;
                Arc[] arcArr5 = this.mArcs;
                int length = arcArr5.length - 1;
                boolean z3 = arcArr5[length].mLinear;
                Arc[] arcArr6 = this.mArcs;
                if (z3) {
                    dArr[0] = arcArr6[length].getLinearX(d5) + (this.mArcs[length].getLinearDX(d5) * d6);
                    dArr[1] = this.mArcs[length].getLinearY(d5) + (d6 * this.mArcs[length].getLinearDY(d5));
                    return;
                } else {
                    arcArr6[length].setPoint(d);
                    dArr[0] = this.mArcs[length].getX() + (this.mArcs[length].getDX() * d6);
                    dArr[1] = this.mArcs[length].getY() + (d6 * this.mArcs[length].getDY());
                    return;
                }
            }
        } else {
            if (d < arcArr[0].mTime1) {
                d = this.mArcs[0].mTime1;
            }
            Arc[] arcArr7 = this.mArcs;
            if (d > arcArr7[arcArr7.length - 1].mTime2) {
                Arc[] arcArr8 = this.mArcs;
                d = arcArr8[arcArr8.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr9 = this.mArcs;
            if (i >= arcArr9.length) {
                return;
            }
            if (d <= arcArr9[i].mTime2) {
                boolean z4 = this.mArcs[i].mLinear;
                Arc[] arcArr10 = this.mArcs;
                if (z4) {
                    dArr[0] = arcArr10[i].getLinearX(d);
                    dArr[1] = this.mArcs[i].getLinearY(d);
                    return;
                } else {
                    arcArr10[i].setPoint(d);
                    dArr[0] = this.mArcs[i].getX();
                    dArr[1] = this.mArcs[i].getY();
                    return;
                }
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getPos(double d, float[] fArr) {
        boolean z = this.mExtrapolate;
        Arc[] arcArr = this.mArcs;
        if (z) {
            double d2 = arcArr[0].mTime1;
            Arc[] arcArr2 = this.mArcs;
            if (d < d2) {
                double d3 = arcArr2[0].mTime1;
                double d4 = d - this.mArcs[0].mTime1;
                boolean z2 = this.mArcs[0].mLinear;
                Arc[] arcArr3 = this.mArcs;
                if (z2) {
                    fArr[0] = (float) (arcArr3[0].getLinearX(d3) + (this.mArcs[0].getLinearDX(d3) * d4));
                    fArr[1] = (float) (this.mArcs[0].getLinearY(d3) + (d4 * this.mArcs[0].getLinearDY(d3)));
                    return;
                } else {
                    arcArr3[0].setPoint(d3);
                    fArr[0] = (float) (this.mArcs[0].getX() + (this.mArcs[0].getDX() * d4));
                    fArr[1] = (float) (this.mArcs[0].getY() + (d4 * this.mArcs[0].getDY()));
                    return;
                }
            }
            if (d > arcArr2[arcArr2.length - 1].mTime2) {
                Arc[] arcArr4 = this.mArcs;
                double d5 = arcArr4[arcArr4.length - 1].mTime2;
                double d6 = d - d5;
                Arc[] arcArr5 = this.mArcs;
                int length = arcArr5.length - 1;
                boolean z3 = arcArr5[length].mLinear;
                Arc[] arcArr6 = this.mArcs;
                if (z3) {
                    fArr[0] = (float) (arcArr6[length].getLinearX(d5) + (this.mArcs[length].getLinearDX(d5) * d6));
                    fArr[1] = (float) (this.mArcs[length].getLinearY(d5) + (d6 * this.mArcs[length].getLinearDY(d5)));
                    return;
                } else {
                    arcArr6[length].setPoint(d);
                    fArr[0] = (float) this.mArcs[length].getX();
                    fArr[1] = (float) this.mArcs[length].getY();
                    return;
                }
            }
        } else {
            double d7 = arcArr[0].mTime1;
            Arc[] arcArr7 = this.mArcs;
            if (d < d7) {
                d = arcArr7[0].mTime1;
            } else if (d > arcArr7[arcArr7.length - 1].mTime2) {
                Arc[] arcArr8 = this.mArcs;
                d = arcArr8[arcArr8.length - 1].mTime2;
            }
        }
        int i = 0;
        while (true) {
            Arc[] arcArr9 = this.mArcs;
            if (i >= arcArr9.length) {
                return;
            }
            if (d <= arcArr9[i].mTime2) {
                boolean z4 = this.mArcs[i].mLinear;
                Arc[] arcArr10 = this.mArcs;
                if (z4) {
                    fArr[0] = (float) arcArr10[i].getLinearX(d);
                    fArr[1] = (float) this.mArcs[i].getLinearY(d);
                    return;
                } else {
                    arcArr10[i].setPoint(d);
                    fArr[0] = (float) this.mArcs[i].getX();
                    fArr[1] = (float) this.mArcs[i].getY();
                    return;
                }
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public void getSlope(double d, double[] dArr) {
        double d2 = this.mArcs[0].mTime1;
        Arc[] arcArr = this.mArcs;
        if (d < d2) {
            d = arcArr[0].mTime1;
        } else if (d > arcArr[arcArr.length - 1].mTime2) {
            Arc[] arcArr2 = this.mArcs;
            d = arcArr2[arcArr2.length - 1].mTime2;
        }
        int i = 0;
        while (true) {
            Arc[] arcArr3 = this.mArcs;
            if (i >= arcArr3.length) {
                return;
            }
            if (d <= arcArr3[i].mTime2) {
                boolean z = this.mArcs[i].mLinear;
                Arc[] arcArr4 = this.mArcs;
                if (z) {
                    dArr[0] = arcArr4[i].getLinearDX(d);
                    dArr[1] = this.mArcs[i].getLinearDY(d);
                    return;
                } else {
                    arcArr4[i].setPoint(d);
                    dArr[0] = this.mArcs[i].getDX();
                    dArr[1] = this.mArcs[i].getDY();
                    return;
                }
            }
            i++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getPos(double d, int i) {
        double linearY;
        double linearDY;
        double y;
        double dy;
        double linearY2;
        double linearDY2;
        boolean z = this.mExtrapolate;
        Arc[] arcArr = this.mArcs;
        int i2 = 0;
        if (z) {
            double d2 = arcArr[0].mTime1;
            Arc[] arcArr2 = this.mArcs;
            if (d < d2) {
                double d3 = arcArr2[0].mTime1;
                double d4 = d - this.mArcs[0].mTime1;
                if (this.mArcs[0].mLinear) {
                    Arc[] arcArr3 = this.mArcs;
                    if (i == 0) {
                        linearY2 = arcArr3[0].getLinearX(d3);
                        linearDY2 = this.mArcs[0].getLinearDX(d3);
                    } else {
                        linearY2 = arcArr3[0].getLinearY(d3);
                        linearDY2 = this.mArcs[0].getLinearDY(d3);
                    }
                    return linearY2 + (d4 * linearDY2);
                }
                this.mArcs[0].setPoint(d3);
                Arc[] arcArr4 = this.mArcs;
                if (i == 0) {
                    y = arcArr4[0].getX();
                    dy = this.mArcs[0].getDX();
                } else {
                    y = arcArr4[0].getY();
                    dy = this.mArcs[0].getDY();
                }
                return y + (d4 * dy);
            }
            if (d > arcArr2[arcArr2.length - 1].mTime2) {
                double d5 = this.mArcs[r0.length - 1].mTime2;
                double d6 = d - d5;
                Arc[] arcArr5 = this.mArcs;
                int length = arcArr5.length - 1;
                if (i == 0) {
                    linearY = arcArr5[length].getLinearX(d5);
                    linearDY = this.mArcs[length].getLinearDX(d5);
                } else {
                    linearY = arcArr5[length].getLinearY(d5);
                    linearDY = this.mArcs[length].getLinearDY(d5);
                }
                return linearY + (d6 * linearDY);
            }
        } else {
            double d7 = arcArr[0].mTime1;
            Arc[] arcArr6 = this.mArcs;
            if (d < d7) {
                d = arcArr6[0].mTime1;
            } else if (d > arcArr6[arcArr6.length - 1].mTime2) {
                d = this.mArcs[r7.length - 1].mTime2;
            }
        }
        while (true) {
            Arc[] arcArr7 = this.mArcs;
            if (i2 >= arcArr7.length) {
                return Double.NaN;
            }
            if (d <= arcArr7[i2].mTime2) {
                if (this.mArcs[i2].mLinear) {
                    Arc[] arcArr8 = this.mArcs;
                    if (i == 0) {
                        return arcArr8[i2].getLinearX(d);
                    }
                    return arcArr8[i2].getLinearY(d);
                }
                this.mArcs[i2].setPoint(d);
                Arc[] arcArr9 = this.mArcs;
                if (i == 0) {
                    return arcArr9[i2].getX();
                }
                return arcArr9[i2].getY();
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double getSlope(double d, int i) {
        int i2 = 0;
        if (d < this.mArcs[0].mTime1) {
            d = this.mArcs[0].mTime1;
        }
        if (d > this.mArcs[r0.length - 1].mTime2) {
            d = this.mArcs[r5.length - 1].mTime2;
        }
        while (true) {
            Arc[] arcArr = this.mArcs;
            if (i2 >= arcArr.length) {
                return Double.NaN;
            }
            if (d <= arcArr[i2].mTime2) {
                if (this.mArcs[i2].mLinear) {
                    Arc[] arcArr2 = this.mArcs;
                    if (i == 0) {
                        return arcArr2[i2].getLinearDX(d);
                    }
                    return arcArr2[i2].getLinearDY(d);
                }
                this.mArcs[i2].setPoint(d);
                Arc[] arcArr3 = this.mArcs;
                if (i == 0) {
                    return arcArr3[i2].getDX();
                }
                return arcArr3[i2].getDY();
            }
            i2++;
        }
    }

    @Override // androidx.constraintlayout.core.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mTime;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ArcCurveFit(int[] r24, double[] r25, double[][] r26) {
        /*
            r23 = this;
            r0 = r23
            r1 = r25
            r0.<init>()
            r2 = 1
            r0.mExtrapolate = r2
            r0.mTime = r1
            int r3 = r1.length
            int r3 = r3 - r2
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc[] r3 = new androidx.constraintlayout.core.motion.utils.ArcCurveFit.Arc[r3]
            r0.mArcs = r3
            r3 = 0
            r5 = r2
            r6 = r5
            r4 = r3
        L16:
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc[] r7 = r0.mArcs
            int r8 = r7.length
            if (r4 >= r8) goto L58
            r8 = r24[r4]
            r9 = 3
            if (r8 == 0) goto L35
            if (r8 == r2) goto L33
            r10 = 2
            if (r8 == r10) goto L31
            if (r8 == r9) goto L2f
            r9 = 4
            if (r8 == r9) goto L35
            r9 = 5
            if (r8 == r9) goto L35
            r9 = r6
            goto L35
        L2f:
            if (r5 != r2) goto L33
        L31:
            r5 = r10
            goto L34
        L33:
            r5 = r2
        L34:
            r9 = r5
        L35:
            androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc r8 = new androidx.constraintlayout.core.motion.utils.ArcCurveFit$Arc
            r10 = r1[r4]
            int r6 = r4 + 1
            r12 = r1[r6]
            r14 = r26[r4]
            r15 = r14[r3]
            r17 = r14[r2]
            r14 = r26[r6]
            r19 = r14[r3]
            r21 = r14[r2]
            r14 = r15
            r16 = r17
            r18 = r19
            r20 = r21
            r8.<init>(r9, r10, r12, r14, r16, r18, r20)
            r7[r4] = r8
            r4 = r6
            r6 = r9
            goto L16
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.ArcCurveFit.<init>(int[], double[], double[][]):void");
    }

    private static class Arc {
        private static final double EPSILON = 0.001d;
        private static final String TAG = "Arc";
        private static double[] sOurPercent = new double[91];
        double mArcDistance;
        double mArcVelocity;
        double mEllipseA;
        double mEllipseB;
        double mEllipseCenterX;
        double mEllipseCenterY;
        boolean mLinear;
        double[] mLut;
        double mOneOverDeltaTime;
        double mTime1;
        double mTime2;
        double mTmpCosAngle;
        double mTmpSinAngle;
        boolean mVertical;
        double mX1;
        double mX2;
        double mY1;
        double mY2;

        Arc(int i, double d, double d2, double d3, double d4, double d5, double d6) {
            this.mLinear = false;
            double d7 = d5 - d3;
            double d8 = d6 - d4;
            if (i == 1) {
                this.mVertical = true;
            } else if (i == 4) {
                this.mVertical = d8 > AudioStats.AUDIO_AMPLITUDE_NONE;
            } else if (i == 5) {
                this.mVertical = d8 < AudioStats.AUDIO_AMPLITUDE_NONE;
            } else {
                this.mVertical = false;
            }
            this.mTime1 = d;
            this.mTime2 = d2;
            this.mOneOverDeltaTime = 1.0d / (d2 - d);
            if (3 == i) {
                this.mLinear = true;
            }
            if (this.mLinear || Math.abs(d7) < EPSILON || Math.abs(d8) < EPSILON) {
                this.mLinear = true;
                this.mX1 = d3;
                this.mX2 = d5;
                this.mY1 = d4;
                this.mY2 = d6;
                double dHypot = Math.hypot(d8, d7);
                this.mArcDistance = dHypot;
                this.mArcVelocity = dHypot * this.mOneOverDeltaTime;
                double d9 = this.mTime2;
                double d10 = this.mTime1;
                this.mEllipseCenterX = d7 / (d9 - d10);
                this.mEllipseCenterY = d8 / (d9 - d10);
                return;
            }
            this.mLut = new double[101];
            boolean z = this.mVertical;
            this.mEllipseA = d7 * ((double) (z ? -1 : 1));
            this.mEllipseB = d8 * ((double) (z ? 1 : -1));
            this.mEllipseCenterX = z ? d5 : d3;
            this.mEllipseCenterY = z ? d4 : d6;
            buildTable(d3, d4, d5, d6);
            this.mArcVelocity = this.mArcDistance * this.mOneOverDeltaTime;
        }

        void setPoint(double d) {
            double dLookup = lookup((this.mVertical ? this.mTime2 - d : d - this.mTime1) * this.mOneOverDeltaTime) * 1.5707963267948966d;
            this.mTmpSinAngle = Math.sin(dLookup);
            this.mTmpCosAngle = Math.cos(dLookup);
        }

        double getX() {
            return this.mEllipseCenterX + (this.mEllipseA * this.mTmpSinAngle);
        }

        double getY() {
            return this.mEllipseCenterY + (this.mEllipseB * this.mTmpCosAngle);
        }

        double getDX() {
            double d = this.mEllipseA * this.mTmpCosAngle;
            double dHypot = this.mArcVelocity / Math.hypot(d, (-this.mEllipseB) * this.mTmpSinAngle);
            return this.mVertical ? (-d) * dHypot : d * dHypot;
        }

        double getDY() {
            double d = this.mEllipseA * this.mTmpCosAngle;
            double d2 = (-this.mEllipseB) * this.mTmpSinAngle;
            double dHypot = this.mArcVelocity / Math.hypot(d, d2);
            return this.mVertical ? (-d2) * dHypot : d2 * dHypot;
        }

        public double getLinearX(double d) {
            double d2 = (d - this.mTime1) * this.mOneOverDeltaTime;
            double d3 = this.mX1;
            return d3 + (d2 * (this.mX2 - d3));
        }

        public double getLinearY(double d) {
            double d2 = (d - this.mTime1) * this.mOneOverDeltaTime;
            double d3 = this.mY1;
            return d3 + (d2 * (this.mY2 - d3));
        }

        public double getLinearDX(double d) {
            return this.mEllipseCenterX;
        }

        public double getLinearDY(double d) {
            return this.mEllipseCenterY;
        }

        double lookup(double d) {
            if (d <= AudioStats.AUDIO_AMPLITUDE_NONE) {
                return AudioStats.AUDIO_AMPLITUDE_NONE;
            }
            if (d >= 1.0d) {
                return 1.0d;
            }
            double[] dArr = this.mLut;
            double length = d * ((double) (dArr.length - 1));
            int i = (int) length;
            double d2 = length - ((double) i);
            double d3 = dArr[i];
            return d3 + (d2 * (dArr[i + 1] - d3));
        }

        private void buildTable(double d, double d2, double d3, double d4) {
            double d5 = d3 - d;
            double d6 = d2 - d4;
            int i = 0;
            double dHypot = AudioStats.AUDIO_AMPLITUDE_NONE;
            double d7 = AudioStats.AUDIO_AMPLITUDE_NONE;
            double d8 = AudioStats.AUDIO_AMPLITUDE_NONE;
            while (true) {
                if (i >= sOurPercent.length) {
                    break;
                }
                int i2 = i;
                double radians = Math.toRadians((((double) i) * 90.0d) / ((double) (r15.length - 1)));
                double dSin = Math.sin(radians) * d5;
                double dCos = Math.cos(radians) * d6;
                if (i2 > 0) {
                    dHypot += Math.hypot(dSin - d7, dCos - d8);
                    sOurPercent[i2] = dHypot;
                }
                i = i2 + 1;
                d7 = dSin;
                d8 = dCos;
            }
            this.mArcDistance = dHypot;
            int i3 = 0;
            while (true) {
                double[] dArr = sOurPercent;
                if (i3 >= dArr.length) {
                    break;
                }
                dArr[i3] = dArr[i3] / dHypot;
                i3++;
            }
            int i4 = 0;
            while (true) {
                if (i4 >= this.mLut.length) {
                    return;
                }
                double length = ((double) i4) / ((double) (r1.length - 1));
                int iBinarySearch = Arrays.binarySearch(sOurPercent, length);
                if (iBinarySearch >= 0) {
                    this.mLut[i4] = ((double) iBinarySearch) / ((double) (sOurPercent.length - 1));
                } else if (iBinarySearch == -1) {
                    this.mLut[i4] = 0.0d;
                } else {
                    int i5 = -iBinarySearch;
                    int i6 = i5 - 2;
                    double[] dArr2 = sOurPercent;
                    double d9 = dArr2[i6];
                    this.mLut[i4] = (((double) i6) + ((length - d9) / (dArr2[i5 - 1] - d9))) / ((double) (dArr2.length - 1));
                }
                i4++;
            }
        }
    }
}
