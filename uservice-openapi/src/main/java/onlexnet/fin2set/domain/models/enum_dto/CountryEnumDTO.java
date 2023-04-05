package onlexnet.fin2set.domain.models.enum_dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CountryEnumDTO {

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

  CountryEnumDTO(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static CountryEnumDTO fromValue(String value) {
    for (CountryEnumDTO b : CountryEnumDTO.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static List<CountryEnumDTO> fromValue(List<String> listOfValue) {
    var list = new ArrayList<CountryEnumDTO>();
    for (String value : listOfValue) {
        list.add(fromValue(value));
    }
    return list;
  }

}
