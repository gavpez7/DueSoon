package com.Gaveen.duesoon.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.Gaveen.duesoon.Entity.Task;

class UrgencyCalculatorTest {

    private final UrgencyCalculator calculator = new UrgencyCalculator();
    private final LocalDateTime now = LocalDateTime.of(2026, 11, 11, 0, 0);

    private Task task(int weight, double hours, LocalDateTime due) {
        Task t = new Task();
        t.setWeightPercent(weight);
        t.setEstimatedHours(hours);
        t.setDueAt(due);
        return t;
    }

    @Test
    void heavyTaskDueInFourDays() {
        double score = calculator.calculate(task(15, 8, now.plusDays(4)), now);
        assertEquals(3.5, score, 0.001);
    }

    @Test
    void quizDueInOneDay() {
        double score = calculator.calculate(task(5, 2, now.plusDays(1)), now);
        assertEquals(2.5, score, 0.001);
    }


    @Test
    void quizDueInTenDay() {
        double score = calculator.calculate(task(40, 20, now.plusDays(10)), now);
        assertEquals(6, score, 0.001);
    }

    @Test
    void quizDueInOneHour() {
        double score = calculator.calculate(task(10, 1, now.plusHours(1)), now);
        assertEquals(5, score, 0.001);
    }

}