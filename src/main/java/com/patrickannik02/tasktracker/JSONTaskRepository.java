package com.patrickannik02.tasktracker;

import java.util.List;
import java.util.Optional;

public class JSONTaskRepository implements ITaskRepository {
    public void save(Task task) {

    }

    public void delete(int id) {

    }

    public Optional<Task> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Task> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Task> findByStatus(Status status) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
