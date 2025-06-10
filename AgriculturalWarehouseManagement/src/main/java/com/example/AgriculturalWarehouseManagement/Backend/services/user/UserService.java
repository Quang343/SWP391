package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.response.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.response.user.RoleResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.user.RegisterRequestDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.Role;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.RoleRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public ResponseResult<User> registerUserService(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.findByEmail(registerRequestDTO.getEmail()) != null) {
            return new ResponseResult<>("ERROR", "Email already exists !!!", false);
        } else {
            if (registerRequestDTO.getPassword().length() < 9) {
                return new ResponseResult<>("ERROR", "Password cannot be less than 9 characters", false);
            } else {
//                .: Đại diện cho một ký tự bất kỳ, ngoại trừ ký tự new line (dấu xuống dòng).
//                *: Có nghĩa là lặp lại 0 hoặc nhiều lần ký tự trước đó
                if (!registerRequestDTO.getPassword().matches(".*[a-zA-Z].*") || !registerRequestDTO.getPassword().matches(".*\\d.*")) {
                    return new ResponseResult<>("ERROR", "Password must contain at least one letter and one number", false);
                } else {
                    if (registerRequestDTO.getPassword().matches(".*\\s.*")) {
                        return new ResponseResult<>("ERROR", "Password cannot contain spaces", false);
                    } else {

                        if (!registerRequestDTO.getPassword().matches(".*[A-Z].*")) {
                            return new ResponseResult<>("ERROR", "Password must contain at least one uppercase letter", false);
                        }

                        if (!registerRequestDTO.getPassword().matches(".*[a-z].*")) {
                            return new ResponseResult<>("ERROR", "Password must contain at least one lowercase letter", false);
                        }

                        if (!registerRequestDTO.getPassword().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                            return new ResponseResult<>("ERROR", "Password must contain at least one special character.", false);
                        }

                        return new ResponseResult<>("SUCCESS", "User registered successfully !!!", true);
                    }
                }
            }


        }

    }

    public void insertUserService(RegisterRequestDTO registerRequestDTO, String sessionOtp) {

        // Encode password
        String encodedPassword = passwordEncoder.encode(registerRequestDTO.getPassword());

        // Set user
        User user = new User();
        Role role = roleRepository.findByRoleName("user");

        user.setRole(role);
        user.setUserName(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPasswordHash(encodedPassword);
        user.setStatus("Active");
        user.setOtp(sessionOtp);
        user.setGoogleID("In");
        user.setStatusGG("Inactive");

        userRepository.save(user);

    }

    public User loadUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public ResponseResult<User> changePassword(String email, String changePassword) {

        if (email == null) {
            return new ResponseResult<>("ERROR", null, false);
        } else {
            if (changePassword == null) {
                return new ResponseResult<>("ERROR", "Let enter password !!!", false);
            } else {
                User user = userRepository.findByEmail(email);
                if (changePassword.length() < 9) {
                    return new ResponseResult<>("ERROR", "Password cannot be less than 9 characters", false);
                } else {
//                .: Đại diện cho một ký tự bất kỳ, ngoại trừ ký tự new line (dấu xuống dòng).
//                *: Có nghĩa là lặp lại 0 hoặc nhiều lần ký tự trước đó
                    if (!changePassword.matches(".*[a-zA-Z].*") || !changePassword.matches(".*\\d.*")) {
                        return new ResponseResult<>("ERROR", "Password must contain at least one letter and one number", false);
                    } else {
                        if (changePassword.matches(".*\\s.*")) {
                            return new ResponseResult<>("ERROR", "Password cannot contain spaces", false);
                        } else {

                            if (!changePassword.matches(".*[A-Z].*")) {
                                return new ResponseResult<>("ERROR", "Password must contain at least one uppercase letter", false);
                            }

                            if (!changePassword.matches(".*[a-z].*")) {
                                return new ResponseResult<>("ERROR", "Password must contain at least one lowercase letter", false);
                            }

                            if (!changePassword.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                                return new ResponseResult<>("ERROR", "Password must contain at least one special character.", false);
                            }

                            String hashedPassword = passwordEncoder.encode(changePassword);
                            user.setPasswordHash(hashedPassword);
                            userRepository.save(user); // Save in database

                            return new ResponseResult<>("SUCCESS", "Change password successfully !!!", true);
                        }
                    }
                }


            }


        }
    }

}
