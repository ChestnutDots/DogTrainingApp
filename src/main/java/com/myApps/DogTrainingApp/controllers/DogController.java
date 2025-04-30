package com.myApps.DogTrainingApp.controllers;

import com.myApps.DogTrainingApp.service.DogService;
import com.myApps.DogTrainingApp.entities.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DogController {

    private DogService dogService;

    public DogController(DogService theDogService){
        this.dogService=theDogService;
    }

    @GetMapping("/addDogs")
    public String showAddNewDog(Model model){
        model.addAttribute("dog", new Dog());
        return "add-dog";
    }

    @PostMapping("/save")
    public String saveDog(@ModelAttribute("dog") Dog theDog){

        dogService.save(theDog);

        return "redirect:/main";
    }

}
