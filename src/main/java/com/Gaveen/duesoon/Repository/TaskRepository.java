package com.Gaveen.duesoon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Gaveen.duesoon.Entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}