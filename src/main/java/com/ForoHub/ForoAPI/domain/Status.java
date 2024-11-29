package com.ForoHub.ForoAPI.domain;

public enum Status {
    ACTIVE("active"),
    INACTIVE("inactive"),
    CLOSE("close");

    private String value;
    private Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
