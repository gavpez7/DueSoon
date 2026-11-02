package com.Gaveen.duesoon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Gaveen.duesoon.Entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}