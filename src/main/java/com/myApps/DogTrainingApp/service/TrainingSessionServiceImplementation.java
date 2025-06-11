package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.dao.TrainingSessionRepository;
import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.entities.TrainingSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<TrainingSession> findByDog(Dog theDog) {
        return trainingSessionRepository.findByDog(theDog);
    }

    @Override
    public TrainingSession calculateSessionProgress(TrainingSession theSession, Dog theDog, String theCommand) {
        theSession.setDog(theDog);
        theSession.setCommand(theCommand);

        int progress=calculateProgress(theSession);
        theSession.setProgress(progress);
        return theSession;
    }

    private int calculateProgress(TrainingSession theSession) {
        if(theSession.getNr_trials()==0) return 0;
        return(int)(((double) theSession.getNr_successful() / theSession.getNr_trials()) * 100);
    }
}
