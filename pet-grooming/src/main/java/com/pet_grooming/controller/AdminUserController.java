package com.pet_grooming.controller;

import com.pet_grooming.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    // Mostrar todos los usuarios (solo para el administrador)
    @GetMapping("/users")
    public String viewAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }
}
