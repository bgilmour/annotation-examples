package com.langtoun.annotation_examples.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface CustomTypeEncoding {

  String prefix() default "";

  String suffix() default "";

  String fieldSep() default "";

  String keyValSep() default "";

  String encoding() default "";
}
