package com.patrickannik02.tasktracker;

import java.util.List;

public class TaskService {

    private final ITaskRepository taskRepository;

    public TaskService (InMemoryTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description of task cannot be empty.");
        }
        Task newTask = new Task(description);
        taskRepository.save(newTask);
    }

    public void updateTask(int id, String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description of task cannot be empty.");
        } else {
            taskRepository.findById(id).get().setDescription(description);
        }
    }

    public void deleteTask(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id of task must be greater than zero.");
        } else {
            taskRepository.delete(id);
        }
    }

    public void markAsToDo(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id of task must be greater than zero.");
        } else {
            taskRepository.findById(id).get().setStatus(Status.TODO);
        }
    }

    public void markAsDone(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id of task must be greater than zero.");
        } else {
            taskRepository.findById(id).get().setStatus(Status.DONE);
        }
    }

    public void markAsInProgress(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id of task must be greater than zero.");
        } else {
            taskRepository.findById(id).get().setStatus(Status.IN_PROGRESS);
        }
    }

    public List<Task> tasks() {
        return taskRepository.findAll();
    }

    public List<Task> doneTasks() {
        return taskRepository.findByStatus(Status.DONE);
    }

    public List<Task> pendingTasks() {
        return taskRepository.findByStatus(Status.TODO);
    }

    public List<Task> inProgressTasks() {
        return taskRepository.findByStatus(Status.IN_PROGRESS);
    }
}
