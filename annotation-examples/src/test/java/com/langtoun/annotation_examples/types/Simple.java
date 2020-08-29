package com.langtoun.annotation_examples.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.langtoun.annotation_examples.annotations.CustomTypeEncoding;
import com.langtoun.annotation_examples.annotations.Property;

@CustomTypeEncoding(prefix = "{", suffix = "}", fieldSep = ",", keyValSep = ":", encoder = "gql")
public class Simple {

  @Property(json = "json_str", xml = "xml_str", encoding = FieldEncodingType.JSON)
  private String string;

  @Property(json = "json_int", encoding = FieldEncodingType.XML_URLENCODED)
  private Integer integer;

  @Property(xml = "xml_list", encoding = FieldEncodingType.BASE64)
  private List<String> list;

  public Simple() {
    list = new ArrayList<>();
  }

  public String getString() { return string; }

  public void setString(String string) { this.string = string; }

  public Integer getInteger() { return integer; }

  public void setInteger(Integer integer) { this.integer = integer; }

  public List<String> getList() { return list; }

  public void setList(List<String> list) { this.list = list; }

  @Override
  public int hashCode() {
    return Objects.hash(integer, list, string);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Simple other = (Simple) obj;
    return Objects.equals(integer, other.integer) && Objects.equals(list, other.list) && Objects.equals(string, other.string);
  }

  @Override
  public String toString() {
    return String.format("Simple [string=%s, integer=%s, list=%s]", string, integer, list);
  }

}
