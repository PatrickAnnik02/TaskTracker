package com.patrickannik02.tasktracker;

import java.util.ArrayList;

public class TaskService {

    private final ITaskRepository taskRepository;

    public TaskService (InMemoryTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String description) {
        if (description == null) {
            throw new IllegalArgumentException("La descripción de la tarea no puede estar vacía.");
        }
        Task newTask = new Task(description);
        taskRepository.save(newTask);
    }

    public void updateTask(String description) {

    }

    public void deleteTask() {

    }

    public ArrayList<Task> tasks() {
        return null;
    }

    public ArrayList<Task> doneTasks() {
        return null;
    }

    public ArrayList<Task> pendingTasks() {
        return null;
    }
}
