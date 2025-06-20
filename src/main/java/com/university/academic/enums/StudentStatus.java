package com.university.academic.enums;

public enum StudentStatus {
    ENROLLED("Matriculado"),
    LOCKED("Trancado"),
    GRADUATED("Formado"),
    DROPPED_OUT("Evadido");

    private final String description;

    StudentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
