package com.example.AgriculturalWarehouseManagement.Backend;

import com.example.AgriculturalWarehouseManagement.dtos.RoleDTO;
import com.example.AgriculturalWarehouseManagement.dtos.UserDTO;
import com.example.AgriculturalWarehouseManagement.models.*;
import com.example.AgriculturalWarehouseManagement.services.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @GetMapping("/admin/users")
    public String getAllUsers(Model model){
        List<User> users = userService.findAll();
//        List<User> users = userService.findByStatusIsNot("Ban");
        model.addAttribute("users", users);
        return "BackEnd/Admin/All_Users";
//        return ResponseEntity.ok().body(users);
    }

    @RequestMapping("/admin/add_user")
    public String showCreateForm(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("userDTO", new UserDTO());
        return "BackEnd/Admin/Add_User";
    }

    private UserDTO config(User user) {
        modelMapper.typeMap(user.getClass(), UserDTO.class);
//                .addMappings(mapper -> mapper.skip(User::setUserId));
        return modelMapper.map(user, UserDTO.class);
    }


    @RequestMapping("/admin/edit_user/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        UserDTO userDTO = config(user);
        List<Role> roles = roleService.findAll();
        if(userDTO.getDob() != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dobFormatted = userDTO.getDob().format(formatter);
            model.addAttribute("dobFormatted", dobFormatted);
        }
        model.addAttribute("roles", roles);
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("id", id);
        return "BackEnd/Admin/Edit_User";
    }

    @PostMapping("/admin/saveUser")
    public String saveUser(@ModelAttribute("userDTO") @Valid UserDTO userDTO,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "BackEnd/Admin/Add_User";
        }
        if(userService.existsByPhone(userDTO.getPhone())){
            bindingResult.rejectValue("phone", "error.phone", "Phone number already exists");
            return "BackEnd/Admin/Add_User";
        }
        try {
            User user = userService.createUser(userDTO);
            redirectAttributes.addFlashAttribute("successMessage", "User added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add user: " + e.getMessage());
        }
        return "redirect:/admin/users";
    }

    @PutMapping("/admin/update_user/{id}")
    public String updateRole(@PathVariable("id") Long id,
                             @ModelAttribute UserDTO userDTO,
                             RedirectAttributes redirectAttributes){
        try {
            User user = userService.updateUser(id, userDTO);
            redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update user: " + e.getMessage());
        }
        return "redirect:/admin/users";
    }

    @RequestMapping("backend/login")
    public String login() {
        return "BackEnd/Admin/login";
    }

    @DeleteMapping("/admin/delete_user/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean isDeleted = userService.deleteUser(id);
        if(isDeleted)
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
        else
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete");
        return "redirect:/admin/users";
    }
}
