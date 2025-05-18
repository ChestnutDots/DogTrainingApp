package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.entities.TrainingSession;
import org.springframework.stereotype.Service;


public interface TrainingSessionService {
    public void save(TrainingSession trainingSession);
}
