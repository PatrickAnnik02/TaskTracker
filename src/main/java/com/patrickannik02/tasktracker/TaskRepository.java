package com.patrickannik02.tasktracker;

import java.util.ArrayList;

public class TaskRepository {

    private ArrayList<Task> tasks;

    public TaskRepository(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        return "TaskRepository{" +
                "tasks=" + tasks +
                '}';
    }
}
