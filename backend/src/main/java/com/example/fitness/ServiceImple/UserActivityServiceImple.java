package com.example.fitness.ServiceImple;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fitness.repository.GoalRepository;



import com.example.fitness.Service.UserActivityService;
import com.example.fitness.dto.UserActivityRequestDto;
import com.example.fitness.dto.UserActivityResponseDto;
import com.example.fitness.entity.Activity;
import com.example.fitness.entity.User;
import com.example.fitness.entity.UserActivity;
import com.example.fitness.repository.ActivityRepository;
import com.example.fitness.repository.UserActivityRepository;
import com.example.fitness.repository.UserRepository;

@Service
public class UserActivityServiceImple implements UserActivityService {

    private final UserActivityRepository userActivityRepo;
    private final ActivityRepository activityRepo;
    private final UserRepository userRepo;
    private final GoalRepository goalRepo;


    public UserActivityServiceImple(
            UserActivityRepository userActivityRepo,
            ActivityRepository activityRepo,
            UserRepository userRepo,
            GoalRepository goalRepo) {
        this.userActivityRepo = userActivityRepo;
        this.activityRepo = activityRepo;
        this.userRepo = userRepo;
        this.goalRepo=goalRepo;
    }
    private void updateGoals(User user, UserActivityRequestDto dto, double calories) {
    	System.out.println("User ID = " + user.getUserId());

        if (dto.getStepsCount() != null) {
        	 goalRepo.findTopByUserAndGoalTypeAndStatusOrderByGoalIdDesc(user, "STEPS", "ACTIVE")
             .ifPresentOrElse(
                 goal -> {
                     System.out.println("✅ STEPS goal found: " + goal.getGoalId());
                     goal.setCurrentValue(goal.getCurrentValue() + dto.getStepsCount());
                     goalRepo.save(goal);
                 },
                 () -> System.out.println("❌ STEPS goal NOT FOUND")
             );
        }

        goalRepo.findTopByUserAndGoalTypeAndStatusOrderByGoalIdDesc(user, "CALORIES", "ACTIVE")
                .ifPresent(goal -> {
                    goal.setCurrentValue(
                        goal.getCurrentValue() + (int) calories
                    );
                    goalRepo.save(goal);
                });


        if (dto.getDurationMinutes() != null) {
            goalRepo.findTopByUserAndGoalTypeAndStatusOrderByGoalIdDesc(user, "WORKOUT", "ACTIVE")
                    .ifPresent(goal -> {
                        goal.setCurrentValue(
                            goal.getCurrentValue() + dto.getDurationMinutes()
                        );
                        goalRepo.save(goal);
                    });
        }
    }


    	private double calculateCalories(
    	        Integer steps,
    	        Integer duration,
    	        String activityName,
    	        Double weight) {

    	    if (weight == null || weight <= 0) {
    	        throw new IllegalArgumentException("Weight is mandatory");
    	    }
   
    	    if (activityName == null) return 0;

    	    String name = activityName.trim().toLowerCase();

    	    switch (name) {

    	        case "walking":
    	        case "running":
    	            return (steps != null ? steps : 0) * weight * 0.0005;

    	        case "cycling":
    	            return (duration != null ? duration : 0) * weight * 0.08;

    	        case "swimming":
    	            return (duration != null ? duration : 0) * weight * 0.13;

    	        case "gym workout":
    	        case "gym":
    	            return (duration != null ? duration : 0) * weight * 0.09;

    	        case "yoga":
    	            return (duration != null ? duration : 0) * weight * 0.04;

    	        default:
    	            return 0;
    	    }
    	}


    // ===============================
    // ENTITY → DTO
    // ===============================
    private UserActivityResponseDto mapToDto(UserActivity ua) {

        UserActivityResponseDto dto = new UserActivityResponseDto();
        dto.setId(ua.getUserActivityId());
        dto.setActivityName(ua.getActivity().getActivityName());
        dto.setActivityCategory(ua.getActivity().getActivityCategory());
        dto.setStepsCount(ua.getStepsCount());
        dto.setDurationMinutes(ua.getDurationMinutes());
        dto.setCaloriesBurned(ua.getCaloriesBurned());
        dto.setActivityDate(ua.getActivityDate());
        return dto;
    }


    @Override
    @Transactional
    public UserActivityResponseDto addActivity(UserActivityRequestDto dto) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Activity activity = activityRepo.findById(dto.getActivityId())
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        double calories = calculateCalories(
                dto.getStepsCount(),
                dto.getDurationMinutes(),
                activity.getActivityName(),
                dto.getWeight()
        );

        UserActivity ua = new UserActivity();
        ua.setUser(user);
        ua.setActivity(activity);
        ua.setStepsCount(dto.getStepsCount());
        ua.setDurationMinutes(dto.getDurationMinutes());
        ua.setCaloriesBurned(calories);
        ua.setActivityDate(dto.getActivityDate());
        ua.setWeightAtThatTime(dto.getWeight());

     // AFTER saving activity
        userActivityRepo.save(ua);

        System.out.println("🔥 updateGoals called");
        updateGoals(user, dto, calories);

        user.setWeightKg(dto.getWeight());
        userRepo.save(user);
        
        return mapToDto(ua);
    }

    @Override
    public List<UserActivityResponseDto> getUserActivities() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userActivityRepo
                .findTop5ByUserOrderByActivityDateDesc(user)
                .stream()
                .map(this::mapToDto)
                .toList();

    }

    @Override
    public UserActivityResponseDto updateActivity(Long id, UserActivityRequestDto dto) {

        UserActivity ua = userActivityRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        ua.setStepsCount(dto.getStepsCount());
        ua.setDurationMinutes(dto.getDurationMinutes());
        ua.setActivityDate(dto.getActivityDate());
        ua.setWeightAtThatTime(dto.getWeight());

        double calories = calculateCalories(
                dto.getStepsCount(),
                dto.getDurationMinutes(),
                ua.getActivity().getActivityName(),
                dto.getWeight()
        );

        ua.setCaloriesBurned(calories);

        User user = ua.getUser();
        user.setWeightKg(dto.getWeight());
        userRepo.save(user);

        return mapToDto(userActivityRepo.save(ua));
    }

    @Override
    public void deleteActivity(Long id) {
        userActivityRepo.deleteById(id);
    }
}
