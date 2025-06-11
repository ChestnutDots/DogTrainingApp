package com.myApps.DogTrainingApp.controllers;

import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.entities.TrainingSession;
import com.myApps.DogTrainingApp.service.DogService;
import com.myApps.DogTrainingApp.service.TrainingSessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/training")
public class TrainingSessionController {

    private TrainingSessionService trainingSessionService;
    private DogService dogService;

    public TrainingSessionController(TrainingSessionService trainingSessionService,
                                     DogService dogService){
        this.trainingSessionService=trainingSessionService;
        this.dogService=dogService;
    }

    @GetMapping("/new")
    public String addNewTrainingSession(@RequestParam("dogId") int theId, Model theModel){
        Dog theDog=dogService.findById(theId);
        theModel.addAttribute("dog", theDog);
        theModel.addAttribute("trainingSession", new TrainingSession());
        return "new-training";
    }

    @PostMapping("/save")
    public String saveTraining(@ModelAttribute("trainingSession") TrainingSession trainingSession,
                               @RequestParam("command") String command,
                               @RequestParam("dogId") int theId){
        Dog theDog=dogService.findById(theId);
        TrainingSession theSession=trainingSessionService.calculateSessionProgress(trainingSession, theDog, command);
        trainingSessionService.save(theSession);
        return "redirect:/";
    }
}
