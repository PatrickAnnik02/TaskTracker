package com.patrickannik02.tasktracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryTaskRepository implements ITaskRepository {

    private final List<Task> tasks = new ArrayList<>();
    private int idCounter = 0;

    @Override
    public void save(Task task) {
        if (task.getId() == 0) {
            task.setId(++idCounter);
            tasks.add(task);
        } else {
            Optional<Task> updatedTask = findById(task.getId());
            if (updatedTask.isPresent()) {
                updatedTask.get().setDescription(task.getDescription());
                updatedTask.get().setStatus(task.getStatus());
            } else {
                throw new IllegalArgumentException("Task with id " + task.getId() + " does not exist");
            }
        }
    }

    @Override
    public void delete(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    @Override
    public Optional<Task> findById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return Optional.of(task);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public List<Task> findByStatus(Status status) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getStatus().equals(status)) {
                result.add(task);
            }
        }
        return result;
    }
}
