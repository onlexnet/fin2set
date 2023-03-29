package dj.models.dto.enum_dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountStatusEnumDTO {

    DISCOVERED("DISCOVERED"),

    PROCESSING("PROCESSING"),

    READY("READY"),

    ERROR("ERROR"),

    SUSPENDED("SUSPENDED");

    private String value;

    AccountStatusEnumDTO(String value) {
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
    public static AccountStatusEnumDTO fromValue(String value) {
        for (AccountStatusEnumDTO b : AccountStatusEnumDTO.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
