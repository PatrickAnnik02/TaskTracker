package com.patrickannik02.tasktracker;

import java.time.LocalDateTime;

public class Task {

    private int id;
    private String description;
    private Status status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor para tareas nuevas
    public Task(String description) {
        this.id = 0;
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null");
        } else {
            this.description = description;
        }
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Constructor para tareas existentes
    public Task(int id, String description, Status status,  LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id cannot be negative or zero");
        } else {
            this.id = id;
        }
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        } else {
            this.description = description;
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        } else {
            this.status = status;
        }
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        } else {
            this.id = id;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        } else {
            this.description = description;
        }
        setUpdatedAt();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        } else {
            this.status = status;
        }
        setUpdatedAt();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
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
