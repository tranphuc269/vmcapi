package com.vai.vmcapi.enums;

public enum EDrivetrain {
    AWD("AWD"),
    FWD("FWD"),
    RWD("RWD"),
    FourWD("4WD");

    private final String value;

    EDrivetrain(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
