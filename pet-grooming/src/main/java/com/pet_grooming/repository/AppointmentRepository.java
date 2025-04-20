package com.pet_grooming.repository;

import com.pet_grooming.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByPetId(Integer petId);
    List<Appointment> findByDate(LocalDate date);
    List<Appointment> findByPetUserId(Integer userId);


}
