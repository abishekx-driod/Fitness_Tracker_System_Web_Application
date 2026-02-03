package com.example.fitness.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.fitness.dto.GoalRequestDto;
import com.example.fitness.dto.GoalResponseDto;
import com.example.fitness.entity.User;
import com.example.fitness.security.JwtUtil;
import com.example.fitness.Service.GoalSerivce;
import com.example.fitness.Service.UserService;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "http://localhost:5173")
public class GoalController {

    private final GoalSerivce goalService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public GoalController(
            GoalSerivce goalService,
            JwtUtil jwtUtil,
            UserService userService) {
        this.goalService = goalService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    private Long extractUserId(String authHeader) {
        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);
        User user = userService.getUserByEmail(email);
        return user.getUserId();
    }

    @PostMapping
    public GoalResponseDto createGoal(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody GoalRequestDto dto) {
        return goalService.createGoal(extractUserId(authHeader), dto);
    }

    @GetMapping
    public List<GoalResponseDto> getGoals(
            @RequestHeader("Authorization") String authHeader) {

        return goalService.getUserGoals(extractUserId(authHeader));
    }
     
    @PutMapping("/{goalId}")
    public GoalResponseDto updateGoal(
            @PathVariable Long goalId,
            @RequestBody GoalRequestDto dto) {

        return goalService.updateGoal(goalId, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
    }
}
