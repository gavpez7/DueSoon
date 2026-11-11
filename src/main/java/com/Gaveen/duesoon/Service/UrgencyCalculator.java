package com.Gaveen.duesoon.Service;

import java.time.Duration;
import java.time.LocalDateTime;

import com.Gaveen.duesoon.Entity.Task;

public class UrgencyCalculator {

    public double calculate(Task task, LocalDateTime now) {
        double hoursUntilDue = Duration.between(now, task.getDueAt()).toMinutes() / 60.0;
        double daysLeft = Math.max(hoursUntilDue / 24, 0.25);
        double score = (task.getWeightPercent() / 10.0) + (task.getEstimatedHours() / daysLeft);
        return score;
    }
}

