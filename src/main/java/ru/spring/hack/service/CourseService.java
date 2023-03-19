package ru.spring.hack.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.spring.hack.exeption.ResourceNotFoundException;
import ru.spring.hack.model.Course;
import ru.spring.hack.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // CRUD операции
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course course) {
        Course existingCourse = getCourseById(id);
        BeanUtils.copyProperties(course, existingCourse, "id");
        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}