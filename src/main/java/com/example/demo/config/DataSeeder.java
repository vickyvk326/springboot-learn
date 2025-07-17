package com.example.demo.config;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public void run(String... args) {

        // Create dummy users
        if (userRepository.count() == 0) {
            System.out.println("Seeding Users...");
            String hashedPassword = passwordEncoder.encode("Password@123");

            CreateUserRequest createAlice = new CreateUserRequest("Alice", "alice@example.com", hashedPassword);
            User alice = userMapper.toEntity(createAlice);
            userRepository.save(alice);

            CreateUserRequest createBob = new CreateUserRequest("Bob", "bob@example.com", hashedPassword);
            User bob = userMapper.toEntity(createBob);
            userRepository.save(bob);
        }
    }
}
