package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.RoleDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Role;
import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplication;
import com.example.AgriculturalWarehouseManagement.Backend.models.SellerApplicationStatus;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.RoleService;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.user.UserService;
import com.example.AgriculturalWarehouseManagement.Backend.services.seller.SellerApplicationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RoleController {

    private final UserService userService;
    private final RoleService roleService;private final SellerApplicationService sellerApplicationRepository;

    @RequestMapping("/admin/add_role")
    public String addRole(Model model) {
        model.addAttribute("roleDTO", new RoleDTO());
        return "BackEnd/Admin/Add_Role";
    }

    @RequestMapping("/admin/roles")
    public String adminRole(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "BackEnd/Admin/All_Role";
    }

    @PostMapping("/admin/saveRole")
    public ResponseEntity<?> saveRole(@ModelAttribute @Valid RoleDTO roleDTO,
                                      BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        if (roleService.existsByNameIgnoreCase(roleDTO.getName())) {
            return ResponseEntity.badRequest().body(Map.of("name", "Role name already exists"));
        }

        try {
            Role role = roleService.createRole(roleDTO);
            return ResponseEntity.ok().body(Map.of("message", "Role added successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Failed to add role"));
        }
    }

    @GetMapping("/admin/edit_role/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) throws Exception {
        Role role = roleService.findById(id);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getRoleName());
        roleDTO.setDescription(role.getDescription());
        roleDTO.setStatus(role.getStatus());
        model.addAttribute("roleDTO", roleDTO);
        model.addAttribute("id", id);
        return "BackEnd/Admin/Edit_Role";
    }

    @PutMapping("/admin/update_role/{id}")
    public String updateRole(@PathVariable("id") Long id,
                             @ModelAttribute RoleDTO roleDTO,
                             RedirectAttributes redirectAttributes) {
        try {
            Role role = roleService.updateRole(id, roleDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Role updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update role: " + e.getMessage());
        }
        return "redirect:/admin/roles";
    }

    @DeleteMapping("/admin/delete_role/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable("id") Long id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.ok().body(Map.of("message", "Role deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/admin/restore_role/{id}")
    public ResponseEntity<?> restoreRole(@PathVariable("id") Long id) {
        try {
            roleService.restoreRole(id);
            return ResponseEntity.ok().body(Map.of("message", "Role restored successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/admin/assign_role")
    public String assignRole(Model model) {
        Role adminRole = roleService.findByName("ADMIN");
        List<Role> roles = roleService.findByStatusAndNameIsNot("ACTIVE", adminRole.getRoleName());
        List<User> users = userService.findByRoleIsNot(adminRole);
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return "BackEnd/Admin/Assign_Role";
    }

    @PutMapping("/api/user/upgrade-to-seller")
    @ResponseBody
    public ResponseEntity<?> upgradeToSeller(HttpSession session) {
        Integer accountId = (Integer) session.getAttribute("accountId");
        if (accountId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Bạn chưa đăng nhập"));
        }

        try {
            User user = userService.findById(accountId.longValue());

            // Đổi vai trò sang SELLER
            Role sellerRole = roleService.findByName("SELLER");
            user.setRole(sellerRole);
            userService.save(user);

            // Cập nhật trạng thái application từ APPROVED → COMPLETED
            sellerApplicationRepository.findByUserAndStatus(user, SellerApplicationStatus.APPROVED)
                    .ifPresent(application -> {
                        application.setStatus(SellerApplicationStatus.COMPLETED);
                        sellerApplicationRepository.save(application);
                    });

            return ResponseEntity.ok(Map.of("message", "Đổi vai trò sang SELLER thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Không thể đổi vai trò: " + e.getMessage()));
        }
    }



}
