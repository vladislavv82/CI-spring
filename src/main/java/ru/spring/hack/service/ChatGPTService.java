package ru.spring.hack.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

@Service
public class ChatGPTService {

    private final String API_KEY = "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";
    private final String OPENAI_API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private final RestTemplate restTemplate;

    public ChatGPTService() {
        restTemplate = new RestTemplate();
    }

    public String generateTest(String lectureText) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(API_KEY);

        String prompt = "Создайте тест на основе следующей лекции: " + lectureText;
        String requestBody = String.format("{\"prompt\": \"%s\", \"max_tokens\": 100}", prompt);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            // Обработка ответа и извлечение сгенерированного теста
            return extractGeneratedTest(response.getBody());
        }

        return "Не удалось сгенерировать тест";
    }

    private String extractGeneratedTest(String responseJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(responseJson);
            JsonNode choicesNode = jsonResponse.get("choices");

            if (choicesNode != null && choicesNode.isArray() && choicesNode.size() > 0) {
                JsonNode firstChoiceNode = choicesNode.get(0);
                JsonNode textNode = firstChoiceNode.get("text");

                if (textNode != null) {
                    return textNode.asText().trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Не удалось извлечь сгенерированный тест";
    }
}
