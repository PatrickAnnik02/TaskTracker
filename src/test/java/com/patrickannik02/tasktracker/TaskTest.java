package com.patrickannik02.tasktracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

public class TaskTest {
    Task newTask;
    Task existingTask;

    @BeforeEach
    void setUp() {
        // basic constructor
        newTask = new Task("Tarea 1");
        // complex constructor
        existingTask = new Task(2, "Tarea 2", Status.TODO, LocalDate.now(), LocalDate.now());
    }

    @Test
    @DisplayName("getId Test")
    void testGetId() {
        // basic constructor task
        assertEquals(0, newTask.getId());
        // complex constructor task
        assertEquals(2, existingTask.getId());
        // incorrect id task
        assertThrows(IllegalArgumentException.class, () -> new Task(-1, "Tarea erronea", Status.TODO, LocalDate.now(), LocalDate.now()));
    }

    @Test
    @DisplayName("getDescription Test")
    void testGetDescription() {
        assertEquals("Tarea 1", newTask.getDescription());
        assertEquals("Tarea 2", existingTask.getDescription());
    }

    @Test
    @DisplayName("setDescription Test")
    void testSetDescription() {
        newTask.setDescription("new description 1");
        existingTask.setDescription("new description 2");
        assertEquals("new description 1", newTask.getDescription());
        assertEquals("new description 2", existingTask.getDescription());
        assertThrows(IllegalArgumentException.class, () -> new Task(""));
    }

    @Test
    @DisplayName("getStatus Test")
    void testGetStatus() {
        assertEquals(Status.TODO, newTask.getStatus());
        assertEquals(Status.TODO, existingTask.getStatus());
    }

    @Test
    @DisplayName("setStatus Test")
    void testSetStatus() {
        newTask.setStatus(Status.DONE);
        assertEquals(Status.DONE, newTask.getStatus());
        existingTask.setStatus(Status.IN_PROGRESS);
        assertEquals(Status.IN_PROGRESS, existingTask.getStatus());
        assertThrows(IllegalArgumentException.class, () -> new Task(1, "Incorrect task", null,  LocalDate.now(), LocalDate.now()));
    }

    @Test
    @DisplayName("getCreatedAt Test")
    void testGetCreatedAt() {
        assertEquals(LocalDate.now(), newTask.getCreatedAt());
        assertEquals(LocalDate.now(), existingTask.getCreatedAt());
    }

    @Test
    @DisplayName("getUpdatedAt Test")
    void testGetUpdatedAt() {
        newTask.setDescription("updated task");
        existingTask.setDescription("updated task");
        assertEquals(LocalDate.now(), newTask.getUpdatedAt());
        assertEquals(LocalDate.now(), existingTask.getUpdatedAt());
    }

    @Test
    @DisplayName("setUpdatedAt Test")
    void testSetUpdatedAt() {
        newTask.setDescription("updated task");
        assertEquals(LocalDate.now(), newTask.getUpdatedAt());
    }
}
