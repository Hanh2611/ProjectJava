package org.projects.entity.Enum;

public enum QuyCach {
    THUNG("Thùng"),
    CHAI("Chai"),
    TUI("Túi"),
    KG("Kg"),
    G("G"),
    HOP("Hộp"),
    KHAY("Khay"),
    GOI("Gói"),;

    private final String value;

    QuyCach(String value) {
        this.value = value;
    }

    public static QuyCach fromValue(String value) {
        for (QuyCach qc : values()) {
            if (qc.value.equalsIgnoreCase(value)) {
                return qc;
            }
        }
        throw new IllegalArgumentException("Unknown quy cách: " + value);
    }

    public String getValue() {
        return value;
    }
}
