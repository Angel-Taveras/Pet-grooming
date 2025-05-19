package com.pet_grooming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicePageController {



    @GetMapping("/service")
    public String  showService(){
        return "service";
    }

}
