package com.example.fitness.dto;

import java.time.LocalDate;

public class UserActivityResponseDto {
	 	private Long id;
	 	private String activityName;
	    private String activityCategory;
	    private Integer stepsCount;
	    private Integer durationMinutes;
	    private Double caloriesBurned;
	    private LocalDate activityDate;
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getActivityName() {
			return activityName;
		}
		public void setActivityName(String activityName) {
			this.activityName = activityName;
		}
		public String getActivityCategory() {
			return activityCategory;
		}
		public void setActivityCategory(String activityCategory) {
			this.activityCategory = activityCategory;
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
		
}
