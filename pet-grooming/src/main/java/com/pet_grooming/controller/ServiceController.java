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

    // Listar todos los servicios
    @GetMapping
    public String listServices(Model model) {
        model.addAttribute("services", serviceRepository.findAll());
        return "admin/services"; // Esta vista debe estar en templates/admin/services.html
    }

    // Mostrar formulario para crear nuevo servicio
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("service", new Service());
        return "admin/service_form"; // Esta vista debe estar en templates/admin/service_form.html
    }

    // Guardar servicio nuevo o actualizado
    @PostMapping("/save")
    public String saveService(@ModelAttribute("service") Service service) {
        serviceRepository.save(service);
        return "redirect:/admin/services";
    }

    // Mostrar formulario para editar servicio
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Service service = serviceRepository.findById(id).orElseThrow();
        model.addAttribute("service", service);
        return "admin/service_form"; // Reutiliza el mismo formulario
    }

    // Eliminar servicio
    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable Integer id) {
        serviceRepository.deleteById(id);
        return "redirect:/admin/services";
    }
}
