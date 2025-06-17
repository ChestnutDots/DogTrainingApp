package com.myApps.DogTrainingApp.controllers;

import com.myApps.DogTrainingApp.entities.TrainingSession;
import com.myApps.DogTrainingApp.entities.User;
import com.myApps.DogTrainingApp.service.ChartService;
import com.myApps.DogTrainingApp.service.DogService;
import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.service.TrainingSessionService;
import com.myApps.DogTrainingApp.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Controller
@RequestMapping("/dogs")
public class DogController {

    private DogService dogService;
    private UserService userService;
    private TrainingSessionService trainingSessionService;
    private ChartService chartService;

    public DogController(DogService dogService, UserService userService,
                         TrainingSessionService trainingSessionService,
                         ChartService chartService){
        this.dogService=dogService;
        this.userService=userService;
        this.trainingSessionService=trainingSessionService;
        this.chartService=chartService;
    }

    @GetMapping("/show")
    public String showDogProfile(@RequestParam("dogId") int theId, Model model){

        Dog theDog=dogService.findById(theId);
        model.addAttribute("dog", theDog);

        List<TrainingSession> trainingSessionsList=trainingSessionService.findByDog(theDog);
        List<Map<String,Number>> trainingData = chartService.buildLineChartData(trainingSessionsList,
                "sessionId", (Function<TrainingSession, Number>) TrainingSession::getId,
                "sessionProgress", (Function<TrainingSession, Number>) TrainingSession::getProgress);

        HashMap<String, Integer> commandPercentage=trainingSessionService.findCountsOfCommandsUsed(theDog);
        List<List<Object>> commandData=chartService.buildPieChartPercentageData(commandPercentage, "Command", "Percentage");

        HashMap<String, Integer> treatPercentage=trainingSessionService.findCountsOfTreats(theDog);
        List<List<Object>> treatData=chartService.buildPieChartPercentageData(treatPercentage, "Treats", "Percentage");

        model.addAttribute("trainingData", trainingData);
        model.addAttribute("commandData", commandData);
        model.addAttribute("treatData", treatData);
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
