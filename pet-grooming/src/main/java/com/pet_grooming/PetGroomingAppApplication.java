package com.pet_grooming;

import com.pet_grooming.entity.Role;
import com.pet_grooming.entity.User;
import com.pet_grooming.repository.RoleRepository;
import com.pet_grooming.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class PetGroomingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetGroomingAppApplication.class, args);
    }

}
