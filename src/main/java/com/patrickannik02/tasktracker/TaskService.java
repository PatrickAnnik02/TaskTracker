package com.patrickannik02.tasktracker;

import java.util.ArrayList;

public class TaskService {

    private Task task;
    private TaskRepository taskRepository;

    public TaskService (TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String description) {

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
