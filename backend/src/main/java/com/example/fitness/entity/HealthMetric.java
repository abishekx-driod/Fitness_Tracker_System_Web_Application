package com.example.fitness.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "health_metrics")
public class HealthMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_id")
    private Long healthId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "heart_rate")
    private Integer heartRate;

    @Column(name = "sleep_hours")
    private Double sleepHours;

    @Column(name = "water_intake_liters")
    private Double waterIntakeLiters;

    private Double bmi;

    @Column(name = "recorded_date")
    private LocalDate recordedDate;

    // ===== GETTERS & SETTERS =====

    public Long getHealthId() {
        return healthId;
    }

    public void setHealthId(Long healthId) {
        this.healthId = healthId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Double getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(Double sleepHours) {
        this.sleepHours = sleepHours;
    }

    public Double getWaterIntakeLiters() {
        return waterIntakeLiters;
    }

    public void setWaterIntakeLiters(Double waterIntakeLiters) {
        this.waterIntakeLiters = waterIntakeLiters;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public LocalDate getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(LocalDate recordedDate) {
        this.recordedDate = recordedDate;
    }
}
