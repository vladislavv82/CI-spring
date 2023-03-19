package ru.spring.hack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.hack.model.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}