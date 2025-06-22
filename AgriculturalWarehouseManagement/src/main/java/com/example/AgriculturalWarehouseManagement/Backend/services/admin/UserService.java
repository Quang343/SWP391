package com.example.AgriculturalWarehouseManagement.Backend.services.admin;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.UserDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Role;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.RoleRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        String phone = userDTO.getPhone();
        if(userRepository.existsByPhone(phone)){
            throw new Exception("Phone number already exists");
        }
        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role Not Found"));
        User user = mapDtoToUser(userDTO, role);
        return userRepository.save(user);
    }

    private User mapDtoToUser(UserDTO dto, Role role) {
        return User.builder()
                .userName(dto.getUsername())
                .fullName(dto.getFullName())
                .image(dto.getImage())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .gender(dto.getGender())
                .status(dto.getStatus())
                .dob(dto.getDob())
                .otp(dto.getOtp())
                .role(role)
                .build();
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role Not Found"));
        if (!user.getPhone().equals(userDTO.getPhone()) && userRepository.existsByPhone(userDTO.getPhone())) {
            throw new Exception("Phone number already exists");
        }
        modelMapper.typeMap(UserDTO.class, User.class)
                 .addMappings(mapper-> mapper.skip(User::setUserId));
        modelMapper.map(userDTO, user);
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setStatus("Ban");
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByStatusIsNot(String status) {
        return  userRepository.findByStatusIsNot(status);
    }
}
