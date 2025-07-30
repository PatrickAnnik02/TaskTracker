package com.patrickannik02.tasktracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;



public class TaskTest {
    // debería mejorar algunos test y la construcción de task.
    Task task;

    @BeforeEach
    void setUp() {
        task = new Task(1, "Tarea de prueba", Status.TODO, LocalDate.now(), LocalDate.now());
    }

    @Test
    @DisplayName("getId Test")
    void testGetId() {
        assertEquals(1, task.getId());
    }

    @Test
    @DisplayName("getDescription Test")
    void testGetDescription() {
        assertEquals("Tarea de prueba", task.getDescription());
    }

    @Test
    @DisplayName("setDescription Test")
    void testSetDescription() {
        task.setDescription("new description");
        assertEquals("new description", task.getDescription());
    }

    @Test
    @DisplayName("getStatus Test")
    void testGetStatus() {
        assertEquals(Status.TODO, task.getStatus());
    }

    @Test
    @DisplayName("setStatus Test")
    void testSetStatus() {
        task.setStatus(Status.DONE);
        assertEquals(Status.DONE, task.getStatus());
    }

    @Test
    @DisplayName("getCreatedAt Test")
    void testGetCreatedAt() {
        assertEquals(LocalDate.now(), task.getCreatedAt());
    }

    @Test
    @DisplayName("getUpdatedAt Test")
    void testGetUpdatedAt() {
        assertEquals(LocalDate.now(), task.getUpdatedAt());
    }

    @Test
    @DisplayName("setUpdatedAt Test")
    void testSetUpdatedAt() {
        // En realidad debería testearlo de otra manera, pero mientras tanto...
        task.setUpdatedAt(LocalDate.now());
        assertEquals(LocalDate.now(), task.getUpdatedAt());
    }
}
