package com.patrickannik02.tasktracker;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {

    public void save(Task task);

    public void delete(int id);

    public Optional<Task> findById(int id);

    public List<Task> findAll();

    public List<Task> findByStatus(Status status);
}