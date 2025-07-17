package com.example.demo.controller;

import com.example.demo.Service.UserService;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.Response;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "User", description = "User management APIs")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get users list", description = "Returns the list of users")
    public ResponseEntity<Response<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(Response.success("Users list retrieved successfully", users));
    }

    @PostMapping("/register")
    @Operation(summary = "Create a new user", description = "Creates a new user and returns the user")
    public ResponseEntity<Response<UserResponse>> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserResponse createdUser = userService.createUser(request);
        return ResponseEntity.ok(Response.success("User created successfully", createdUser));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Returns a single user")
    public ResponseEntity<Response<UserResponse>> getUserById(@PathVariable Long id) {
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(Response.success(user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by ID", description = "Deletes a single user")
    public ResponseEntity<Response<String>> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok(Response.success("User deleted successfully"));
    }
}
