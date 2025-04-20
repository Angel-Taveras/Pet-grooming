package com.pet_grooming.repository;

import com.pet_grooming.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    Optional<Pet> findByName(String name);
    List<Pet> findByUserId(Integer userId);

}
