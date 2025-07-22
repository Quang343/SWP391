package com.example.AgriculturalWarehouseManagement.Backend.services.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class GlobalUserAdvice {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addUserToModel(Model model, Principal principal) {
        if (principal != null && !model.containsAttribute("user")) {
            com.example.AgriculturalWarehouseManagement.Backend.models.User user =
                    userService.findByEmail(principal.getName());
            model.addAttribute("user", user);
        }
    }
}