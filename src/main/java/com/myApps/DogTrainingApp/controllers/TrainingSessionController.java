package com.myApps.DogTrainingApp.controllers;

import com.myApps.DogTrainingApp.entities.TrainingSession;
import com.myApps.DogTrainingApp.service.TrainingSessionService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class TrainingSessionController {

    private TrainingSessionService trainingSessionService;

    public TrainingSessionController(TrainingSessionService trainingSessionService){
        this.trainingSessionService=trainingSessionService;
    }

    @PostMapping("/saveTraining")
    public String saveTraining(@ModelAttribute("trainingSession") TrainingSession trainingSession, Model theModel){
        return "redirect:/";
    }
}
