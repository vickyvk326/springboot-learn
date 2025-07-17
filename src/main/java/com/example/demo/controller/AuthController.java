package com.example.demo.controller;

import com.example.demo.Service.AuthService;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RefreshTokenRequest;
import com.example.demo.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountLockedException;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor // Lombok generates constructor (if using Lombok)
@Tag(name = "Auth", description = "Auth management APIs")
public class AuthController {
    private final AuthService authService; // Spring will inject this automatically

    @PostMapping("/login")
    @Operation(summary = "Login using credentials", description = "Returns the user data and login tokens")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) throws AccountLockedException {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(Response.success("Login successful", response));
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "Get new refresh token", description = "Returns the new refresh token")
    public ResponseEntity<Response<Map<String, String>>> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        Map<String, String> tokens = authService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(Response.success("Token refreshed", tokens));
    }

}
