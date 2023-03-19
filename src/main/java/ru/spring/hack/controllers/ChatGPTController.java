package ru.spring.hack.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spring.hack.service.ChatGPTService;

@RestController
@RequestMapping("/api")
public class ChatGPTController {

    private final ChatGPTService chatGPTService; // Замените YourService на имя вашего сервиса

    public ChatGPTController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @PostMapping("/generate-test")
    public ResponseEntity<String> generateTest(@RequestBody String lectureText) {
        String generatedTest = chatGPTService.generateTest(lectureText);

        if (generatedTest.equals("Не удалось сгенерировать тест")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generatedTest);
        } else {
            return ResponseEntity.ok(generatedTest);
        }
    }
}