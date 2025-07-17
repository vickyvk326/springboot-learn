package com.example.demo.mapper;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Mapping from DTO to Entity
    User toEntity(CreateUserRequest dto);

    // Mapping from Entity to DTO
    UserResponse toDto(User user);
}