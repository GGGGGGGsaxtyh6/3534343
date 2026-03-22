package com.google.android.gms.common.data;

import com.google.re2j.Parser;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@18.9.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class FreezableUtils {
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        Parser.Stack stack = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            stack.add(arrayList.get(i).freeze());
        }
        return stack;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        Parser.Stack stack = (ArrayList<T>) new ArrayList();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            stack.add(it.next().freeze());
        }
        return stack;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        Parser.Stack stack = (ArrayList<T>) new ArrayList(eArr.length);
        for (E e : eArr) {
            stack.add(e.freeze());
        }
        return stack;
    }
}
