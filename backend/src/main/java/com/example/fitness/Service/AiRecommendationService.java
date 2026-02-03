package com.example.fitness.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AiRecommendationService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String OLLAMA_URL =
            "http://localhost:11434/api/chat";

    public String getRecommendation(String prompt) {

        Map<String, Object> body = new HashMap<>();
        body.put("model", "mistral");


        body.put("messages", List.of(
                Map.of("role", "system",
                       "content", "You are an intelligent fitness coach. Think before answering."),
                Map.of("role", "user",
                       "content", prompt)
        ));

        body.put("stream", false);
        body.put("options", Map.of(
        	    "temperature", 1.0,
        	    "top_p", 0.9,
        	    "num_predict", 300  
        	));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<Map<String, Object>> response =
                restTemplate.exchange(
                        OLLAMA_URL,
                        HttpMethod.POST,
                        request,
                        new ParameterizedTypeReference<>() {}
                );

        Map<String, Object> message =
                (Map<String, Object>) response.getBody().get("message");

        return message.get("content").toString();
    }
}
