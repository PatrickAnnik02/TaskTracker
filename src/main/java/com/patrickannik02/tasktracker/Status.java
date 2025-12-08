package com.patrickannik02.tasktracker;

public enum Status {
    DONE("Done"),
    IN_PROGRESS("In progress"),
    TODO("To do");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
