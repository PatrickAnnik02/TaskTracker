package com.patrickannik02.tasktracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryTaskRepositoryTest {

    InMemoryTaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        // Create repository tasks
        taskRepository = new InMemoryTaskRepository();
        // Create TaskService
        TaskService taskService = new TaskService(taskRepository);
        // Add tasks to the repository
        taskService.addTask("Tarea 1");
        taskService.addTask("Tarea 2");
        taskService.addTask("Tarea 3");
        taskRepository.findById(1).get().setStatus(Status.DONE);
    }

    @Test
    @DisplayName("save test")
    public void testSave() {
        assertEquals(3, taskRepository.findAll().size());
        taskRepository.save(new Task("Tarea 4"));
        assertEquals(4, taskRepository.findAll().size());
    }

    @Test
    @DisplayName("delete test")
    public void testDelete() {
        taskRepository.delete(1);
        assertEquals(2, taskRepository.findAll().size());
        assertEquals(true, taskRepository.findById(1).isEmpty());
    }

    @Test
    @DisplayName("findById test")
    public void testFindById() {
        assertEquals(1, taskRepository.findById(1).get().getId());
        assertEquals(2, taskRepository.findById(2).get().getId());
        assertEquals(3, taskRepository.findById(3).get().getId());
    }

    @Test
    @DisplayName("findAll test")
    public void testFindAll() {
        assertEquals(3, taskRepository.findAll().size());
    }

    @Test
    @DisplayName("findByStatus test")
    public void testFindByStatus() {
        assertEquals(1, taskRepository.findByStatus(Status.DONE).size());
    }
}
