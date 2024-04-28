package org.osmanacademy.enums;
public enum Environment {
    DEV("dev"),
    INT("int"),
    SIT("sit"),
    UAT("uat"),
    STG("stg");

    private String value;

    Environment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Environment findByValue(String searchValue) {
        for (Environment env : Environment.values()) {
            if (env.value.equals(searchValue)) {
                return env;
            }
        }
        throw new IllegalArgumentException();
    }
}