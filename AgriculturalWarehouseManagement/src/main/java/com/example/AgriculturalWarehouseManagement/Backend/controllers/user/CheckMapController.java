package com.example.AgriculturalWarehouseManagement.Backend.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckMapController {
    @Autowired
    private jakarta.servlet.http.HttpSession session;

    @PostMapping("/checkMap")
    public String checkMap(@RequestParam(name = "address") String mapName, Model model) {
        Object account = session.getAttribute("account");
        if (account == null) {
            session.invalidate();
            return "redirect:/login";
        }

        model.addAttribute("fromAddress", "Đại học FPT");
        model.addAttribute("toAddress", mapName);
        return "FrontEnd/Home/map";
    }

}
