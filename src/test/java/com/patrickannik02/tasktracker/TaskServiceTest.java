package com.patrickannik02.tasktracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

public class TaskServiceTest {

    private TaskService service;
    private ITaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
        service = new TaskService(repository);
    }

    @Test
    @DisplayName("Add task should save it to repository")
    void testAddTask() {
        service.addTask("Learn JUnit");

        assertEquals(1, repository.findAll().size());
        assertEquals("Learn JUnit", repository.findAll().get(0).getDescription());
    }

    @Test
    @DisplayName("Update task should change description")
    void testUpdateTask() {
        service.addTask("old task");
        int id = repository.findAll().get(0).getId();

        service.updateTask(id, "new task");

        Task updateTask = repository.findById(id).get();
        assertEquals("new task", updateTask.getDescription());
    }

    @Test
    @DisplayName("Update non-existent task should throw exception")
    void testUpdateNonExistentTask() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateTask(999, "Esto no debería funcionar");
        });

        assertEquals("Task not found with id: 999", exception.getMessage());
    }

    @Test
    @DisplayName("Delete task should remove it from repository")
    void testDeleteTask() {
        service.addTask("Task");
        int id = repository.findAll().get(0).getId();

        service.deleteTask(id);
        
        assertTrue(repository.findById(id).isEmpty());
    }

    @Test
    @DisplayName("Mark as 'done' should change status and update timestamp")
    void testMarkAsDone() throws InterruptedException {
        service.addTask("task");
        Task task = repository.findAll().get(0);
        LocalDateTime originalTime = task.getUpdatedAt();

        Thread.sleep(10);

        service.markAsDone(task.getId());

        assertEquals(Status.DONE, task.getStatus());
        assertTrue(task.getUpdatedAt().isAfter(originalTime));
    }

    @Test
    @DisplayName("Mark as 'in-progress' should change status")
    void testMarkAsInProgress() throws InterruptedException {
        service.addTask("task");
        Task task = repository.findAll().get(0);
        LocalDateTime originalTime = task.getUpdatedAt();

        Thread.sleep(10);

        service.markAsInProgress(task.getId());

        assertEquals(Status.IN_PROGRESS, task.getStatus());
        assertTrue(task.getUpdatedAt().isAfter(originalTime));
    }

    @Test
    @DisplayName("Mark as 'to do' should change status")
    void testMarkAsToDo() throws InterruptedException {
        service.addTask("task");
        Task task = repository.findAll().get(0);
        LocalDateTime originalTime = task.getUpdatedAt();

        Thread.sleep(10);

        service.markAsToDo(task.getId());

        assertEquals(Status.TODO, task.getStatus());
        assertTrue(task.getUpdatedAt().isAfter(originalTime));
    }

    @Test
    @DisplayName("List all tasks")
    void testTasks() {
        service.addTask("task 1");
        service.addTask("task 2");
        service.addTask("task 3");

        service.markAsInProgress(2);
        service.markAsDone(3);

        List<Task> tasks = service.tasks();

        assertEquals(3, tasks.size());

        Task task1 = repository.findById(1).orElseThrow();
        Task task2 = repository.findById(2).orElseThrow();
        Task task3 = repository.findById(3).orElseThrow();

        assertEquals(List.of(task1, task2, task3), tasks);
    }

    @Test
    @DisplayName("List only done tasks")
    void testDoneTasks() {
        service.addTask("task 1");
        service.addTask("task 2");
        service.addTask("task 3");

        service.markAsInProgress(2);
        service.markAsDone(3);

        List<Task> doneTasks = service.doneTasks();

        assertEquals(1, doneTasks.size());

        Task task3 = repository.findById(3).orElseThrow();

        assertEquals(List.of(task3), doneTasks);
    }

    @Test
    @DisplayName("List only pending tasks")
    void testPendingTasks() {
        service.addTask("task 1");
        service.addTask("task 2");
        service.addTask("task 3");

        service.markAsInProgress(2);
        service.markAsDone(3);

        List<Task> pendingTasks = service.pendingTasks();

        assertEquals(1, pendingTasks.size());

        Task task1 = repository.findById(1).orElseThrow();

        assertEquals(List.of(task1), pendingTasks);
    }

    @Test
    @DisplayName("List only in-progress tasks")
    void testInProgressTasks() {
        service.addTask("task 1");
        service.addTask("task 2");
        service.addTask("task 3");

        service.markAsInProgress(2);
        service.markAsDone(3);

        List<Task> inProgressTasks = service.inProgressTasks();

        assertEquals(1, inProgressTasks.size());

        Task task2 = repository.findById(2).orElseThrow();

        assertEquals(List.of(task2), inProgressTasks);
    }
}
