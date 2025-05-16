package com.myApps.DogTrainingApp.service;

import org.springframework.beans.factory.annotation.Autowired;

public class TrainingSessionServiceImplementation implements TrainingSessionService{

    private TrainingSessionService trainingSessionService;

    @Autowired
    public TrainingSessionServiceImplementation(TrainingSessionService trainingSessionService){
        this.trainingSessionService=trainingSessionService;
    }


}
