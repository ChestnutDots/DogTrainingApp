package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.dao.TrainingSessionRepository;
import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.entities.TrainingSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

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
    public HashMap<String, Integer> findCountsOfCommandsUsed(Dog theDog) {
        return findCountsOfString(theDog, TrainingSession::getCommand);
    }

    @Override
    public HashMap<String, Integer> findCountsOfTreats(Dog theDog) {
        return findCountsOfString(theDog, TrainingSession::getTreat);
    }

    private HashMap<String, Integer> findCountsOfString(Dog theDog,
                                                        Function<TrainingSession, String> fieldExtractor){

        List<TrainingSession> trainingSessions=findByDog(theDog);
        int totalSessions=trainingSessions.size();

        HashMap<String, Integer> stringCounts = new HashMap<>();
        if(totalSessions==0) return stringCounts;

        for(TrainingSession session : trainingSessions){
            String key = fieldExtractor.apply(session);
            int trials = session.getNr_trials();
            stringCounts.put(key, stringCounts.getOrDefault(key, 0) + trials);
        }

        return stringCounts;
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
