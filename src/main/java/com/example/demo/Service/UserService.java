package com.example.demo.Service;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponse createUser(CreateUserRequest request) {
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
        boolean isUserExists = userRepository.existsById(id);
        if (isUserExists) {
            userRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("User with Id " + id + " not found");
        }
    }
}
