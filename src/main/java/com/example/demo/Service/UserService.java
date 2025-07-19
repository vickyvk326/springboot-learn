package com.example.demo.Service;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.PaginatedResponse;
import com.example.demo.dto.UserResponse;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public PaginatedResponse<UserResponse> getAllUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        Page<UserResponse> dtoPage = userPage.map(userMapper::toDto);
        return new PaginatedResponse<>(dtoPage);
    }

    public boolean isUserNameExists(String username) {
        return userRepository.existsByUserName(username.trim());
    }

    public UserResponse createUser(CreateUserRequest request) {
        String requestEmail = request.getEmail().trim().toLowerCase();
        if (userRepository.existsByEmail(requestEmail)) throw new KeyAlreadyExistsException("Email already in use");

        String userName = request.getUserName();
        if (isUserNameExists(userName)) throw new KeyAlreadyExistsException("Username already in use");

        User newUser = userMapper.toEntity(request);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        return userMapper.toDto(newUser);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User with Id " + id + " not found"));
        return userMapper.toDto(user);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) throw new NoSuchElementException("User with Id " + id + " not found");
        userRepository.deleteById(id);
    }
}
