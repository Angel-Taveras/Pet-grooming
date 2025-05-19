package com.pet_grooming.controller;

import com.pet_grooming.entity.Service;
import com.pet_grooming.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/services")
class AdminServiceController {

    @Autowired
    private ServiceRepository serviceRepository;


    @GetMapping
    public String listServices(Model model) {
        model.addAttribute("services", serviceRepository.findAll());
        return "admin/services";
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("service", new Service());
        return "admin/service_form";
    }


    @PostMapping("/save")
    public String saveService(@ModelAttribute("service") Service service) {
        serviceRepository.save(service);
        return "redirect:/admin/services";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Service service = serviceRepository.findById(id).orElseThrow();
        model.addAttribute("service", service);
        return "admin/service_form"; // Reutiliza el mismo formulario
    }


    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable Integer id) {
        serviceRepository.deleteById(id);
        return "redirect:/admin/services";
    }
}
