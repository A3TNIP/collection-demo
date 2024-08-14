package com.islington.summer.collectiondemo.auth.service;

import com.islington.summer.collectiondemo.auth.config.TokenProvider;
import com.islington.summer.collectiondemo.auth.dto.AuthResponse;
import com.islington.summer.collectiondemo.auth.dto.UserLoginDto;
import com.islington.summer.collectiondemo.model.Role;
import com.islington.summer.collectiondemo.model.UserEntity;
import com.islington.summer.collectiondemo.repository.RoleRepository;
import com.islington.summer.collectiondemo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }


    public ResponseEntity<?> generateToken(UserLoginDto req) {

        if (req.getEmail() == null || req.getPassword() == null) {
            throw new IllegalArgumentException("Email and password must not be null");
        }

        // 1. Get user details
        final UserEntity userEntity = userRepository.findByEmail(req.getEmail());

        if (userEntity == null) {
            return ResponseEntity.status(401).build();
        }

        final Set<Role> roles = userEntity.getRoles();
        // 2. Create an AuthResponse object

        // 3. Authenticate user
        final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 4. Generate a JWT token
        final String token = tokenProvider.generateToken(authentication);

        // 5. Set token, role, user and subject in the AuthResponse object
        var authResponse = AuthResponse.builder()
                .token(token)
                .roles(roles.stream().map(role -> "ROLE_" + role.getName().replace(" ", "_").toUpperCase()).toList())
                .build();

        // 6. Return the AuthResponse object
        return ResponseEntity.ok(authResponse);
    }
}
