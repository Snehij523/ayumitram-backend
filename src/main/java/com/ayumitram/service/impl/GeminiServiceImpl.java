package com.ayumitram.service.impl;

import com.ayumitram.service.GeminiService;
import com.ayumitram.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiServiceImpl implements GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String generateAyurvedicResponse(String prompt) {
        String url = Constants.GEMINI_API_URL + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Building the Gemini Request JSON Structure
        Map<String, Object> partsMap = new HashMap<>();
        partsMap.put("text", prompt);

        Map<String, Object> contentMap = new HashMap<>();
        contentMap.put("parts", List.of(partsMap));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", List.of(contentMap));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            return extractTextFromResponse(response.getBody());
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate response from AI: " + e.getMessage());
        }
    }

    private String extractTextFromResponse(Map responseBody) {
        try {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
            return (String) parts.get(0).get("text");
        } catch (Exception e) {
            return "An error occurred while parsing the AI response.";
        }
    }
}