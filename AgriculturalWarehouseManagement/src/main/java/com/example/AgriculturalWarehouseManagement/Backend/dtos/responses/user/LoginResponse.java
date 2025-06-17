package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.UserRequest;

public class LoginResponse {
    private UserRequest user;
    private String token;

    public LoginResponse(UserRequest user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserRequest getUser() {
        return user;
    }

    public void setUser(UserRequest user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
