package com.example.AgriculturalWarehouseManagement.services;

import com.example.AgriculturalWarehouseManagement.dtos.UserDTO;
import com.example.AgriculturalWarehouseManagement.models.User;

import java.util.List;

public interface IUserService {

    User createUser(UserDTO userDTO) throws Exception;
    boolean deleteUser(Long id);
    User updateUser(Long id, UserDTO userDTO) throws Exception;
    User findById(Long id);
    List<User> findAll();
    List<User> findByStatusIsNot(String status);
    boolean existsByPhone(String phone);
}
