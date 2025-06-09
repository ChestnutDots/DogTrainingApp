package com.myApps.DogTrainingApp.controllers;

import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.entities.User;
import com.myApps.DogTrainingApp.service.DogService;
import com.myApps.DogTrainingApp.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private UserService userService;
    private DogService dogService;

    public HomeController(UserService userService, DogService dogService){
        this.userService=userService;
        this.dogService=dogService;
    }

    @GetMapping("/")
    public String showMainPage(Model model){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User theUser=userService.findByUsername(username);
        List<Dog> allDogs=dogService.findAllByUser(theUser);
        model.addAttribute("dogs", allDogs);
        return "main";
    }
}
