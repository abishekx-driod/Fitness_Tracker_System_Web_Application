package com.example.fitness.repository;

import com.example.fitness.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository
        extends JpaRepository<Activity, Long> {
}
