package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.entities.TrainingSession;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TrainingSessionService {
    void save(TrainingSession trainingSession);
    List<TrainingSession> findByDog(Dog theDog);
}
