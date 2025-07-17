package com.example.demo.Service;

import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginAttemptService {

    private final UserRepository userRepository;

    public LoginAttemptService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void incrementFailedLogin(Long userId) {
        userRepository.incrementFailedLoginAttempts(userId);
        System.out.println("⛔ Failed login incremented for userId = " + userId);
    }
}
