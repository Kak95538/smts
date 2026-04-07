package com.example.stms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.stms.TaskService;
import com.example.stms.entity.Task;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*") // allow frontend later
public class TaskController {

    @Autowired
    private TaskService service;

    // ✅ Create Task
    @PostMapping
    public Task create(@RequestBody Task task) {
        return service.createTask(task);
    }

    // ✅ Get All Tasks
    @GetMapping
    public List<Task> getAll() {
        return service.getAllTasks();
    }

    // ✅ Update Task
    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return service.updateTask(id, task);
    }

    // ✅ Delete Task
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteTask(id);
        return "Deleted successfully";
    }
}