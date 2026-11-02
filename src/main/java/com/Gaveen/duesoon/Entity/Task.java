package com.Gaveen.duesoon.Entity;

import java.time.LocalDateTime;

import com.Gaveen.duesoon.Enums.TaskStatus;
import com.Gaveen.duesoon.Enums.TaskType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @Enumerated(EnumType.STRING)
    private TaskType type;

    private LocalDateTime  dueAt;

    private int weightPercent;
    private double estimatedHours;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.TODO;
    
    @ManyToOne
    private Course course;






    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public TaskType getType() { return type; }
    public void setType(TaskType type) { this.type = type; }

    public LocalDateTime getdueAt() { return dueAt; }
    public void setdueAt(LocalDateTime dueAt) { this.dueAt = dueAt; }

    public int getweightPercent() { return weightPercent; }
    public void setweightPercent(int weightPercent) { this.weightPercent = weightPercent; }

    public double getHours() { return estimatedHours; }
    public void setHours(double estimatedHours) { this.estimatedHours = estimatedHours; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}