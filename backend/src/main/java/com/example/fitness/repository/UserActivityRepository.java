package com.example.fitness.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fitness.entity.User;
import com.example.fitness.entity.UserActivity;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
	List<UserActivity> findTop5ByUser_EmailOrderByActivityDateDesc(Long long1);
	
	List<UserActivity> findTop5ByUser_EmailOrderByActivityDateDesc(String email);

	List<UserActivity> findTop5ByUserOrderByActivityDateDesc(User user);
  
    @Query("""
        SELECT COALESCE(SUM(ua.stepsCount), 0)
        FROM UserActivity ua
        WHERE ua.user.userId = :userId
    """)
    Integer getTotalSteps(@Param("userId") Long userId);

    @Query("""
        SELECT COALESCE(SUM(ua.caloriesBurned), 0)
        FROM UserActivity ua
        WHERE ua.user.userId = :userId
    """)
    Double getTotalCalories(@Param("userId") Long userId);

    @Query("""
        SELECT COALESCE(SUM(ua.durationMinutes), 0)
        FROM UserActivity ua
        WHERE ua.user.userId = :userId
    """)
    Integer getTotalWorkoutMinutes(@Param("userId") Long userId);

	List<UserActivity> findTop5ByUser_UserIdOrderByActivityDateDesc(Long userId);
	
}



