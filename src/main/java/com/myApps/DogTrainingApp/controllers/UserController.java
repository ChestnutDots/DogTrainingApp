package com.myApps.DogTrainingApp.controllers;

import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.service.DogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private DogService dogService;

    public UserController(DogService dogService){
        this.dogService=dogService;
    }

    @GetMapping("/main")
    public String showMainPage(Model model){
        List<Dog> allDogs=dogService.findAll();
        model.addAttribute("dogs", allDogs);
        return "main";
    }
}
