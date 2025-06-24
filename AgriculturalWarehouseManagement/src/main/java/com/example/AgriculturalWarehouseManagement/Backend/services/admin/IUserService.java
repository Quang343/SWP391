package com.example.AgriculturalWarehouseManagement.Backend.services.admin;



import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.UserDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;

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
