package com.langtoun.annotation_examples.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface TypeDefinition {

  /**
   * This is an array of field names in the order that they are to be
   * (de-)serialized. While this isn't important for some encodings, it's required
   * for others so we will also require it. The array may contain names of fields
   * that are in the annotated class or in any of its superclasses.
   * 
   * @return an array of field names
   */
  String[] value();

  /**
   * Does the annotated type definition represents a list type.
   * 
   * @return {@code true} if the type is a list, otherwise {@code false}
   */
  boolean isList() default false;

  /**
   * An annotation that defines custom encoding parameters for the type
   * definition.
   * 
   * @return a {@link CustomTypeEncoding} object
   */
  CustomTypeEncoding encoding() default @CustomTypeEncoding;

}
