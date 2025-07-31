package com.patrickannik02.tasktracker;

import java.util.List;

public interface ITaskRepository {

    public void save(Task task);

    public void delete(Task task);

    public Task findById(int id);

    public List<Task> findAll();

    public List<Task> findAllByStatus(Status status);
}