package ru.spring.hack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.hack.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
