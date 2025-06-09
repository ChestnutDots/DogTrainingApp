package com.myApps.DogTrainingApp.controllers;

import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.entities.User;
import com.myApps.DogTrainingApp.service.DogService;
import com.myApps.DogTrainingApp.service.TrainingSessionService;
import com.myApps.DogTrainingApp.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private DogService dogService;
    private UserService userService;

    public UserController(DogService dogService, UserService userService,
                          TrainingSessionService trainingSessionService){
        this.dogService=dogService;
        this.userService=userService;
    }

    @GetMapping("/new")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "user-registration-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User theUser, Model model){
        try{
            userService.saveUser(theUser);
            return "redirect:/login";
        }catch(RuntimeException ex){
            model.addAttribute("user", theUser);
            model.addAttribute("errorMessage", ex.getMessage());
            return "user-registration-form";
        }
    }


}
