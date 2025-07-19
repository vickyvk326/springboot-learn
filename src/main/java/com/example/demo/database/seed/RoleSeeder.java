package com.example.demo.database.seed;

import com.example.demo.model.Role;
import com.example.demo.model.RoleType;
import com.example.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(null, RoleType.ROLE_ADMIN, "Admin Role"));
            roleRepository.save(new Role(null, RoleType.ROLE_USER, "User Role"));
            roleRepository.save(new Role(null, RoleType.ROLE_MODERATOR, "Moderator Role"));
        }
    }
}
