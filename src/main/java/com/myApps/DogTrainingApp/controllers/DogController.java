package com.myApps.DogTrainingApp.controllers;

import com.myApps.DogTrainingApp.entities.TrainingSession;
import com.myApps.DogTrainingApp.entities.User;
import com.myApps.DogTrainingApp.service.DogService;
import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.service.TrainingSessionService;
import com.myApps.DogTrainingApp.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dogs")
public class DogController {

    private DogService dogService;
    private UserService userService;
    private TrainingSessionService trainingSessionService;

    public DogController(DogService dogService, UserService userService,
                         TrainingSessionService trainingSessionService){
        this.dogService=dogService;
        this.userService=userService;
        this.trainingSessionService=trainingSessionService;
    }

    @GetMapping("/show")
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

        HashMap<String, Integer> commandPercentage=trainingSessionService.findCountsOfCommandsUsed(theDog);
        List<List<Object>> percentageData = new ArrayList<>();
        List<Object> header = new ArrayList<>();
        header.add("Command");
        header.add("Percentage");
        percentageData.add(header);

        for(String command : commandPercentage.keySet()){
            Integer percentage= commandPercentage.get(command);
            List<Object> row = new ArrayList<>();
            row.add(command);
            row.add(percentage);

            percentageData.add(row);
        }
        model.addAttribute("chartData", chartData);
        model.addAttribute("percentageData", percentageData);
        return "dog-profile";
    }

    @GetMapping("/new")
    public String showAddNewDog(Model model){
        Dog theDog= new Dog();
        model.addAttribute("dog", theDog);
        return "add-dog";
    }

    @PostMapping("/save")
    public String saveDog(@ModelAttribute("dog") Dog theDog, Model theModel){
        try{
            String username= SecurityContextHolder.getContext().getAuthentication().getName();
            User theUser=userService.findByUsername(username);
            theDog.setUser(theUser);
            dogService.save(theDog);
            return "redirect:/";
        }catch (IllegalArgumentException ex){
            theModel.addAttribute("errorMessage", ex.getMessage());
            return "add-dog";
        }

    }

    @PostMapping("/delete")
    public String deleteDog(@RequestParam("dogId") int theId){
        dogService.deleteById(theId);
        return "redirect:/";
    }





}
