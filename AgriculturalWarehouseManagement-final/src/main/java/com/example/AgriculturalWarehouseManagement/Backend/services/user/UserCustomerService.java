package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.components.CapitalizedEachWord;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.ProfileRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProfileResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ResponseResult;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.GoogleAccountRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.requests.user.RegisterRequestDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.Role;
import com.example.AgriculturalWarehouseManagement.Backend.models.UpdateProfileHistory;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.models.Wallets;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.RoleRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UpdateProfileHistoryRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.UserRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.WalletsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class UserCustomerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private WalletsRepository walletsRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UpdateProfileHistoryRepository updateProfileHistoryRepository;


    public ResponseResult<User> registerUserService(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.FindByEmail(registerRequestDTO.getEmail()) != null) {
            return new ResponseResult<>("ERROR", "Email đã tồn tại !!!", false);
        } else {
            if (registerRequestDTO.getUsername().length() < 9) {
                return new ResponseResult<>("ERROR", "Tên người dùng quá ngắn, ít nhất 9 ký tự !!!", false);
            }

            if (registerRequestDTO.getPassword().length() < 9) {
                return new ResponseResult<>("ERROR", "Mật khẩu không được ít hơn 9 ký tự", false);
            } else {
//                .: Đại diện cho một ký tự bất kỳ, ngoại trừ ký tự new line (dấu xuống dòng).
//                *: Có nghĩa là lặp lại 0 hoặc nhiều lần ký tự trước đó
                if (!registerRequestDTO.getPassword().matches(".*[a-zA-Z].*") || !registerRequestDTO.getPassword().matches(".*\\d.*")) {
                    return new ResponseResult<>("ERROR", "Mật khẩu phải chứa ít nhất một chữ cái và một số", false);
                } else {
                    if (registerRequestDTO.getPassword().matches(".*\\s.*")) {
                        return new ResponseResult<>("ERROR", "Mật khẩu không được chứa khoảng trắng", false);
                    } else {

                        if (!registerRequestDTO.getPassword().matches(".*[A-Z].*")) {
                            return new ResponseResult<>("ERROR", "Mật khẩu phải chứa ít nhất một chữ cái viết hoa", false);
                        }

                        if (!registerRequestDTO.getPassword().matches(".*[a-z].*")) {
                            return new ResponseResult<>("ERROR", "Mật khẩu phải chứa ít nhất một chữ cái thường", false);
                        }

                        if (!registerRequestDTO.getPassword().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                            return new ResponseResult<>("ERROR", "Mật khẩu phải chứa ít nhất một ký tự đặc biệt.", false);
                        }

                        return new ResponseResult<>("SUCCESS", "Người dùng đã đăng ký thành công !!!", true);
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
        Role role = roleRepository.findByRoleName("USER");

        user.setRole(role);
        user.setUserName(registerRequestDTO.getUsername());
        user.setImage("https://jbagy.me/wp-content/uploads/2025/03/Hinh-anh-avatar-nam-cute-2.jpg");
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(encodedPassword);
        user.setStatus("Active");
        user.setOtp(sessionOtp);
        user.setGoogleID("Inactive");
        user.setStatusGG("Inactive");
        userRepository.save(user);

        Wallets wallets = new Wallets();
        wallets.setUser(user);
        wallets.setBalance(BigDecimal.ZERO);
        walletsRepository.save(wallets);
    }

    public User loadUserByEmail(String email) {
        User user = userRepository.FindByEmail(email);
        return user;
    }

    public ResponseResult<User> changePassword(String email, String changePassword) {

        if (email == null) {
            return new ResponseResult<>("ERROR", null, false);
        } else {
            if (changePassword == null) {
                return new ResponseResult<>("ERROR", "Hãy nhập mật khẩu nhé !!!", false);
            } else {
                User user = userRepository.FindByEmail(email);
                if (changePassword.length() < 9) {
                    return new ResponseResult<>("ERROR", "Mật khẩu không được ít hơn 9 ký tự", false);
                } else {
//                .: Đại diện cho một ký tự bất kỳ, ngoại trừ ký tự new line (dấu xuống dòng).
//                *: Có nghĩa là lặp lại 0 hoặc nhiều lần ký tự trước đó
                    if (!changePassword.matches(".*[a-zA-Z].*") || !changePassword.matches(".*\\d.*")) {
                        return new ResponseResult<>("ERROR", "Mật khẩu phải chứa ít nhất một chữ cái và một số", false);
                    } else {
                        if (changePassword.matches(".*\\s.*")) {
                            return new ResponseResult<>("ERROR", "Mật khẩu không được chứa khoảng trắng", false);
                        } else {

                            if (!changePassword.matches(".*[A-Z].*")) {
                                return new ResponseResult<>("ERROR", "Mật khẩu phải chứa ít nhất một chữ cái viết hoa", false);
                            }

                            if (!changePassword.matches(".*[a-z].*")) {
                                return new ResponseResult<>("ERROR", "Mật khẩu phải chứa ít nhất một chữ cái thường", false);
                            }

                            if (!changePassword.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                                return new ResponseResult<>("ERROR", "Mật khẩu phải chứa ít nhất một ký tự đặc biệt.", false);
                            }

                            if (passwordEncoder.matches(changePassword, user.getPassword())){
                                return new ResponseResult<>("ERROR", "Mật khẩu mới không được giống với mật khẩu cũ.", false);
                            }

                            String hashedPassword = passwordEncoder.encode(changePassword);
                            user.setPassword(hashedPassword);
                            userRepository.save(user); // Save in database

                            return new ResponseResult<>("SUCCESS", "Đã đổi mật khẩu thành công !!!", true);
                        }
                    }
                }


            }


        }
    }

    public ResponseResult<User> inserUserGGService(GoogleAccountRequest googleAccountRequest) {

        User user = new User();
        Role role = roleRepository.findByRoleName("USER");

        user.setRole(role);
        user.setUserName(googleAccountRequest.getFamily_name());
        user.setFullName(googleAccountRequest.getName());
        user.setImage(googleAccountRequest.getPicture());
        user.setEmail(googleAccountRequest.getEmail());
        user.setStatus("Active");
        user.setGoogleID(googleAccountRequest.getId());
        user.setStatusGG("Active");
        user.setPassword("");
        userRepository.save(user);

        Wallets wallets = new Wallets();
        wallets.setUser(user);
        wallets.setBalance(BigDecimal.ZERO);
        walletsRepository.save(wallets);

        return new ResponseResult<>("SUCCESS", "Tài khoản người dùng google đăng  nhập thành công !!!", true);
    }


    public UserResponse getUser(User userEntity) {

        UserResponse userResponse = new UserResponse();
        userResponse.setUserID(userEntity.getUserId());
        userResponse.setRole(userEntity.getRole());
        userResponse.setUserName(userEntity.getUserName());
        userResponse.setFullName(userEntity.getFullName());
        userResponse.setImageUrl(userEntity.getImage());
        userResponse.setEmail(userEntity.getEmail());
        userResponse.setPhone(userEntity.getPhone());
        userResponse.setAddress(userEntity.getAddress());
        userResponse.setGender(userEntity.getGender());
        userResponse.setStatus(userEntity.getStatus());

        if (userEntity.getDob() != null) {
            LocalDate dob = userEntity.getDob();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDob = dob.format(formatter);
            userResponse.setDob(formattedDob);
        }else {
            userResponse.setDob(null);
        }
        userResponse.setCreateAt(userEntity.getCreatedAt());
        userResponse.setLastUpdateAt(userResponse.getLastUpdateAt());
        userResponse.setGoogleID(userEntity.getGoogleID());
        userResponse.setStatusGG(userEntity.getStatusGG());

        return userResponse;
    }

    public ResponseResult<ProfileResponse> editProfileUser(ProfileRequest profileRequest) {

        //thanh
        User user = userRepository.FindByEmail(profileRequest.getEmail());

        if(profileRequest.getUserName().length() < 9){
            return new ResponseResult<>("ERROR", "Tên người dùng không được ít hơn 9 ký tự", false);
        }

        CapitalizedEachWord capitalizedEachWord = new CapitalizedEachWord();

        if (!capitalizedEachWord.isCapitalizedEachWord(profileRequest.getFullName())) {
            return new ResponseResult<>("ERROR", "Tên đầy đủ không được viết hoa Mỗi từ", false);
        }

//        if (profileRequest.getFullName().length() < 4) {
//            return new ResponseResult<>("ERROR", "FullName cannot be less than 4 characters", false);
//        }

        String updateInfor = "";

        // Data in database
        if (user.getUserName() != null) {
            if (!profileRequest.getUserName().toLowerCase().equals(user.getUserName().toLowerCase())) {
                updateInfor += "Username:" + profileRequest.getUserName() + ", ";
            }
        } else {
            updateInfor += "Username:" + profileRequest.getUserName() + ", ";
        }

        if (user.getFullName() != null) {
            if (!profileRequest.getFullName().toLowerCase().equals(user.getFullName().toLowerCase())) {
                updateInfor += "Full Name:" + profileRequest.getFullName() + ", ";
            }
        } else {
            updateInfor += "Full Name:" + profileRequest.getFullName() + ", ";
        }

        if (user.getPhone() != null) {
            if (!profileRequest.getPhone().toLowerCase().equals(user.getPhone().toLowerCase())) {
                updateInfor += "Phone:" + profileRequest.getPhone() + ", ";
            }
        } else {
            updateInfor += "Phone:" + profileRequest.getPhone() + ", ";
        }

        if (user.getAddress() != null) {
            if (!profileRequest.getAddress().toLowerCase().equals(user.getAddress().toLowerCase())) {
                updateInfor += "Address:" + profileRequest.getAddress() + ", ";
            }
        } else {
            updateInfor += "Address:" + profileRequest.getAddress() + ", ";
        }

        if (user.getGender() != null) {
            if (!profileRequest.getGender().toLowerCase().equals(user.getGender().toLowerCase())) {
                updateInfor += "Gender:" + profileRequest.getGender() + ", ";
            }
        } else {
            updateInfor += "Gender:" + profileRequest.getGender() + ", ";
        }


        if (user.getDob() != null) {
            if (!profileRequest.getDob().equals(user.getDob())) {
                updateInfor += "Dob:" + profileRequest.getDob() + ", ";
            }
        } else {
            updateInfor += "Dob:" + profileRequest.getDob() + ", ";
        }

        // Xoá dấu ", " ở cuối nếu có
        if (!updateInfor.isEmpty() && updateInfor.endsWith(", ")) {
            updateInfor = updateInfor.substring(0, updateInfor.length() - 2);
        }

        // UpdateProfileHistory
        UpdateProfileHistory updateProfileHistory = new UpdateProfileHistory();
        updateProfileHistory.setUser(user);
        updateProfileHistory.setUpdateInfo(updateInfor);
        updateProfileHistory.setUpdateTime(new Date());
        updateProfileHistoryRepository.save(updateProfileHistory);

        // Updateuser
        user.setUserName(profileRequest.getUserName());
        user.setFullName(profileRequest.getFullName());
        user.setPhone(profileRequest.getPhone());
        user.setAddress(profileRequest.getAddress());
        user.setGender(profileRequest.getGender());
        System.out.println("hello ae" + profileRequest.getDob());
        user.setDob(profileRequest.getDob());
        user.setLastTimeUpdatePass(LocalDateTime.now());
        userRepository.save(user);

        // Check ProfileResponse
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setUserID(user.getUserId());
        profileResponse.setEmail(user.getEmail());
        profileResponse.setUserName(user.getFullName());
        profileResponse.setFullName(user.getFullName());
        profileResponse.setPhone(user.getPhone());
        profileResponse.setAddress(user.getAddress());
        profileResponse.setGender(user.getGender());

        if (user.getDob() != null) {
            LocalDate dob = user.getDob(); // kiểu Date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDob = dob.format(formatter);
            profileResponse.setDob(formattedDob);
        } else {
            profileResponse.setDob(null);
        }

        profileResponse.setLastUpdateAt(user.getLastTimeUpdatePass());

        return new ResponseResult<>("SUCCESS", "Chỉnh sửa hồ sơ thành công !!!", true);
    }

    public ResponseResult<User> editProfileUserImage(String email, String imagePath, MultipartFile file) {

        if (file != null && !file.isEmpty()) {
            User user = userRepository.FindByEmail(email);
            user.setImage(imagePath);
            userRepository.save(user);
            return new ResponseResult<>("SUCCESS", "Chỉnh sửa hình ảnh thành công !!!", true);
        } else {
            return new ResponseResult<>("ERROR", "Vui lòng chọn hình ảnh để chỉnh sửa !!!", false);
        }

    }

    public ResponseResult<User> checkOldPassword(String email, String oldPassword) {
        User user = userRepository.FindByEmail(email);
        if (!passwordEncoder.matches(oldPassword, user.getPassword())){
            return new ResponseResult<>("ERROR", "Mật khẩu cũ không khớp !!!", false);
        } else {
            return new ResponseResult<>("SUCCESS", "Mật khẩu cũ thành công !!!", true);
        }
    }

    public ResponseResult<User> deleteAccount(String email) {

        User user = userRepository.FindByEmail(email);
        user.setStatus("Inactive");
        userRepository.save(user);

        return new ResponseResult<>("SUCCESS", "Xóa tài khoản thành công !!!", true);
    }
}
