package com.langtoun.annotation_examples.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.langtoun.annotation_examples.types.FieldEncodingType;

@Retention(RUNTIME)
@Target(FIELD)
public @interface TypeProperty {

  /**
   * The name of the annotated field as it should appear in a JSON serialization.
   * 
   * @return the JSON property name
   */
  String json() default "";

  /**
   * The name of the annotated field as it should appear in an XML serialization.
   * 
   * @return the XML element name
   */
  String xml() default "";

  /**
   * If the annotated field is a member of a type that is subject to custom
   * encoding then this is the encoding that is to be used.
   * 
   * @return the type of encoding for the annotated field
   */
  FieldEncodingType encoding() default FieldEncodingType.STD;
}
