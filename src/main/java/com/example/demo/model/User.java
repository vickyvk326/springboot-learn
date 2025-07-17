package com.example.demo.model;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.validation.StrongPassword;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity // Defines a sql table
@Data // Common constructors
@AllArgsConstructor // Required for creating a user with own values
@NoArgsConstructor
@DynamicUpdate // Updated only required columns
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @StrongPassword
    @JsonIgnore
    private String password;

    @JsonIgnore
    @Column(name = "failed_login_attempts", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int failedLoginAttempts = 0;
}
