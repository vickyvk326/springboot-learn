package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String roleName;
    private String accessToken;
    private String refreshToken;

    public LoginResponse(UserResponse user, String accessToken, String refreshToken){
        this.id = user.getId();
        this.name = user.getName();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.roleName = user.getRoleName();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
