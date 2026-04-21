package com.ecommerce.controller;

import com.ecommerce.dto.ApiResponse;
import com.ecommerce.dto.request.LoginRequest;
import com.ecommerce.dto.request.RegisterRequest;
import com.ecommerce.service.AuthService;
import com.ecommerce.util.UserContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Authentication APIs")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginRequest request) {
        authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(ApiResponse.success("Login successful", UserContext.getCurrentUserRole()));
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new customer user")
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(
            request.getName(),
            request.getEmail(),
            request.getPassword(),
            request.getRole(),
            request.getLocation()
        );
        return ResponseEntity.ok(ApiResponse.success("Registration successful", request.getEmail()));
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout current user context")
    public ResponseEntity<ApiResponse<Void>> logout() {
        authService.logout();
        return ResponseEntity.ok(ApiResponse.success("Logout successful", null));
    }
}
