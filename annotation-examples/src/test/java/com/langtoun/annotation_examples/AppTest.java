package com.langtoun.annotation_examples;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.langtoun.annotation_examples.annotations.CustomTypeEncoding;
import com.langtoun.annotation_examples.annotations.TypeDefinition;
import com.langtoun.annotation_examples.annotations.TypeProperty;
import com.langtoun.annotation_examples.types.BaseType;
import com.langtoun.annotation_examples.types.DerivedType;
import com.langtoun.annotation_examples.util.ExampleUtils;

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

    final BaseType baseType = new BaseType();
    baseType.setString("bruce");
    baseType.setInteger(54);
    baseType.getList().add("one");
    baseType.getList().add("two");
    baseType.getList().add("three");
    System.out.println("baseType = " + baseType);

    final DerivedType derivedType = new DerivedType();
    derivedType.setString("louise");
    derivedType.setInteger(39);
    derivedType.getList().add("four");
    derivedType.getList().add("five");
    derivedType.getList().add("six");
    derivedType.setDbl(42.0);
    System.out.println("derivedType = " + derivedType);

    final Object object = derivedType;
    final Class<?> clazz = object.getClass();
    List<Class<?>> superclasses = null;
    String indent;

    System.out.println("--- SUPERCLASSES ---");
    superclasses = ExampleUtils.getSuperclasses(clazz);
    indent = "";
    for (final Class<?> c : superclasses) {
      System.out.printf("%s%s: %d fields\n", indent, c.getTypeName(), c.getDeclaredFields().length);
      indent = indent + "  ";
    }
    clazz.getSuperclass();

    System.out.println("--- CLASS HIERARCHY ---");
    superclasses = ExampleUtils.getClassHierarchy(clazz);
    indent = "";
    for (final Class<?> c : superclasses) {
      System.out.printf("%s%s: %d fields\n", indent, c.getTypeName(), c.getDeclaredFields().length);
      indent = indent + "  ";
    }

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

      System.out.println("  --- MAP / REDUCE ---");
//      final Map<String, Pair<Field, TypeProperty>> propertyFields = ExampleUtils.getDeclaredFieldsWithTypeProperty(clazz);
//      final Map<String, Pair<Field, TypeProperty>> propertyFields = ExampleUtils.getSuperclassFieldsWithTypeProperty(clazz);
      final Map<String, Pair<Field, TypeProperty>> propertyFields = ExampleUtils.getHierarchyFieldsWithTypeProperty(clazz);

//      final String[] fieldOrder = { "string", "integer", "list", "dbl" };

      final String[] fieldOrder = typeDefinition.fieldOrder().value().length > 0 ? typeDefinition.fieldOrder().value()
          : propertyFields.keySet().toArray(new String[] {});

      for (final String fieldName : fieldOrder) {
        final Pair<Field, TypeProperty> entry = propertyFields.get(fieldName);
        if (entry != null) {
          final Field field = entry.getKey();
          final TypeProperty property = entry.getValue();
          field.setAccessible(true);
          try {
            System.out.printf("  field: %s = %s\n", fieldName, field.get(object));
          } catch (IllegalArgumentException | IllegalAccessException e) {
            System.out.printf("ERROR: %s\n", e);
          }
          System.out.printf("    @%s [json=%s xml=%s encoding=%s]\n", property.annotationType().getSimpleName(), property.json(),
              property.xml(), property.encoding().encodingType);
        }
      }
    }
  }
}
