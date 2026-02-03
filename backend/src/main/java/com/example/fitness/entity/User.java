package com.example.fitness.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;





@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;

    private Integer age;

    private String gender;

    @Column(name = "height_cm")
    private Double heightCm;

    @Column(name = "weight_kg")
    private Double weightKg;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /* =========================
       ROLE MAPPING (SECURITY)
       ========================= */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    /* =========================
       RELATIONSHIPS
       ========================= */

    // One User → One Preference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserPreference userPreference;

    // One User → Many Activities
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserActivity> userActivities;

    // One User → Many Health Metrics
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<HealthMetric> healthMetrics;

    // One User → Many Goals
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Goal> goals;

    // One User → Many AI Recommendations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<AiRecommendation> aiRecommendations;

    /* =========================
       CONSTRUCTORS
       ========================= */
    public User() {}

    /* =========================
       GETTERS & SETTERS
       ========================= */

    public  Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public Integer getAge() {
        return age;
    }
 
    public void setAge(Integer age) {
        this.age = age;
    }
 
    public String getGender() {
        return gender;
    }
 
    public void setGender(String gender) {
        this.gender = gender;
    }
 
    public Double getHeightCm() {
        return heightCm;
    }
 
    public void setHeightCm(Double heightCm) {
        this.heightCm = heightCm;
    }
 
    public Double getWeightKg() {
        return weightKg;
    }
 
    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }
 
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
 
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
 
    public Set<Role> getRoles() {
        return roles;
    }
 
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserPreference getUserPreference() {
        return userPreference;
    }

    public void setUserPreference(UserPreference userPreference) {
        this.userPreference = userPreference;
    }

    public Set<UserActivity> getUserActivities() {
        return userActivities;
    }

    public void setUserActivities(Set<UserActivity> userActivities) {
        this.userActivities = userActivities;
    }

    public Set<HealthMetric> getHealthMetrics() {
        return healthMetrics;
    }

    public void setHealthMetrics(Set<HealthMetric> healthMetrics) {
        this.healthMetrics = healthMetrics;
    }

    public Set<Goal> getGoals() {
        return goals;
    }

    public void setGoals(Set<Goal> goals) {
        this.goals = goals;
    }

    public Set<AiRecommendation> getAiRecommendations() {
        return aiRecommendations;
    }

    public void setAiRecommendations(Set<AiRecommendation> aiRecommendations) {
        this.aiRecommendations = aiRecommendations;
    }
}
