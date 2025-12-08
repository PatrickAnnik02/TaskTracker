package com.patrickannik02.tasktracker;

import java.util.List;

public class TaskService {

    private final ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
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
        if (description == null || description.trim().isEmpty()) { 
            throw new IllegalArgumentException("Description of task cannot be empty.");
        }

        Task updatedTask = taskRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));
        
        updatedTask.setDescription(description);
        
        taskRepository.save(updatedTask);
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
        }
        
        Task markAsTodoTask = taskRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));
        
        markAsTodoTask.setStatus(Status.TODO);

        taskRepository.save(markAsTodoTask);
    }

    public void markAsDone(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id of task must be greater than zero.");
        }
        
        Task markAsDoneTask = taskRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));
        
        markAsDoneTask.setStatus(Status.DONE);

        taskRepository.save(markAsDoneTask);
    }

    public void markAsInProgress(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id of task must be greater than zero.");
        }
        
        Task markAsInProgressTask = taskRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));
        
        markAsInProgressTask.setStatus(Status.IN_PROGRESS);

        taskRepository.save(markAsInProgressTask);
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