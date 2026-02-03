package com.example.fitness.repository;

import com.example.fitness.entity.HealthMetric;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;


public interface HealthMetricRepository extends JpaRepository<HealthMetric, Long> {
	Optional<HealthMetric> findTopByUser_UserIdOrderByRecordedDateDesc(Long userId);


}
