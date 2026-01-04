package com.example.Job.Portal.dto;

import com.example.Job.Portal.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Role role; // ADMIN, RECRUITER, CANDIDATE
    private String password;

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

}
