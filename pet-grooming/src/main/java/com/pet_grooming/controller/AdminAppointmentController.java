package com.pet_grooming.controller;

import com.pet_grooming.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminAppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Mostrar todas las citas (para el administrador)
    @GetMapping("/appointments")
    public String viewAllAppointments(Model model) {
        model.addAttribute("appointments", appointmentRepository.findAll());
        return "admin/appointments";
    }
}
