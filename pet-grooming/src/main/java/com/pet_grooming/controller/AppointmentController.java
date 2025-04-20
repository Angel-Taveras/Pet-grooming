package com.pet_grooming.controller;

import com.pet_grooming.entity.*;
import com.pet_grooming.repository.AppointmentRepository;
import com.pet_grooming.repository.PetRepository;
import com.pet_grooming.repository.ServiceRepository;
import com.pet_grooming.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    // Mostrar todas las citas del usuario autenticado
    @GetMapping
    public String listAppointments(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        List<Appointment> appointments = appointmentRepository.findByPetUserId(user.getId());
        model.addAttribute("appointments", appointments);

        return "appointments";
    }

    // Formulario para nueva cita
    @GetMapping("/new")
    public String showAppointmentForm(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();

        model.addAttribute("appointment", new Appointment());
        model.addAttribute("pets", petRepository.findByUserId(user.getId()));
        model.addAttribute("services", serviceRepository.findAll());

        return "appointment";
    }

    // Guardar nueva cita
    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment,
                                  @RequestParam("serviceIds") List<Integer> serviceIds) {

        List<Service> selectedServices = serviceRepository.findAllById(serviceIds);
        appointment.setServices(selectedServices);
        appointment.setStatus(AppointmentStatus.PENDING);

        appointmentRepository.save(appointment);

        return "redirect:/appointments";
    }

    @GetMapping("/all")
    public String viewAllAppointments(Model model) {
        model.addAttribute("appointments", appointmentRepository.findAll());
        return "admin/admin_appointments";
    }

}
