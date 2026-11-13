package com.Gaveen.duesoon.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.Gaveen.duesoon.Entity.Task;

public class WorkloadCalculator {

    public Map<LocalDate, Double> hoursPerDay(List<Task> tasks) {
        Map<LocalDate, Double> result = new TreeMap<>();

        for (Task task : tasks) {
            LocalDate dueDate = task.getDueAt().toLocalDate();

            result.merge(dueDate, task.getEstimatedHours(), Double::sum);
        }

        return result;
    }
}