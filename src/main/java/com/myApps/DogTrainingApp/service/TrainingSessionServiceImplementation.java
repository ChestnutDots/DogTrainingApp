package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.dao.TrainingSessionRepository;
import com.myApps.DogTrainingApp.entities.TrainingSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingSessionServiceImplementation implements TrainingSessionService{

    private TrainingSessionRepository trainingSessionRepository;

    @Autowired
    public TrainingSessionServiceImplementation(TrainingSessionRepository trainingSessionRepository){
        this.trainingSessionRepository=trainingSessionRepository;
    }


    @Override
    public void save(TrainingSession trainingSession) {
        trainingSessionRepository.save(trainingSession);
    }
}
