package com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user;

public class ResponseResult<T> {

    private String status;  // SUCCESS hoặc ERROR
    private String message; // Thông điệp liên quan đến kết quả
    private boolean active; // Trạng thái hoạt động (true/false)
    private T data;

    // Constructor
    public ResponseResult(String status, String message, boolean active) {
        this.status = status;
        this.message = message;
        this.active = active;
    }

    public ResponseResult(String status, String message, boolean active, T data) {
        this.status = status;
        this.message = message;
        this.active = active;
        this.data = data;
    }

    // Getter và Setter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

