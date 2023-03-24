package com.example.voting_app.service;

import com.example.voting_app.model.User;

import java.util.Map;

public interface JWTGenerator {
    Map<String, String> generateToken(User user);
}
