package com.example.demo.Service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountLockedException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private static final int MAX_ATTEMPTS = 5;
    private final LoginAttemptService loginAttemptService;

    @Transactional
    public LoginResponse login(LoginRequest request) throws AccountLockedException {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NoSuchElementException("Email not found"));

        int failedLoginAttempts = user.getFailedLoginAttempts();

        if (failedLoginAttempts >= MAX_ATTEMPTS) {
            throw new AccountLockedException("Account locked. Try again after some time.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            loginAttemptService.incrementFailedLogin(user.getId());
            throw new BadCredentialsException("Password and email does not match!");
        }

        if (failedLoginAttempts > 0) {
            user.setFailedLoginAttempts(0);
            userRepository.save(user);
        }

        return new LoginResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                jwtUtil.generateToken(user.getEmail()),
                jwtUtil.generateRefreshToken(user.getEmail())
        );
    }

    public Map<String,String> refreshAccessToken (String refreshToken){
        if(!jwtUtil.validateToken(refreshToken)){
            throw new BadCredentialsException("Invalid refresh token or refresh token expired");
        }

        String email = jwtUtil.extractUsername(refreshToken);
        String newAccessToken = jwtUtil.generateToken(email);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", newAccessToken);
        return tokens;
    }
}