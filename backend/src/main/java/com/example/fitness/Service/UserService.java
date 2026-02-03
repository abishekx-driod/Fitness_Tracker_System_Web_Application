package com.example.fitness.Service;

import com.example.fitness.entity.User;

public interface UserService {

    User getUserById(Long userId);

    User getUserByEmail(String email);
}
