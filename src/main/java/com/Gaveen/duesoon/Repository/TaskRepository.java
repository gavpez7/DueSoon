package com.Gaveen.duesoon.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Gaveen.duesoon.Entity.Task;
import com.Gaveen.duesoon.Enums.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {

    //find the tasks where the status is != to the one provided
    List<Task> findByStatusNot(TaskStatus status);
}