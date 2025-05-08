package com.myApps.DogTrainingApp.controllers;

import com.myApps.DogTrainingApp.entities.User;
import com.myApps.DogTrainingApp.service.DogService;
import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DogController {

    private DogService dogService;
    private UserService userService;

    public DogController(DogService dogService, UserService userService){
        this.dogService=dogService;
        this.userService=userService;
    }

    @GetMapping("/addDogs")
    public String showAddNewDog(Model model){
        Dog theDog= new Dog();
        model.addAttribute("dog", theDog);
        return "add-dog";
    }

    @PostMapping("/save")
    public String saveDog(@ModelAttribute("dog") Dog theDog){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User theUser=userService.findByUsername(username);
        theDog.setUser(theUser);
        dogService.save(theDog);
        return "redirect:/";
    }

    @GetMapping("/newTraining")
    public String addNewTrainingSession(){
        return "new-training";
    }

}
