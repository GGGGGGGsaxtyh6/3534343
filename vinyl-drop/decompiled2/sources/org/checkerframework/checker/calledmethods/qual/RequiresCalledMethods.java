package org.checkerframework.checker.calledmethods.qual;

import com.google.firebase.firestore.model.Values;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.PreconditionAnnotation;
import org.checkerframework.framework.qual.QualifierArgument;

/* JADX INFO: loaded from: classes4.dex */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@PreconditionAnnotation(qualifier = CalledMethods.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresCalledMethods {

    @Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface List {
        RequiresCalledMethods[] value();
    }

    @QualifierArgument(Values.VECTOR_MAP_VECTORS_KEY)
    String[] methods();

    String[] value();
}
