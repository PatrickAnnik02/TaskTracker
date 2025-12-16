package com.patrickannik02.tasktracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class JSONTaskRepositoryTest {
    
    private static final String TEST_FILE = "test_tasks.json";
    private JSONTaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = new JSONTaskRepository(TEST_FILE);
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    @DisplayName("Should save task to JSON file and load it back")
    void testPersistence() {
        Task task = new Task("Persistent Task");
        repository.save(task);

        JSONTaskRepository newRepositoryInstance = new JSONTaskRepository(TEST_FILE);

        List<Task> loadedTasks = newRepositoryInstance.findAll();

        assertEquals(1, loadedTasks.size());
        assertEquals("Persistent Task", loadedTasks.get(0).getDescription());
    }

    @Test
    @DisplayName("Should handle empty file correctly")
    void testEmptyLoad() {
        List<Task> tasks = repository.findAll();
        assertTrue(tasks.isEmpty());
    }
}