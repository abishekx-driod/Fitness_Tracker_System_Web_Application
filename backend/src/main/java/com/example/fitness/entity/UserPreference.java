package com.example.fitness.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_preferences")
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Long preferenceId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Column(name = "activity_level")
    private String activityLevel; // sedentary / moderate / active

    @Column(name = "fitness_goal_type")
    private String fitnessGoalType; // weight_loss / muscle_gain / maintenance
}
