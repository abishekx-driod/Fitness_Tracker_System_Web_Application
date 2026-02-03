package com.example.fitness.repository;

import com.example.fitness.entity.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPreferenceRepository
        extends JpaRepository<UserPreference, Long> {

    Optional<UserPreference> findByUserUserId(Long userId);
}
