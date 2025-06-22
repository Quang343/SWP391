package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.admin.RoleDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.Role;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

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
    public String saveRole(@ModelAttribute @Valid RoleDTO roleDTO,
                           BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "BackEnd/Admin/Add_Role";
        }
        if(roleService.existsByName(roleDTO.getName().toUpperCase())) {
            result.rejectValue("name", "error.name", "Role name already exists");
            return "BackEnd/Admin/Add_Role";
        }
        try{
            Role role = roleService.createRole(roleDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Role added successfully");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add role: " + e.getMessage());
        }
        return "redirect:/admin/roles";
    }

    @GetMapping("/admin/edit_role/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) throws Exception {
        Role role = roleService.findById(id);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());
        roleDTO.setDescription(role.getDescription());
        roleDTO.setStatus(role.getStatus());
        model.addAttribute("roleDTO", roleDTO);
        model.addAttribute("id", id);
        return  "BackEnd/Admin/Edit_Role";
    }

    @PutMapping("/admin/update_role/{id}")
    public String updateRole(@PathVariable("id") Long id,
                             @ModelAttribute RoleDTO roleDTO,
                             RedirectAttributes redirectAttributes){
        try {
            Role role = roleService.updateRole(id, roleDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Role updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update role: " + e.getMessage());
        }
        return "redirect:/admin/roles";
    }

    @DeleteMapping("/admin/delete_role/{id}")
    public String deleteRole(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        try{
            roleService.deleteRole(id);
            redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully!");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete category: " + e.getMessage());
        }
        return "redirect:/admin/roles";
    }
}
