package com.example.fitness.ServiceImple;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fitness.Service.GoalSerivce;
import com.example.fitness.dto.GoalRequestDto;
import com.example.fitness.dto.GoalResponseDto;

import com.example.fitness.entity.Goal;
import com.example.fitness.entity.User;

import com.example.fitness.repository.GoalRepository;
import com.example.fitness.repository.UserActivityRepository;
import com.example.fitness.repository.UserRepository;

@Service
public class GoalServiceImple implements GoalSerivce {

    private final GoalRepository goalRepo;
    private final UserRepository userRepo;
    private final UserActivityRepository actiRepo;
    public GoalServiceImple(GoalRepository goalRepo, UserRepository userRepo,UserActivityRepository actiRepo) {
        this.goalRepo = goalRepo;
        this.userRepo = userRepo;
        this.actiRepo=actiRepo;
    }

    private GoalResponseDto mapToDto(Goal g) {
        GoalResponseDto dto = new GoalResponseDto();
        dto.setGoalId(g.getGoalId());
        dto.setGoalType(g.getGoalType());
        dto.setTargetValue(g.getTargetValue());
        dto.setCurrentValue(g.getCurrentValue());
        dto.setStatus(g.getStatus());
        return dto;
    }

    @Override
    public GoalResponseDto createGoal(Long userId, GoalRequestDto dto) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Goal goal = new Goal();
        goal.setUser(user);
        goal.setGoalType(dto.getGoalType());
        goal.setTargetValue(dto.getTargetValue());
        goal.setCurrentValue(0);
        goal.setStartDate(dto.getStartDate());
        goal.setEndDate(dto.getEndDate());
        goal.setStatus("ACTIVE");

        return mapToDto(goalRepo.save(goal));
    }

    @Override
    public List<GoalResponseDto> getUserGoals(Long userId) {
        return goalRepo.findByUser_UserId(userId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public void deleteGoal(Long goalId) {
        goalRepo.deleteById(goalId);
    }

	@Override
	public GoalResponseDto updateGoal(Long goalId, GoalRequestDto dto) {
		Goal goal = goalRepo.findById(goalId)
	            .orElseThrow(() -> new RuntimeException("Goal not found"));

	    goal.setGoalType(dto.getGoalType());
	    goal.setTargetValue(dto.getTargetValue());
	    goal.setStartDate(dto.getStartDate());
	    goal.setEndDate(dto.getEndDate());
	    goal.setStatus("active");

	    return mapToDto(goalRepo.save(goal));
	}

	@Override
	public void checkAndUpdateGoals(User user) {
		 List<Goal> activeGoals =
	                goalRepo.findByUser_UserIdAndStatus(user.getUserId(), "active");

	        for (Goal goal : activeGoals) {

	            int progress = switch (goal.getGoalType()) {
	                case "steps" -> actiRepo.getTotalSteps(user.getUserId());
	                case "calories" ->  actiRepo.getTotalCalories(user.getUserId()).intValue();
	                case "duration" ->  actiRepo.getTotalWorkoutMinutes(user.getUserId());
	                default -> 0;
	            };

	            if (progress >= goal.getTargetValue()) {
	                goal.setStatus("achieved");
	                goalRepo.save(goal);
	            }
	        }
	}
	
		
	}

