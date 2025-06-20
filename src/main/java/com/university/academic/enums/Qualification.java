package com.university.academic.enums;

public enum Qualification {
    GRADUATION("Graduação"),
    SPECIALIZATION("Especialização"),
    MASTERS("Mestrado"),
    DOCTORATE("Doutorado");

    private final String description;

    Qualification(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
