package com.example.Job.Portal.dto;

public class AuthResponseDTO {
    private String token;
    private String role;
    private String username;

    public AuthResponseDTO(String token, String role, String username) {
        this.token = token;
        this.role = role;
        this.username = username;
    }

    // Getters
    public String getToken() { return token; }
    public String getRole() { return role; }
    public String getUsername() { return username; }
}
