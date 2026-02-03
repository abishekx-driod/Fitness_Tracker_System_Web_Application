package com.example.fitness.Service;

import java.util.List;

import com.example.fitness.dto.GoalRequestDto;
import com.example.fitness.dto.GoalResponseDto;
import com.example.fitness.entity.User;

public interface GoalSerivce {
	GoalResponseDto createGoal(Long userId, GoalRequestDto dto);

	List<GoalResponseDto> getUserGoals(Long userId);
	 
	GoalResponseDto updateGoal(Long goalId, GoalRequestDto dto);


	void deleteGoal(Long goalId);
	
	 void checkAndUpdateGoals(User user);
}
