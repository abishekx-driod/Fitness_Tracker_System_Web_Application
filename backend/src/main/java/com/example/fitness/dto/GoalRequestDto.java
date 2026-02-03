package com.example.fitness.dto;

import java.time.LocalDate;

public class GoalRequestDto {
    private String goalType;
    private Integer targetValue;
    private LocalDate startDate;
    private LocalDate endDate;
	public String getGoalType() {
		return goalType;
	}
	public void setGoalType(String goalType) {
		this.goalType = goalType;
	}
	public Integer getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(Integer targetValue) {
		this.targetValue = targetValue;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
