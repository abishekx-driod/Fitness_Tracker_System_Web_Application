package com.example.fitness.repository;

import com.example.fitness.entity.Goal;
import com.example.fitness.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoalRepository
        extends JpaRepository<Goal, Long> {

	List<Goal> findByUser_UserId(Long userId);

    List<Goal> findByUser_UserIdAndStatus(Long userId, String status);


    Optional<Goal> findTopByUserAndGoalTypeAndStatusOrderByGoalIdDesc(
    	    User user, String goalType, String status
    	);

}
