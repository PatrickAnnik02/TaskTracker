package com.patrickannik02.tasktracker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public class JSONTaskRepository implements ITaskRepository {

    private final String filePath = "tasks.json"; // Definimos la ruta del archivo
    private final Gson gson; // Nuestro motor configurado
    private List<Task> tasks;
    private int idCounter = 0;

    public JSONTaskRepository() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()) 
                .setPrettyPrinting() 
                .create();

                this.tasks = new ArrayList<>();
                loadTasks();
    }

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

        saveTasks();
    }

    @Override
    public void delete(int id) {
        tasks.removeIf(task -> task.getId() == id);
        
        saveTasks();
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
        return this.tasks;
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

    private void loadTasks() {
        File file = new File(filePath);
        if (file.exists()) {
            try (FileReader fileReader = new FileReader(file)) {

                // Esto crea un objeto que representa el tipo "ArrayList de Tareas"
                Type listType = new TypeToken<ArrayList<Task>>(){}.getType();
                
                this.tasks = gson.fromJson(fileReader, listType);

                if (this.tasks == null) {
                    this.tasks = new ArrayList<>();
                }

                for (Task task : this.tasks) {
                    if (task.getId() > this.idCounter) {
                        this.idCounter = task.getId();
                    }
                }
                
            } catch(FileNotFoundException e) {
                System.err.println("Error: Archivo no encontrado.");
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            this.tasks = new ArrayList<>();
        }
    }

    private void saveTasks() {
        File file = new File(filePath);
        
        try (FileWriter fileWriter = new FileWriter(file)) {
            
            gson.toJson(this.tasks, fileWriter);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
