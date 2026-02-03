package com.example.fitness.dto;

import java.util.List;

public class DashboardResponseDto {
	private Integer steps;
    private Double caloriesBurned;
    private Integer workoutMinutes;
    private Double bmi;
    private Double sleepHours;
    private Double waterIntake;
    public Double getSleepHours() {
		return sleepHours;
	}

	public void setSleepHours(Double sleepHours) {
		this.sleepHours = sleepHours;
	}

	public Double getWaterIntake() {
		return waterIntake;
	}

	public void setWaterIntake(Double waterIntake) {
		this.waterIntake = waterIntake;
	}

	public Integer getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(Integer heartRate) {
		this.heartRate = heartRate;
	}

	private Integer heartRate;

    private List<String> recentWorkouts;

	public Integer getSteps() {
		return steps;
	}

	public void setSteps(Integer steps) {
		this.steps = steps;
	}

	public Double getCaloriesBurned() {
		return caloriesBurned;
	}

	public void setCaloriesBurned(Double caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}

	public Integer getWorkoutMinutes() {
		return workoutMinutes;
	}

	public void setWorkoutMinutes(Integer workoutMinutes) {
		this.workoutMinutes = workoutMinutes;
	}

	public Double getBmi() {
		return bmi;
	}

	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}

	public List<String> getRecentWorkouts() {
		return recentWorkouts;
	}

	public void setRecentWorkouts(List<String> recentWorkouts) {
		this.recentWorkouts = recentWorkouts;
	}
}
