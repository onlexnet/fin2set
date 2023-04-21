package onlexnet.fin2set.domain.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CountryEnum {

  AT("AT"),

  BE("BE"),

  BG("BG"),

  HR("HR"),

  CY("CY"),

  CZ("CZ"),

  DK("DK"),

  EE("EE"),

  FI("FI"),

  FR("FR"),

  DE("DE"),

  GR("GR"),

  HU("HU"),

  IS("IS"),

  IE("IE"),

  IT("IT"),

  LV("LV"),

  LI("LI"),

  LT("LT"),

  LU("LU"),

  MT("MT"),

  NL("NL"),

  NO("NO"),

  PL("PL"),

  PT("PT"),

  RO("RO"),

  SK("SK"),

  SI("SI"),

  ES("ES"),

  SE("SE"),

  GB("GB");

  private String value;

  CountryEnum(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CountryEnum fromValue(String value) {
    for (CountryEnum b : CountryEnum.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static List<CountryEnum> fromValue(List<String> listOfValue) {
    var list = new ArrayList<CountryEnum>();
    for (String value : listOfValue) {
        list.add(fromValue(value));
    }
    return list;
  }

}
