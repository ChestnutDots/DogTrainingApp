package com.myApps.DogTrainingApp.controllers;

import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.entities.User;
import com.myApps.DogTrainingApp.service.DogService;
import com.myApps.DogTrainingApp.service.TrainingSessionService;
import com.myApps.DogTrainingApp.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private DogService dogService;
    private UserService userService;
    private TrainingSessionService trainingSessionService;

    public UserController(DogService dogService, UserService userService,
                          TrainingSessionService trainingSessionService){
        this.dogService=dogService;
        this.userService=userService;
        this.trainingSessionService=trainingSessionService;
    }

    @GetMapping("/")
    public String showMainPage(Model model){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User theUser=userService.findByUsername(username);
        List<Dog> allDogs=dogService.findAllByUser(theUser);
        model.addAttribute("dogs", allDogs);
        return "main";
    }

    @GetMapping("/addUser")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "user-registration-form";
    }

    @PostMapping("/saveUser")
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

    @GetMapping("/showDogProfile")
    public String showDogProfile(@RequestParam("dogId") int theId, Model model){

        Dog theDog=dogService.findById(theId);
        model.addAttribute("dog", theDog);
        List<Map<String,Number>> chartData = trainingSessionService.findByDog(theDog)
                .stream()
                .map(session -> {
                    Map<String, Number> point = new HashMap<>();
                    point.put("trainingSessionId", session.getId());
                    point.put("trainingProgress", session.getProgress());
                    return point;
                })
                .collect(Collectors.toList());
        model.addAttribute("chartData", chartData);
        return "dog-profile";
    }
}
