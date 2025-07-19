package com.example.demo.mapper;

import com.example.demo.model.Role;
import com.example.demo.model.RoleType;
import com.example.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final RoleRepository roleRepository;

    public Role mapRole(String roleName) {
        RoleType roleType = RoleType.valueOf(roleName.toUpperCase());
        return roleRepository.findByName(roleType)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role: " + roleName));
    }
}
