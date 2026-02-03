package com.example.fitness.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_activities")
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_activity_id")
    private Long userActivityId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @Column(name = "steps_count")
    private Integer stepsCount;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "calories_burned")
    private Double caloriesBurned;

    @Column(name = "activity_date")
    private LocalDate activityDate;
    
    @Column(name = "weight_at_that_time", nullable = false)
    private Double weightAtThatTime;

	public Double getWeightAtThatTime() {
		return weightAtThatTime;
	}

	public void setWeightAtThatTime(Double weightAtThatTime) {
		this.weightAtThatTime = weightAtThatTime;
	}

	public Long getUserActivityId() {
		return userActivityId;
	}

	public void setUserActivityId(Long userActivityId) {
		this.userActivityId = userActivityId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
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

