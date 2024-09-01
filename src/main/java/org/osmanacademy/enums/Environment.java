package org.osmanacademy.enums;
public enum Environment {
    DEV("dev",EnvironmentType.LOWER),
    DEV2("dev2",EnvironmentType.LOWER),
    INT("int",EnvironmentType.LOWER),
    INT2("int2",EnvironmentType.LOWER),
    SIT("sit",EnvironmentType.LOWER),
    SIT2("sit2",EnvironmentType.LOWER),
    STG("stg",EnvironmentType.HIGHER),
    STG2("stg2",EnvironmentType.HIGHER),
    PERFORMANCE("performance",EnvironmentType.HIGHER),
    TRAINING("training",EnvironmentType.HIGHER),
    TRAINING2("training",EnvironmentType.HIGHER),
    PREPROD("preprod",EnvironmentType.HIGHER),
    PRODSUPP("prodsupport",EnvironmentType.HIGHER);

    private final String value;
    private final EnvironmentType environmentType;

    Environment(String value, EnvironmentType environmentType) {
        this.value = value;
        this.environmentType = environmentType;
    }

    public String getValue() {
        return value;
    }
    public EnvironmentType getEnvironmentType() {
        return environmentType;
    }

    public static Environment findByValue(String searchValue) {
        for (Environment env : Environment.values()) {
            if (env.value.equals(searchValue)) {
                return env;
            }
        }
        throw new IllegalArgumentException();
    }
    public enum EnvironmentType {
        LOWER, HIGHER
    }
}