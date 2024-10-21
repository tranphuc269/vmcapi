package com.vai.vmcapi.enums;

public enum ECarStatus {
    NEW("New"),
    OLD("Old");

    private final String value;

    ECarStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
