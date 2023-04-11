package onlexnet.fin2set.domain.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountStatusEnum {

    DISCOVERED("DISCOVERED"),

    PROCESSING("PROCESSING"),

    READY("READY"),

    ERROR("ERROR"),

    SUSPENDED("SUSPENDED");

    private String value;

    AccountStatusEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static AccountStatusEnum fromValue(String value) {
        for (AccountStatusEnum b : AccountStatusEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
