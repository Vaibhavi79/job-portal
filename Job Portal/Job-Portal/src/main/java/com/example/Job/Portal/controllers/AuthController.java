package com.example.Job.Portal.controllers;

import com.example.Job.Portal.Enum.Role;
import com.example.Job.Portal.dto.AuthRequestDTO;
import com.example.Job.Portal.dto.AuthResponseDTO;
import com.example.Job.Portal.dto.UserDTO;
import com.example.Job.Portal.entity.User;
import com.example.Job.Portal.repositories.UserRepository;
import com.example.Job.Portal.security.JwtUtil;
import com.example.Job.Portal.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // REGISTER USER
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO request) {
       {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            // ✅ handle role safely
            if (request.getRole() != null) {
                user.setRole(request.getRole());  // since it's already Enum in DTO
            } else {
                user.setRole(Role.CANDIDATE);     // default
            }


            User savedUser = userRepository.save(user);

            // map back to DTO (no password!)
            UserDTO dto = new UserDTO();
            dto.setId(savedUser.getId());
            dto.setUsername(savedUser.getUsername());
            dto.setEmail(savedUser.getEmail());
            dto.setRole(savedUser.getRole());


            return ResponseEntity.ok(dto);
        }

    }


    // LOGIN USER
    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        return new AuthResponseDTO(token, user.getRole().name(), user.getUsername());
    }
}
