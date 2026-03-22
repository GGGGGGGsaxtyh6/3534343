package com.google.re2j;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
class Machine {
    private int[] matchcap;
    private boolean matched;
    private int ncap;
    private Thread[] pool = new Thread[10];
    private int poolSize;
    private final Prog prog;
    private final Queue q0;
    private final Queue q1;
    private RE2 re2;

    private static class Thread {
        int[] cap;
        Inst inst;

        Thread(int i) {
            this.cap = new int[i];
        }
    }

    private static class Queue {
        final int[] densePcs;
        final Thread[] denseThreads;
        int size;
        final int[] sparse;

        Queue(int i) {
            this.sparse = new int[i];
            this.densePcs = new int[i];
            this.denseThreads = new Thread[i];
        }

        boolean contains(int i) {
            int i2 = this.sparse[i];
            return i2 < this.size && this.densePcs[i2] == i;
        }

        boolean isEmpty() {
            return this.size == 0;
        }

        int add(int i) {
            int i2 = this.size;
            this.size = i2 + 1;
            this.sparse[i] = i2;
            this.denseThreads[i2] = null;
            this.densePcs[i2] = i;
            return i2;
        }

        void clear() {
            this.size = 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            for (int i = 0; i < this.size; i++) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(this.densePcs[i]);
            }
            sb.append('}');
            return sb.toString();
        }
    }

    Machine(RE2 re2) {
        Prog prog = re2.prog;
        this.prog = prog;
        this.re2 = re2;
        this.q0 = new Queue(prog.numInst());
        this.q1 = new Queue(prog.numInst());
        this.matchcap = new int[prog.numCap >= 2 ? prog.numCap : 2];
    }

    void init(int i) {
        this.ncap = i;
        if (i > this.matchcap.length) {
            initNewCap(i);
        } else {
            resetCap(i);
        }
    }

    private void resetCap(int i) {
        for (int i2 = 0; i2 < this.poolSize; i2++) {
            Arrays.fill(this.pool[i2].cap, 0, i, 0);
        }
    }

    private void initNewCap(int i) {
        for (int i2 = 0; i2 < this.poolSize; i2++) {
            this.pool[i2].cap = new int[i];
        }
        this.matchcap = new int[i];
    }

    int[] submatches() {
        int i = this.ncap;
        if (i == 0) {
            return Utils.EMPTY_INTS;
        }
        return Arrays.copyOf(this.matchcap, i);
    }

    private Thread alloc(Inst inst) {
        Thread thread;
        int i = this.poolSize;
        if (i > 0) {
            int i2 = i - 1;
            this.poolSize = i2;
            thread = this.pool[i2];
        } else {
            thread = new Thread(this.matchcap.length);
        }
        thread.inst = inst;
        return thread;
    }

    private void free(Queue queue) {
        free(queue, 0);
    }

    private void free(Queue queue, int i) {
        int i2 = this.poolSize + (queue.size - i);
        Thread[] threadArr = this.pool;
        if (threadArr.length < i2) {
            this.pool = (Thread[]) Arrays.copyOf(threadArr, Math.max(threadArr.length * 2, i2));
        }
        while (i < queue.size) {
            Thread thread = queue.denseThreads[i];
            if (thread != null) {
                Thread[] threadArr2 = this.pool;
                int i3 = this.poolSize;
                threadArr2[i3] = thread;
                this.poolSize = i3 + 1;
            }
            i++;
        }
        queue.clear();
    }

    private void free(Thread thread) {
        Thread[] threadArr = this.pool;
        if (threadArr.length <= this.poolSize) {
            this.pool = (Thread[]) Arrays.copyOf(threadArr, threadArr.length * 2);
        }
        Thread[] threadArr2 = this.pool;
        int i = this.poolSize;
        threadArr2[i] = thread;
        this.poolSize = i + 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0060, code lost:
    
        r2 = r8;
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean match(com.google.re2j.MachineInput r20, int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.re2j.Machine.match(com.google.re2j.MachineInput, int, int):boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void step(com.google.re2j.Machine.Queue r14, com.google.re2j.Machine.Queue r15, int r16, int r17, int r18, int r19, int r20, boolean r21) {
        /*
            r13 = this;
            r7 = r16
            r8 = r18
            com.google.re2j.RE2 r1 = r13.re2
            boolean r9 = r1.longest
            r10 = 0
            r11 = r10
        La:
            int r1 = r14.size
            if (r11 >= r1) goto La2
            com.google.re2j.Machine$Thread[] r1 = r14.denseThreads
            r6 = r1[r11]
            if (r6 != 0) goto L18
        L14:
            r12 = r20
            goto L9e
        L18:
            if (r9 == 0) goto L30
            boolean r1 = r13.matched
            if (r1 == 0) goto L30
            int r1 = r13.ncap
            if (r1 <= 0) goto L30
            int[] r1 = r13.matchcap
            r1 = r1[r10]
            int[] r2 = r6.cap
            r2 = r2[r10]
            if (r1 >= r2) goto L30
            r13.free(r6)
            goto L14
        L30:
            com.google.re2j.Inst r1 = r6.inst
            int r2 = r1.op
            r3 = 1
            switch(r2) {
                case 6: goto L55;
                case 7: goto L38;
                case 8: goto L4e;
                case 9: goto L47;
                case 10: goto L52;
                case 11: goto L40;
                default: goto L38;
            }
        L38:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "bad inst"
            r1.<init>(r2)
            throw r1
        L40:
            r2 = 10
            if (r8 == r2) goto L45
            goto L52
        L45:
            r3 = r10
            goto L52
        L47:
            int[] r2 = r1.runes
            r2 = r2[r10]
            if (r8 != r2) goto L45
            goto L52
        L4e:
            boolean r3 = r1.matchRune(r8)
        L52:
            r12 = r20
            goto L84
        L55:
            r2 = 2
            r12 = r20
            if (r12 != r2) goto L5d
            if (r21 != 0) goto L5d
            goto L83
        L5d:
            int r2 = r13.ncap
            if (r2 <= 0) goto L7a
            if (r9 == 0) goto L6d
            boolean r2 = r13.matched
            if (r2 == 0) goto L6d
            int[] r2 = r13.matchcap
            r2 = r2[r3]
            if (r2 >= r7) goto L7a
        L6d:
            int[] r2 = r6.cap
            r2[r3] = r7
            int[] r2 = r6.cap
            int[] r4 = r13.matchcap
            int r5 = r13.ncap
            java.lang.System.arraycopy(r2, r10, r4, r10, r5)
        L7a:
            if (r9 != 0) goto L81
            int r2 = r11 + 1
            r13.free(r14, r2)
        L81:
            r13.matched = r3
        L83:
            r3 = r10
        L84:
            if (r3 == 0) goto L94
            int r2 = r1.out
            int[] r4 = r6.cap
            r0 = r13
            r1 = r15
            r3 = r17
            r5 = r19
            com.google.re2j.Machine$Thread r6 = r0.add(r1, r2, r3, r4, r5, r6)
        L94:
            if (r6 == 0) goto L9e
            r13.free(r6)
            com.google.re2j.Machine$Thread[] r1 = r14.denseThreads
            r2 = 0
            r1[r11] = r2
        L9e:
            int r11 = r11 + 1
            goto La
        La2:
            r14.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.re2j.Machine.step(com.google.re2j.Machine$Queue, com.google.re2j.Machine$Queue, int, int, int, int, int, boolean):void");
    }

    private Thread add(Queue queue, int i, int i2, int[] iArr, int i3, Thread thread) {
        Thread threadAlloc;
        if (i != 0 && !queue.contains(i)) {
            int iAdd = queue.add(i);
            Inst inst = this.prog.inst[i];
            switch (inst.op) {
                case 1:
                case 2:
                    return add(queue, inst.arg, i2, iArr, i3, add(queue, inst.out, i2, iArr, i3, thread));
                case 3:
                    if (inst.arg < this.ncap) {
                        int i4 = iArr[inst.arg];
                        iArr[inst.arg] = i2;
                        add(queue, inst.out, i2, iArr, i3, null);
                        iArr[inst.arg] = i4;
                        return thread;
                    }
                    return add(queue, inst.out, i2, iArr, i3, thread);
                case 4:
                    return (inst.arg & (~i3)) == 0 ? add(queue, inst.out, i2, iArr, i3, thread) : thread;
                case 5:
                    break;
                case 6:
                case 8:
                case 9:
                case 10:
                case 11:
                    if (thread == null) {
                        threadAlloc = alloc(inst);
                    } else {
                        thread.inst = inst;
                        threadAlloc = thread;
                    }
                    if (this.ncap > 0 && threadAlloc.cap != iArr) {
                        System.arraycopy(iArr, 0, threadAlloc.cap, 0, this.ncap);
                    }
                    queue.denseThreads[iAdd] = threadAlloc;
                    return null;
                case 7:
                    return add(queue, inst.out, i2, iArr, i3, thread);
                default:
                    throw new IllegalStateException("unhandled");
            }
        }
        return thread;
    }
}
