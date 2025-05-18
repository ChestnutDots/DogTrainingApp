package com.myApps.DogTrainingApp.controllers;

import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.entities.TrainingSession;
import com.myApps.DogTrainingApp.service.DogService;
import com.myApps.DogTrainingApp.service.TrainingSessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TrainingSessionController {

    private TrainingSessionService trainingSessionService;
    private DogService dogService;

    public TrainingSessionController(TrainingSessionService trainingSessionService,
                                     DogService dogService){
        this.trainingSessionService=trainingSessionService;
        this.dogService=dogService;
    }



    @PostMapping("/saveTraining")
    public String saveTraining(@ModelAttribute("trainingSession") TrainingSession trainingSession,
                               @RequestParam("command") String command,
                               @RequestParam("dogId") int theId){
        Dog theDog=dogService.findById(theId);
        trainingSession.setDog(theDog);
        trainingSession.setCommand(command);
        Double trainingProgress= trainingSession.getNr_successful()/ trainingSession.getNr_trials();
        trainingSession.setProgress(trainingProgress);
        trainingSessionService.save(trainingSession);
        return "redirect:/";
    }
}
