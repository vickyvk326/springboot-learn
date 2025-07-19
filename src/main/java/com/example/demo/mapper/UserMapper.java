package com.example.demo.mapper;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    // Mapping from DTO to Entity
    @Mapping(source = "roleName", target = "role")
    User toEntity(CreateUserRequest dto);

    // Mapping from Entity to DTO
    @Mapping(source = "role.name", target = "roleName")
    UserResponse toDto(User user);
}