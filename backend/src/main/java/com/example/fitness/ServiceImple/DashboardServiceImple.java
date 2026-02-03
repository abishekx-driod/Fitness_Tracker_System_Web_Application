package com.example.fitness.ServiceImple;

import com.example.fitness.Service.DashboardService;
import com.example.fitness.dto.DashboardResponseDto;
import com.example.fitness.entity.UserActivity;
import com.example.fitness.repository.HealthMetricRepository;
import com.example.fitness.repository.UserActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImple implements DashboardService {

    private final UserActivityRepository userActivityRepository;
    private final HealthMetricRepository healthMetricRepository;

    public DashboardServiceImple(UserActivityRepository userActivityRepository,
                                HealthMetricRepository healthMetricRepository) {
        this.userActivityRepository = userActivityRepository;
        this.healthMetricRepository = healthMetricRepository;
    }

    @Override
    public DashboardResponseDto getDashboardData(Long userId) {

        DashboardResponseDto response = new DashboardResponseDto();

        response.setSteps(
        	    userActivityRepository.getTotalSteps(userId) != null
        	        ? userActivityRepository.getTotalSteps(userId)
        	        : 0
        	);

        	response.setCaloriesBurned(
        	    userActivityRepository.getTotalCalories(userId) != null
        	        ? userActivityRepository.getTotalCalories(userId)
        	        : 0
        	);

        	response.setWorkoutMinutes(
        	    userActivityRepository.getTotalWorkoutMinutes(userId) != null
        	        ? userActivityRepository.getTotalWorkoutMinutes(userId)
        	        : 0
        	);



        List<UserActivity> activities =
            userActivityRepository.findTop5ByUser_UserIdOrderByActivityDateDesc(userId);

        response.setRecentWorkouts(
                activities.stream()
                        .map(a -> a.getActivity() != null
                                ? a.getActivity().getActivityName()
                                : "Workout")
                        .toList()
        );
        
        healthMetricRepository
        .findTopByUser_UserIdOrderByRecordedDateDesc(userId)
        .ifPresent(health -> {
            response.setBmi(health.getBmi());
            response.setSleepHours(health.getSleepHours());
            response.setWaterIntake(health.getWaterIntakeLiters());
            response.setHeartRate(health.getHeartRate());
        });

        return response;
    }


}
