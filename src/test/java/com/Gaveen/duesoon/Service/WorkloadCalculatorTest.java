package com.Gaveen.duesoon.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.Gaveen.duesoon.Entity.Task;

class WorkloadCalculatorTest {

    private final WorkloadCalculator calculator = new WorkloadCalculator();

    private Task task(double hours, LocalDateTime due) {
        Task t = new Task();
        t.setEstimatedHours(hours);
        t.setDueAt(due);
        return t;
    }

    @Test
    void tasksOnTheSameDayAreAdded() {
        List<Task> tasks = List.of(
            task(3, LocalDateTime.of(2026, 11, 20, 10, 0)),
            task(4, LocalDateTime.of(2026, 11, 20, 23, 59))
        );

        Map<LocalDate, Double> result = calculator.hoursPerDay(tasks);

        assertEquals(7.0, result.get(LocalDate.of(2026, 11, 20)), 0.001);
    }

    @Test
    void tasksOnDifferentDayAreAdded() {
        List<Task> tasks = List.of(
            task(3, LocalDateTime.of(2026, 11, 20, 10, 0)),
            task(2, LocalDateTime.of(2026, 11, 22, 23, 59))
        );

        Map<LocalDate, Double> result = calculator.hoursPerDay(tasks);

        assertEquals(3.0, result.get(LocalDate.of(2026, 11, 20)), 0.001);
        assertEquals(2.0, result.get(LocalDate.of(2026, 11, 22)), 0.001);
        assertEquals(2, result.size());
    }


    @Test
    void emptyListGivesEmptyMap() {
        Map<LocalDate, Double> result = calculator.hoursPerDay(List.of());

        assertTrue(result.isEmpty());
    }
}