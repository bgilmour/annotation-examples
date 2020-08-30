package com.langtoun.annotation_examples.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface TypeDefinition {

  /**
   * Does the annotated type definition represents a list type.
   * 
   * @return {@code true} if the type is a list, otherwise {@code false}
   */
  boolean isList() default false;

  /**
   * An object that defines custom encoding parameters for the type definition.
   * 
   * @return a {@link CustomTypeEncoding} object
   */
  CustomTypeEncoding encoding() default @CustomTypeEncoding;

}
