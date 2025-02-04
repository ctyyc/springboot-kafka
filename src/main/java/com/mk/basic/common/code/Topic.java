package com.mk.basic.common.code;

public enum Topic {
    TEST("test", "test"),
    MEMBER("member", "member");

    public final String value;
    public final String label;

    Topic(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
