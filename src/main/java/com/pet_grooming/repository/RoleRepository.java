package com.pet_grooming.repository;


import com.pet_grooming.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

   Role findByName(String name);
}
