package com.pet_grooming.controller;


import com.pet_grooming.entity.User;
import com.pet_grooming.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/user/edit-profile")
    public String editProfile(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        model.addAttribute("user", user);
        return "edit_profile";
    }

    @PostMapping("/user/update-profile")
    public String updateProfile(@ModelAttribute("user") User userForm,
                                @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            User user = userRepository.findById(userForm.getId()).orElseThrow();
            user.setFirstName(userForm.getFirstName());
            user.setLastName(userForm.getLastName());

            if (!imageFile.isEmpty()) {
                String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                Path uploadPath = Paths.get("src/main/resources/static/uploads/");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Files.copy(imageFile.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                user.setProfileImage("/uploads/" + fileName);
            }

            userRepository.save(user);
            return "redirect:/user/home";
        } catch (IOException e) {
            e.printStackTrace();
            return "edit_profile";
        }
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/home")
    public String userHome(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        model.addAttribute("user", user);
        return "user_home";
    }





}
