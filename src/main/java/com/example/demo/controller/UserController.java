package com.example.demo.controller;

import com.example.demo.Service.UserService;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.PaginatedResponse;
import com.example.demo.dto.Response;
import com.example.demo.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "User", description = "User management APIs")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get users list", description = "Returns the list of users")
    public ResponseEntity<Response<PaginatedResponse<UserResponse>>> getAllUsers(
            @RequestParam(defaultValue = "1") int page,   // 1-based page index
            @RequestParam(defaultValue = "10") int size,
            @Parameter(hidden = true) Sort sort  // Pulls sort query string, e.g., ?sort=name,asc
    ) {
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        PaginatedResponse<UserResponse> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(Response.success("Users list retrieved successfully", users));
    }

    @GetMapping("/usernameAvailable")
    @Operation(summary = "Check if a username is available", description = "Returns true if username is available")
    public ResponseEntity<Response<Boolean>> isUsernameAvailable(String username) {
        boolean isAvailable = !userService.isUserNameExists(username);
        return ResponseEntity.ok(Response.success(isAvailable ? "Username available" : "Username already taken", isAvailable));
    }

    @PostMapping("/register")
    @Operation(summary = "Create a new user", description = "Creates a new user and returns the user")
    public ResponseEntity<Response<UserResponse>> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserResponse createdUser = userService.createUser(request);
        return ResponseEntity.ok(Response.success("User created successfully", createdUser));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
