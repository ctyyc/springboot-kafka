package com.mk.basic.common.code;

public enum ConsumerGroup {
    TEST("test", "test"),
    MEMBER("member", "member");

    public final String value;
    public final String label;

    ConsumerGroup(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
