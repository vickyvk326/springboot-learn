package com.example.demo.database.seed;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.RoleType;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Order(2)
@Profile("dev")
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public void run(String... args) {

        // Create dummy users
        if (userRepository.count() == 0) {
            System.out.println("Seeding Users...");
            String hashedPassword = passwordEncoder.encode("Password@123");

            CreateUserRequest createAlice = new CreateUserRequest("Alice", "alice", "alice@example.com", hashedPassword, RoleType.ROLE_USER.name());
            User alice = userMapper.toEntity(createAlice);
            userRepository.save(alice);

            CreateUserRequest createBob = new CreateUserRequest("Bob", "bob", "bob@example.com", hashedPassword, RoleType.ROLE_USER.name());
            User bob = userMapper.toEntity(createBob);
            userRepository.save(bob);

            CreateUserRequest createVignesh = new CreateUserRequest("Vignesh", "vicky", "vicky@email.com", hashedPassword, RoleType.ROLE_ADMIN.name());
            User vicky = userMapper.toEntity(createVignesh);
            userRepository.save(vicky);
        }
    }
}
