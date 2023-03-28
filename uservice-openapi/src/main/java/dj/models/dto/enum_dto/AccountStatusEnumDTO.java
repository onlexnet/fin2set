package dj.models.dto.enum_dto;

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
}
