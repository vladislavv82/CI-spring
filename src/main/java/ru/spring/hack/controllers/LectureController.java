package ru.spring.hack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spring.hack.model.Lecture;
import ru.spring.hack.service.LectureService;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    // CRUD операции
    @GetMapping
    public ResponseEntity<List<Lecture>> getAllLectures() {
        return ResponseEntity.ok(lectureService.getAllLectures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lecture> getLectureById(@PathVariable Long id) {
        return ResponseEntity.ok(lectureService.getLectureById(id));
    }

    @PostMapping
    public ResponseEntity<Lecture> createLecture(@RequestBody Lecture lecture) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lectureService.createLecture(lecture));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lecture> updateLecture(@PathVariable Long id, @RequestBody Lecture lecture) {
        return ResponseEntity.ok(lectureService.updateLecture(id, lecture));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLecture(@PathVariable Long id) {
        lectureService.deleteLecture(id);
        return ResponseEntity.noContent().build();
    }

}