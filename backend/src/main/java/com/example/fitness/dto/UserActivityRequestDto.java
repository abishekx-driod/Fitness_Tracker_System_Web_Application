package com.example.fitness.dto;

import java.time.LocalDate;

public class UserActivityRequestDto {
	private Long activityId;
    private Integer stepsCount;
    private Integer durationMinutes;
    private Double caloriesBurned;
    private LocalDate activityDate;

    private Double weight;
    
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Integer getStepsCount() {
		return stepsCount;
	}
	public void setStepsCount(Integer stepsCount) {
		this.stepsCount = stepsCount;
	}
	public Integer getDurationMinutes() {
		return durationMinutes;
	}
	public void setDurationMinutes(Integer durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	public Double getCaloriesBurned() {
		return caloriesBurned;
	}
	public void setCaloriesBurned(Double caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}
	public LocalDate getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(LocalDate activityDate) {
		this.activityDate = activityDate;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
