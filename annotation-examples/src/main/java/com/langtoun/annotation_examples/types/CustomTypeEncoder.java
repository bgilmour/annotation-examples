package com.langtoun.annotation_examples.types;

public enum CustomTypeEncoder {

  STD(""),
  GQL("gql");

  private CustomTypeEncoder(String customEncoder) {
    this.customEncoder = customEncoder;
  }

  public final String customEncoder;

}
