package com.patrickannik02.tasktracker;

import java.util.Scanner;

public class TaskTracker {
    public static void main(String[] args) {
        InMemoryTaskRepository taskRepository = new InMemoryTaskRepository();
        TaskService taskService = new TaskService(taskRepository);
        CommandProcessor commandProcessor = new CommandProcessor(taskService);

        System.out.println("Welcome to TaskTracker.");
        System.out.println("Write 'help' to commands or 'exit' to exit.");
        commandProcessor.execute(args);
    }
}
