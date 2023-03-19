package ru.spring.hack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.hack.model.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
}