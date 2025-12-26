package com.example.demo.service;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    /**
     * Requirement Rule 2: Use constructor injection.
     * Order: Repository, Encoder, AuthManager, TokenProvider.
     */
    public AuthService(AppUserRepository userRepository, 
                       PasswordEncoder passwordEncoder, 
                       AuthenticationManager authenticationManager, 
                       JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return new JwtResponse(jwt, loginRequest.getUsername());
    }

    public String register(RegisterRequest reg) {
        if (userRepository.existsByUsername(reg.getUsername())) {
            return "Error: Username is already taken!";
        }
        
        AppUser user = new AppUser();
        user.setUsername(reg.getUsername());
        user.setEmail(reg.getEmail());
        user.setPassword(passwordEncoder.encode(reg.getPassword()));
        user.setRole("ROLE_FACULTY"); // Set default role
        
        userRepository.save(user);
        return "User registered successfully!";
    }
}
