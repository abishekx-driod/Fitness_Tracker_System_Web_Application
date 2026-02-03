package com.example.fitness.controller;

import com.example.fitness.Service.UserActivityService;
import com.example.fitness.dto.UserActivityRequestDto;
import com.example.fitness.dto.UserActivityResponseDto;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "http://localhost:5173")
public class UserActivityController {

    private final UserActivityService service;

    public UserActivityController(UserActivityService service) {
        this.service = service;
    }

    // ✅ CREATE ACTIVITY (user from JWT)
    @PostMapping
    public UserActivityResponseDto addActivity(
            @RequestBody UserActivityRequestDto dto) {

        return service.addActivity(dto);
    }

    // ✅ GET USER ACTIVITIES (user from JWT)
    @GetMapping
    public List<UserActivityResponseDto> getActivities() {

        return service.getUserActivities();
    }

    // ✅ UPDATE ACTIVITY
    @PutMapping("/{id}")
    public UserActivityResponseDto updateActivity(
            @PathVariable Long id,
            @RequestBody UserActivityRequestDto dto) {

        return service.updateActivity(id, dto);
    }

    // ✅ DELETE ACTIVITY
    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable Long id) {
        service.deleteActivity(id);
    }
}
