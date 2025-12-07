package com.patrickannik02.tasktracker;

import java.util.ArrayList;
import java.util.List;

public class CommandProcessor {
    private final TaskService taskService;

    public CommandProcessor(TaskService taskService) {
        if (taskService == null) {
            throw new IllegalArgumentException("taskService cannot be null");
        } else {
            this.taskService = taskService;
        }
    }

    public void execute(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("task argument cannot be empty.");
        } else {
            switch (args[0]) {
                case "exit":
                    if (args.length != 1) {
                        throw new IllegalArgumentException("cannot read the command");
                    } else {
                        return;
                    }
                case "help":
                    if (args.length != 1) {
                        throw new IllegalArgumentException("cannot read the command.");
                    } else {
                        System.out.println("usage: task-cli [option] [arg(s)]");
                        System.out.println("add \"description\"                   - Add new task.");
                        System.out.println("update <id> \"description\"           - Update task with specified id.");
                        System.out.println("delete <id>                         - Delete task with specified id.");
                        System.out.println("mark-todo <id>                      - Mark as to do task with specified id.");
                        System.out.println("mark-in-progress <id>               - Mark as in progress task with specified id.");
                        System.out.println("mark-done <id>                      - Mark as done task with specified id.");
                        System.out.println("list                                - List all tasks.");
                        System.out.println("list done                           - List all tasks done.");
                        System.out.println("list todo                           - List all tasks to do.");
                        System.out.println("list in-progress                    - List all tasks done.");
                    }
                    break;
                case "add":
                    if (args.length != 2) {
                        throw new IllegalArgumentException("cannot read the command.");
                    } else {
                        taskService.addTask(args[1]);
                        System.out.println("Task '" + args[1] + "' added successfully.");
                    }
                    break;
                case "delete":
                    if (args.length != 2) {
                        throw new IllegalArgumentException("cannot read the command.");
                    } else {
                        int id = Integer.parseInt(args[1]);
                        taskService.deleteTask(id);
                        System.out.println("Task with ID " + id + " deleted successfully.");
                    }
                    break;
                case "update":
                    if (args.length != 3) {
                        throw new IllegalArgumentException("cannot read the command.");
                    } else {
                        int id = Integer.parseInt(args[1]);
                        taskService.updateTask(id, args[2]);
                    }
                    break;
                case "mark-in-progress":
                    if (args.length != 2) {
                        throw new IllegalArgumentException("cannot read the command.");
                    } else {
                        int id = Integer.parseInt(args[1]);
                        taskService.markAsInProgress(id);
                    }
                    break;
                case "mark-done":
                    if (args.length != 2) {
                        throw new IllegalArgumentException("cannot read the command.");
                    } else {
                        int id = Integer.parseInt(args[1]);
                        taskService.markAsDone(id);
                    }
                    break;
                case "list":
                    if (args.length == 1) {
                        List<Task> allTasks = taskService.tasks();
                        printTasks(allTasks);
                    } else if (args.length == 2) {
                        switch (args[1]) {
                            case "done":
                                List<Task> doneTasks = taskService.doneTasks();
                                printTasks(doneTasks);
                                break;
                            case "in-progress":
                                List<Task> inProgressTasks = taskService.inProgressTasks();
                                printTasks(inProgressTasks);
                                break;
                            case "todo":
                                List<Task> todoTasks = taskService.pendingTasks();
                                printTasks(todoTasks);
                                break;
                        }
                    } else {
                        throw new IllegalArgumentException("cannot read the command.");
                    }
            }
        }
    }

    private void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are not any tasks to show.");
            return;
        }
        System.out.println("--- Task list ---");
        for (Task task : tasks) {
            System.out.println("ID: " + task.getId() + " | [" + task.getStatus() + "] " + task.getDescription());
            System.out.println("Created: " + task.getCreatedAt() + " | Updated: " + task.getUpdatedAt() + "\n");
        }
    }
}
