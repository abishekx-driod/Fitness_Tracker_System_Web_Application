package com.example.fitness.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.Service.AiRecommendationService;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin
public class AIController {

    @Autowired
    private AiRecommendationService aiService;

    @PostMapping("/recommendation")
    public String getAiRecommendation(@RequestBody Map<String, String> data) {

        String goal = data.get("goal");
        String level = data.get("level");

        String prompt = """
        You are a fitness coach.
        Create a personalized workout plan.
        Goal: %s
        Fitness Level: %s
        Duration: 7 days
        """.formatted(goal, level);

        return aiService.getRecommendation(prompt);
    }
}
