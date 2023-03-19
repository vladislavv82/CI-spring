package ru.spring.hack.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.spring.hack.exeption.ResourceNotFoundException;
import ru.spring.hack.model.Test;
import ru.spring.hack.repository.TestRepository;

import java.util.List;

@Service
public class TestService {
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    // CRUD операции
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTestById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test not found with id: " + id));
    }

    public Test createTest(Test test) {
        return testRepository.save(test);
    }

    public Test updateTest(Long id, Test test) {
        Test existingTest = getTestById(id);
        BeanUtils.copyProperties(test, existingTest, "id");
        return testRepository.save(existingTest);
    }

    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }

}