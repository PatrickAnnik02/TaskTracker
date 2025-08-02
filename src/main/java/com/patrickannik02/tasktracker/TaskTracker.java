package com.patrickannik02.tasktracker;

public class TaskTracker {
    public static void main(String[] args) {
        InMemoryTaskRepository taskRepository = new InMemoryTaskRepository();
        TaskService taskService = new TaskService(taskRepository);
        CommandProcessor commandProcessor = new CommandProcessor(taskService);
    }
}
