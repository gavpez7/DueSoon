package com.Gaveen.duesoon.Controller;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Gaveen.duesoon.Entity.Task;
import com.Gaveen.duesoon.Enums.TaskStatus;
import com.Gaveen.duesoon.Repository.TaskRepository;
import com.Gaveen.duesoon.Service.UrgencyCalculator;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private final UrgencyCalculator calculator = new UrgencyCalculator();

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @GetMapping("/today")
    public List<Task> getToday() {
        LocalDateTime now = LocalDateTime.now();
        List<Task> open = taskRepository.findByStatusNot(TaskStatus.DONE);
        open.sort(Comparator.comparingDouble((Task t) -> calculator.calculate(t, now)).reversed());
        return open;
    }
}