package com.patrickannik02.tasktracker;

import java.time.LocalDate;

public class Task {

    private int id;
    private String description;
    private Status status;
    private final LocalDate createdAt;
    private LocalDate updatedAt;

    public Task(String description) {
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
