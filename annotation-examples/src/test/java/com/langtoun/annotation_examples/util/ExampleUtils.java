package com.langtoun.annotation_examples.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import com.langtoun.annotation_examples.annotations.TypeProperty;

public final class ExampleUtils {

  private ExampleUtils() {
    // static utility methods
  }

  public static List<Class<?>> getSuperclasses(Class<?> clazz) {
    List<Class<?>> superclasses = new ArrayList<>();
    getSuperclasses(clazz, superclasses);
    return superclasses;
  }

  public static List<Class<?>> getClassHierarchy(Class<?> clazz) {
    List<Class<?>> superclasses = new ArrayList<>();
    getSuperclasses(clazz, superclasses);
    superclasses.add(clazz);
    return superclasses;
  }

  private static void getSuperclasses(Class<?> clazz, List<Class<?>> superclasses) {
    Class<?> superclass = clazz.getSuperclass();
    if (superclass != null) {
      getSuperclasses(superclass, superclasses);
      superclasses.add(superclass);
    }
  }

  public static Map<String, Pair<Field, TypeProperty>> getDeclaredFieldsWithTypeProperty(Class<?> clazz) {
    return getDeclaredFieldsWithAnnotation(Stream.of(clazz.getDeclaredFields()));
  }

  public static Map<String, Pair<Field, TypeProperty>> getSuperclassFieldsWithTypeProperty(Class<?> clazz) {
    return getDeclaredFieldsWithAnnotation(getSuperclasses(clazz).stream().flatMap(c -> Stream.of(c.getDeclaredFields())));
  }

  public static Map<String, Pair<Field, TypeProperty>> getHierarchyFieldsWithTypeProperty(Class<?> clazz) {
    return getDeclaredFieldsWithAnnotation(getClassHierarchy(clazz).stream().flatMap(c -> Stream.of(c.getDeclaredFields())));
  }

  private static Map<String, Pair<Field, TypeProperty>> getDeclaredFieldsWithAnnotation(Stream<Field> stream) {
    return stream.filter(f -> f.isAnnotationPresent(TypeProperty.class))
        .collect(Collectors.toMap(f -> f.getName(), f -> Pair.of(f, f.getAnnotation(TypeProperty.class))));
  }

}
