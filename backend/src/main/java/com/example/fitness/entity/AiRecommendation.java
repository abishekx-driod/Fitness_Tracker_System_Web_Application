package com.example.fitness.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_recommendations")
public class AiRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendation_id")
    private Long recommendationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "fitness_level")
    private String fitnessLevel; // low / medium / high

    @Column(name = "recommendation_text", columnDefinition = "TEXT")
    private String recommendationText;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}

