package com.patrickannik02.tasktracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskRepository implements ITaskRepository {

    private final List<Task> tasks = new ArrayList<>();
    private int idCounter = 0;

    @Override
    public void save(Task task) {
        if (task.getId() == 0) {
            task.setId(idCounter++);
            tasks.add(task);
            idCounter++;
        } else {
            Task updatedTask = findById(task.getId());
            updatedTask.setDescription(task.getDescription());
            updatedTask.setStatus(task.getStatus());
            updatedTask.setUpdatedAt();
        }
    }

    @Override
    public void delete(Task task) {
        throw new UnsupportedOperationException("El método delete aún no está completamente implementado.");
    }

    @Override
    public Task findById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    @Override
    public List<Task> findAll() {
        throw new UnsupportedOperationException("El método findAll aún no está completamente implementado.");
    }

    @Override
    public List<Task> findAllByStatus(Status status) {
        throw new UnsupportedOperationException("El método findAllByStatus aún no está completamente implementado.");
    }
}
