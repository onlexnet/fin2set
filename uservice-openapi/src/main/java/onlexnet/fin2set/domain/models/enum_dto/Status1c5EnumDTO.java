package onlexnet.fin2set.domain.models.enum_dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status1c5EnumDTO {

    CR("CR"),

    ID("ID"),

    LN("LN"),

    RJ("RJ"),

    ER("ER"),

    SU("SU"),

    EX("EX"),

    GC("GC"),

    UA("UA"),

    GA("GA"),

    SA("SA");

    private String value;

    Status1c5EnumDTO(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Status1c5EnumDTO fromValue(String value) {
        for (Status1c5EnumDTO b : Status1c5EnumDTO.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
