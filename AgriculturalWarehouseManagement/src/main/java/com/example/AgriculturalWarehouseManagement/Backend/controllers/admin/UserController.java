package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.UserResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.UpdateProfileRequest;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.UserDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Role;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.RoleService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.user.UserService;
import com.example.AgriculturalWarehouseManagement.Backend.services.seller.User_SellerService;
import com.example.AgriculturalWarehouseManagement.Backend.utils.PaginationUtils;
import com.example.AgriculturalWarehouseManagement.Backend.utils.StoreFile;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final StoreFile storeFile;
    private final User_SellerService userSellerService;

    @GetMapping("/admin/users")
    public String getAllUsers(Model model,
                              @RequestParam("page") Optional<String> page){
//        List<User> users = userService.findAll();
//        List<User> users = userService.findByStatusIsNot("Ban");
        int pageNumber = 1;
        try{
            if (page.isPresent()) {
                pageNumber = Integer.parseInt(page.get());
                if(pageNumber < 1){
                    pageNumber = 1;
                }
            }
            else{
                //pageNumber = 1
            }
        }catch (Exception e){
            //pageNumber = 1
            //Handle exception
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        Page<User> pageUser = userService.findAll(pageable);
        List<User> users = pageUser.getContent();
        users = users.stream().filter(user -> !user.getRole().getRoleName().equalsIgnoreCase("Admin")).toList();
        int totalPages = pageUser.getTotalPages();
        model.addAttribute("users", users);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageNumbers", PaginationUtils.generatePageNumbers(totalPages, pageNumber));
        model.addAttribute("totalPages", totalPages);
        return "BackEnd/Admin/All_Users";
    }

    @RequestMapping("/admin/add_user")
    public String showCreateForm(Model model) {
        List<Role> roles = roleService.findAll();
        roles = roles.stream().filter(r -> !r.getRoleName().equalsIgnoreCase("ADMIN")).collect(Collectors.toList());
        model.addAttribute("roles", roles);
        model.addAttribute("userDTO", new UserDTO());
        return "BackEnd/Admin/Add_User";
    }

    private UserDTO config(User user) {
        modelMapper.typeMap(user.getClass(), UserDTO.class)
                .addMappings(mapper -> mapper.skip(UserDTO::setImageName));
        return modelMapper.map(user, UserDTO.class);
    }


    @RequestMapping("/admin/edit_user/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        UserDTO userDTO = config(user);
        userDTO.setImageName(user.getImage());
        List<Role> roles = roleService.findAll();
        roles = roles.stream().filter(r -> !r.getRoleName().equals("ADMIN")).collect(Collectors.toList());
        model.addAttribute("roles", roles);
        model.addAttribute("role", user.getRole().getRoleName());
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("id", id);
        return "BackEnd/Admin/Edit_User";
    }

    @PostMapping("/admin/saveUser")
    public ResponseEntity<?> saveUser(@ModelAttribute("userDTO") @Valid UserDTO userDTO,
                                      BindingResult bindingResult,
                                      @RequestPart(value = "image", required = false) MultipartFile image
    ){
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }

        if(userService.existsByEmail(userDTO.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("email", "Email already exists"));
        }
//        MultipartFile image = userDTO.getImage();
        try {
            // mã hóa mật khẩu
            String hashPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(hashPassword);
            User user = userService.createUser(userDTO); // lưu user trước

            // lưu ảnh sau khi có userId
            if (image != null && !image.isEmpty()) {
                try {
                    userSellerService.saveAvatar((long) user.getUserId(), image);
                    // Không cần set lại `image`, vì trong `saveAvatar()` bạn đã `user.setImage(...)` rồi và `userRepository.save(user)` rồi.
                    userService.save(user); // cập nhật lại image sau khi upload
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body("Image upload failed");
                }
            }

            return ResponseEntity.ok().body(Map.of("message", "User added successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/admin/update_user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,
                             @ModelAttribute UserDTO userDTO,
                             BindingResult bindingResult,
                             @RequestPart(value = "image", required = false) MultipartFile image){
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        User user = userService.findById(id);

        String newFileName = null;
        if (image != null && !image.isEmpty()) {
            try {
                newFileName = userSellerService.saveAvatar(id, image);
                userDTO.setImageName(newFileName.substring(newFileName.lastIndexOf("/") + 1));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Image upload failed");
            }
        } else {
            userDTO.setImageName(user.getImage());
        }

        try {
            String presentedPassword = userDTO.getPassword();
            if(!presentedPassword.equals(user.getPassword())){
                String hashPassword = passwordEncoder.encode(userDTO.getPassword());
                userDTO.setPassword(hashPassword);
            }
            userService.updateUser(id, userDTO);
            return ResponseEntity.ok().body(Map.of("message", "User updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.ok().body(Map.of("message", "User updated failed!"));
        }
    }

    @Value("${app.upload.product-dir}")
    private String uploadDir;

    public void deleteFile(String fileName){
        try{
            Path filePath = Paths.get(uploadDir, "Admin").resolve(fileName);
            Files.deleteIfExists(filePath);
        }catch (Exception e){
            System.err.println("Failed to delete old file: " + e.getMessage());
        }
    }

    @DeleteMapping("/admin/delete_user/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        boolean isDeleted = userService.deleteUser(id);
        if(isDeleted)
            return ResponseEntity.ok().body(Map.of("message", "User deleted successfully"));
        else
            return ResponseEntity.badRequest().body(Map.of("message", "Delete user failed!"));
    }

//    @PutMapping("/admin/profile/update")
//    public ResponseEntity<?> updateProfile(
//            @ModelAttribute UpdateProfileRequest request,
//            @RequestParam(value = "image", required = false) MultipartFile image,
//            Authentication authentication
//    ) {
//        try {
//            String email = authentication.getName();
//            User user = userService.findByEmail(email);
//            if (user == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//            }
//
//            // Cập nhật thông tin từ form
//            user.setFullName(request.getFullName());
//            user.setPhone(request.getPhone());
//            user.setAddress(request.getAddress());
//            user.setGender(request.getGender());
//            user.setDob(request.getDob());
//
//            String newFileName = null;
//            if (image != null && !image.isEmpty()) {
//                String oldFileName = user.getImage();
//                if(oldFileName != null && !oldFileName.isBlank()){
//                    deleteFile(oldFileName);
//                }
//                try {
//                    newFileName = storeFile.storeFile(image);
//                    user.setImage(newFileName);
//                }catch (Exception e){
//                    return ResponseEntity.badRequest().body("Image upload failed");
//                }
//            }
//
//            userService.save(user);
//
//            return ResponseEntity.ok().body(Map.of("message", "Cập nhật thành công"));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating profile");
//        }
//    }

    @PutMapping("/admin/profile/update")
    public ResponseEntity<?> updateProfile(
            HttpSession session,
            @ModelAttribute UpdateProfileRequest request,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        try {
            UserResponse userResponse = (UserResponse)session.getAttribute("accountAdmin");
            User user = userService.findById(userResponse.getUserID() * 1L);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // Cập nhật thông tin từ form
            user.setFullName(request.getFullName());
            user.setPhone(request.getPhone());
            user.setAddress(request.getAddress());
            user.setGender(request.getGender());
            user.setDob(request.getDob());

            String newFileName = null;
            if (image != null && !image.isEmpty()) {
                String oldFileName = user.getImage();
                if(oldFileName != null && !oldFileName.isBlank()){
                    deleteFile(oldFileName);
                }
                try {
                    newFileName = storeFile.storeFile(image);
                    user.setImage(newFileName);
                }catch (Exception e){
                    return ResponseEntity.badRequest().body("Image upload failed");
                }
            }

            userService.save(user);

            return ResponseEntity.ok().body(Map.of("message", "Cập nhật thành công"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating profile");
        }
    }

//    @PutMapping("/admin/profile/change-password")
//    public ResponseEntity<?> changePassword(@RequestParam String currentPassword,
//                                            @RequestParam String newPassword,
//                                            @RequestParam String confirmPassword,
//                                            Principal principal){
//        Map<String,Object> response = new HashMap<>();
//        String email = principal.getName();
//        User user = userService.findByEmail(email);
//        if(currentPassword == null || !passwordEncoder.matches(currentPassword, user.getPassword())){
//            response.put("success", false);
//            response.put("message", "Mật khẩu hiện tại không đúng!");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
//        if(newPassword == null || !newPassword.equals(confirmPassword)){
//            response.put("success", false);
//            response.put("message", "Mật khẩu xác nhận không khớp!");
//            return ResponseEntity.badRequest().body(response);
//        }
//        if(!passwordEncoder.matches(newPassword, user.getPassword())){
//            user.setPassword(passwordEncoder.encode(newPassword));
//            userService.save(user);
//        }
//        response.put("success", true);
//        response.put("message", "Đổi mật khẩu thành công");
//        return ResponseEntity.ok().body(response);
//    }

    @PutMapping("/admin/profile/change-password")
    public ResponseEntity<?> changePassword(@RequestParam String currentPassword,
                                            @RequestParam String newPassword,
                                            @RequestParam String confirmPassword,
                                            HttpSession session){
        Map<String,Object> response = new HashMap<>();
        UserResponse userResponse = (UserResponse)session.getAttribute("accountAdmin");
        User user = userService.findByEmail(userResponse.getEmail());
        if (user == null) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        if(!passwordEncoder.matches(currentPassword, user.getPassword())){
            response.put("success", false);
            response.put("message", "Mật khẩu hiện tại không đúng!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if(newPassword == null || !newPassword.equals(confirmPassword)){
            response.put("success", false);
            response.put("message", "Mật khẩu xác nhận không khớp!");
            return ResponseEntity.badRequest().body(response);
        }
        if(!passwordEncoder.matches(newPassword, user.getPassword())){
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.save(user);
        }
        response.put("success", true);
        response.put("message", "Đổi mật khẩu thành công");
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/admin/users/{userId}/role")
    @ResponseBody
    public ResponseEntity<?> assignRoleForUser(@PathVariable("userId") Long id,
                                               @RequestBody Map<String, String> info){

        try {
            String roleKey = info.get("role");
            Role role = roleService.findByName(roleKey);
            User user = userService.findById(id);
            user.setRole(role);
            userService.save(user);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "roleName", user.getRole().getRoleName()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        }
    }
}

