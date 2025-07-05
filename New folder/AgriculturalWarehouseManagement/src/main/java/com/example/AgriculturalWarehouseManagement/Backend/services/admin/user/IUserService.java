package com.example.AgriculturalWarehouseManagement.Backend.services.admin.user;



import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.UserDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Role;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    User createUser(UserDTO userDTO) throws Exception;
    boolean deleteUser(Long id);
    User updateUser(Long id, UserDTO userDTO) throws Exception;
    User findById(Long id);
    List<User> findAll();
    List<User> findByStatusIsNot(String status);
    boolean existsByPhone(String phone);

    Page<User> findAll(Pageable pageable);
    User findByEmail(String email);

    List<User> findByRoleIsNot(Role role);
}
