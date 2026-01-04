package com.example.Job.Portal.entity;

import com.example.Job.Portal.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CANDIDATE;  // ADMIN, RECRUITER, CANDIDATE

    // Candidate-specific details
    private String resumeUrl;   // link to uploaded resume (optional)

    // Recruiter-specific details
    private String companyName;

    }
