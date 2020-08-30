package com.langtoun.annotation_examples.types;

import com.langtoun.annotation_examples.annotations.TypeDefinition;
import com.langtoun.annotation_examples.annotations.TypeProperty;

//@TypeDefinition(isList = false, encoding = @CustomTypeEncoding(prefix = "{", suffix = "}", fieldSep = ",", keyValSep = ":"))
//@TypeDefinition(isList = false, encoding = @CustomTypeEncoding(encoder = CustomTypeEncoder.GQL))
//@TypeDefinition(isList = true)
@TypeDefinition({ "dbl", "list" })
public class DerivedType extends BaseType {

  @TypeProperty
  private Double dbl;

  public DerivedType() {
    super();
  }

  public Double getDbl() { return dbl; }

  public void setDbl(Double dbl) { this.dbl = dbl; }

  @Override
  public String toString() {
    return String.format("DerivedType [dbl=%s] : %s", dbl, super.toString());
  }

}
