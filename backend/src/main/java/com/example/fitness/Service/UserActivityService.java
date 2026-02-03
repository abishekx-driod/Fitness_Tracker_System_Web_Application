package com.example.fitness.Service;

import java.util.List;

import com.example.fitness.dto.UserActivityRequestDto;
import com.example.fitness.dto.UserActivityResponseDto;


public interface UserActivityService {


	    UserActivityResponseDto addActivity(UserActivityRequestDto dto);

	    List<UserActivityResponseDto> getUserActivities();

	    UserActivityResponseDto updateActivity(Long id, UserActivityRequestDto dto);

	    void deleteActivity(Long activityLogId);

}
