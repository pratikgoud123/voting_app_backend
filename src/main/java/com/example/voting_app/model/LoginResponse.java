/*
 * Author Name: Pratik Goud
 * Date: 21-03-2023
 * Created With: IntelliJ IDEA Community Edition
 */
package com.example.voting_app.model;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoginResponse {

    private User user;
    private Map<String, String> secretKeyToken;

    public LoginResponse() {
    }

    public LoginResponse(User user, Map<String, String> secretKeyToken) {
        this.user = user;
        this.secretKeyToken = secretKeyToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, String> getSecretKeyToken() {
        return secretKeyToken;
    }

    public void setSecretKeyToken(Map<String, String> secretKeyToken) {
        this.secretKeyToken = secretKeyToken;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "user=" + user +
                ", secretKeyToken=" + secretKeyToken +
                '}';
    }
}
