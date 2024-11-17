package com.vai.vmcapi.enums;

public enum ETransmission {
    AUTOMATIC("Automatic"),
    HYBRID("Hybird"),
    OTHER("Other"),
    MANUAL("Manual");

    private final String value;

    ETransmission(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
