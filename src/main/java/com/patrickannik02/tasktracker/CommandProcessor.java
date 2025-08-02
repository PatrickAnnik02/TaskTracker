package com.patrickannik02.tasktracker;

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
                case "add":
                    if (args.length != 2) {
                        throw new IllegalArgumentException("cannot read the command.");
                    } else {
                        taskService.addTask(args[1]);
                    }
                    break;
                case "delete":
                    if (args.length != 2) {
                        throw new IllegalArgumentException("cannot read the command.");
                    } else {
                        int id = Integer.parseInt(args[1]);
                        taskService.deleteTask(id);
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
                        taskService.tasks();
                    } else if (args.length == 2) {
                        switch (args[1]) {
                            case "done":
                                taskService.doneTasks();
                                break;
                            case "in-progress":
                                taskService.inProgressTasks();
                                break;
                            case "todo":
                                taskService.pendingTasks();
                                break;
                        }
                    } else {
                        throw new IllegalArgumentException("cannot read the command.");
                    }
            }
        }
    }
}
