package com.pet_grooming.controller;


import com.pet_grooming.entity.Pet;
import com.pet_grooming.entity.User;
import com.pet_grooming.repository.PetRepository;
import com.pet_grooming.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PetController {

    @Autowired
    PetRepository petRepository;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/pets/new")
    public String showPetForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "new_pet";
    }

    @PostMapping("/pets/save")
    public String savePet(@ModelAttribute("pet") Pet pet){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        pet.setUser(user);
        petRepository.save(pet);
        return "redirect:/user/home";
    }

    @GetMapping("/pets")
    public String listUserPets(Model model) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();


        List<Pet> pets = petRepository.findByUserId(user.getId());


        model.addAttribute("pets", pets);


        return "list_pets";
    }

    @GetMapping("/pets/edit/{id}")
    public String showEditPetForm(@PathVariable("id") Integer id, Model model) {
        Pet pet = petRepository.findById(id).orElseThrow();
        model.addAttribute("pet", pet);
        return "edit_pet";
    }

    @PostMapping("/pets/update")
    public String updatePet(@ModelAttribute("pet") Pet pet) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        pet.setUser(user);

        petRepository.save(pet);
        return "redirect:/pets";
    }


    @GetMapping("/pets/delete/{id}")
    public String deletePet(@PathVariable("id") Integer id) {
        petRepository.deleteById(id);
        return "redirect:/pets";
    }



}
