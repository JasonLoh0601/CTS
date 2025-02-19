package com.test.cts.enums;

public enum OrderType {
    BID("bid"),
    ASK("ask");

    private String type;

    OrderType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static OrderType fromString(String type) {
        for (OrderType b : OrderType.values()) {
            if (b.type.equalsIgnoreCase(type)) {
                return b;
            }
        }
        return null;
    }

}
