package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;

import com.example.AgriculturalWarehouseManagement.Backend.services.user.ContactUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ContactUserController {

    private final ContactUserService contactUserService;

    @GetMapping("/admin/contact_users/view")
    public String getAllContactUsers(Model model){
        model.addAttribute("contactUsers", contactUserService.getAllContactUsers());
        return "BackEnd/Admin/AllContactUser";
    }

    @DeleteMapping("/admin/contact_users/delete/{id}")
    public ResponseEntity<?> deleteContactUsers(@PathVariable("id") Integer id){
        try {
            contactUserService.deleteUserContact(id);
            return ResponseEntity.ok().body(Map.of("message", "Xoá thành công"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Người dùng không tồn tại"));
        }
    }
}

