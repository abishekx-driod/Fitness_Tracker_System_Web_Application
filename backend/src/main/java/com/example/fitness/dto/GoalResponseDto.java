package com.example.fitness.dto;

public class GoalResponseDto {
    private Long goalId;
    private String goalType;
    private Integer targetValue;
    private Integer currentValue;
    private String status;
	public String getGoalType() {
		return goalType;
	}
	public void setGoalType(String goalType) {
		this.goalType = goalType;
	}
	public Long getGoalId() {
		return goalId;
	}
	public void setGoalId(Long goalId) {
		this.goalId = goalId;
	}
	public Integer getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(Integer targetValue) {
		this.targetValue = targetValue;
	}
	public Integer getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(Integer currentValue) {
		this.currentValue = currentValue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

