package com.langtoun.annotation_examples;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.langtoun.annotation_examples.annotations.CustomTypeEncoding;
import com.langtoun.annotation_examples.annotations.TypeDefinition;
import com.langtoun.annotation_examples.annotations.TypeProperty;
import com.langtoun.annotation_examples.types.Simple;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Fun with user-defined annotations.
 */
public class AppTest extends TestCase {
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public AppTest(final String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(AppTest.class);
  }

  /**
   * Rigourous Test :-)
   */
  public void testApp() {

    final Simple simple = new Simple();
    simple.setString("bruce");
    simple.setInteger(54);
    simple.getList().add("one");
    simple.getList().add("two");
    simple.getList().add("three");
    System.out.println("simple = " + simple);

    final Class<?> clazz = simple.getClass();
    final TypeDefinition typeDefinition = clazz.getAnnotation(TypeDefinition.class);
    if (typeDefinition != null) {
      System.out.println("--- TYPE DEFINITION ---");
      System.out.printf("@%s [isList=%b encoder=%s]\n", typeDefinition.annotationType().getSimpleName(), typeDefinition.isList(),
          typeDefinition.encoding());
      final CustomTypeEncoding encoding = typeDefinition.encoding();
      if (encoding != null) {
        System.out.printf("  @%s [prefix=%s suffix=%s fieldSep=%s keyValSep=%s encoder=%s]\n",
            encoding.annotationType().getSimpleName(), encoding.prefix(), encoding.suffix(), encoding.fieldSep(),
            encoding.keyValSep(), encoding.encoder());
      }

      System.out.println("  --- NESTED FOR LOOPS ---");
      for (final Field field : clazz.getDeclaredFields()) {
        final boolean isProperty = field.isAnnotationPresent(TypeProperty.class);
        System.out.printf("  field: %s [%s] [%s]\n", field.getName(), field.isAccessible() ? "public" : "private",
            isProperty ? "property []" : "other");
        if (isProperty) {
          for (final TypeProperty property : field.getAnnotationsByType(TypeProperty.class)) {
            System.out.printf("    @%s [json=%s xml=%s encoding=%s]\n", property.annotationType().getSimpleName(), property.json(),
                property.xml(), property.encoding().encodingType);
          }
        }
      }

      System.out.println("  --- MAP / REDUCE ---");
      final Map<Field, TypeProperty> propertyFields = Stream.of(clazz.getDeclaredFields())
          .filter(f -> f.isAnnotationPresent(TypeProperty.class))
          .collect(Collectors.toMap(Function.identity(), f -> f.getAnnotation(TypeProperty.class)));

      for (final Entry<Field, TypeProperty> propertyField : propertyFields.entrySet()) {
        final Field field = propertyField.getKey();
        final TypeProperty property = propertyField.getValue();
        field.setAccessible(true);
        try {
          System.out.printf("  field: %s = %s\n", field.getName(), field.get(simple));
        } catch (IllegalArgumentException | IllegalAccessException e) {
          System.out.printf("ERROR: %s\n", e);
        }
        System.out.printf("    @%s [json=%s xml=%s encoding=%s]\n", property.annotationType().getSimpleName(), property.json(),
            property.xml(), property.encoding().encodingType);
      }
    }
  }
}
