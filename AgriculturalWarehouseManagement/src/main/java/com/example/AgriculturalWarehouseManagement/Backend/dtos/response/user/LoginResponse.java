package com.example.AgriculturalWarehouseManagement.Backend.dtos.response.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;

public class LoginResponse {
    private UserResponse user;
    private String token;

    public LoginResponse(UserResponse user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
