package com.vai.vmcapi.enums;

public enum ETransmission {
    AUTOMATIC("Automatic"),
    MANUAL("Manual");

    private final String value;

    ETransmission(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
