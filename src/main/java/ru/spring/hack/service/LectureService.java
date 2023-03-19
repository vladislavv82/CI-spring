package ru.spring.hack.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.spring.hack.exeption.ResourceNotFoundException;
import ru.spring.hack.model.Lecture;
import ru.spring.hack.repository.LectureRepository;

import java.util.List;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    // CRUD операции
    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

        public Lecture getLectureById(Long id) {
        return lectureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lecture not found with id: " + id));
    }

    public Lecture createLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public Lecture updateLecture(Long id, Lecture lecture) {
        Lecture existingLecture = getLectureById(id);
        BeanUtils.copyProperties(lecture, existingLecture, "id");
        return lectureRepository.save(existingLecture);
    }

    public void deleteLecture(Long id) {
        lectureRepository.deleteById(id);
    }
}