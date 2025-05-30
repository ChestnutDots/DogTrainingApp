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
        if(trainingSession.getNr_successful()>trainingSession.getNr_trials()){
            throw new IllegalArgumentException("Number of successful trials cannot exceed the number of total trials.");
        }
        if(trainingSession.getNr_trials()<0 || trainingSession.getNr_successful()<0){
            throw new IllegalArgumentException("Number of trials must be positive.");
        }
        if(trainingSession.getNr_trials()>500){
            throw new IllegalArgumentException("Number of trials cannot exceed 500.");
        }
        trainingSessionRepository.save(trainingSession);
    }
}
