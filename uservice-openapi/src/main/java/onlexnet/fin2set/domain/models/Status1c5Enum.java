package onlexnet.fin2set.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status1c5Enum {

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

    Status1c5Enum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Status1c5Enum fromValue(String value) {
        for (Status1c5Enum b : Status1c5Enum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
