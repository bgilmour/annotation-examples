package com.langtoun.annotation_examples.annotations;

import com.langtoun.annotation_examples.types.CustomTypeEncoder;

public @interface CustomTypeEncoding {

  /**
   * The string that will appear as a prefix to a custom type encoding.
   * 
   * @return the custom type encoding prefix, or an empty string if there is no
   *         prefix
   */
  String prefix() default "";

  /**
   * The string that will appear as a suffix to a custom type encoding.
   * 
   * @return the custom type encoding suffix, or an empty string if there is no
   *         suffix
   */
  String suffix() default "";

  /**
   * The string that will be used to separate the fields in a custom type
   * encoding. If no field separator is specified then it's still possible to
   * serialize the type but deserialization isn't possible without deeper
   * knowledge of the encoding.
   * 
   * @return the custom type encoding field separator, or an empty string if there
   *         is no field separator
   */
  String fieldSep() default "";

  /**
   * The string that will prefix a custom type encoding.
   * 
   * @return the custom type encoding prefix
   */
  String keyValSep() default "";

  /**
   * The string that will prefix a custom type encoding.
   * 
   * @return the custom type encoding prefix
   */
  CustomTypeEncoder encoder() default CustomTypeEncoder.STD;
}
