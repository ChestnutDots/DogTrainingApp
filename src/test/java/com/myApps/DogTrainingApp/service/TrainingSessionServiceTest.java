package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.dao.TrainingSessionRepository;
import com.myApps.DogTrainingApp.entities.TrainingSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TrainingSessionServiceTest {

    @Mock
    private TrainingSessionRepository trainingSessionRepository;

    @InjectMocks
    private TrainingSessionServiceImplementation trainingSessionServiceImplementation;

    @Test
    public void testSaveTraining_tooManySuccessTrials(){
        TrainingSession theSession = new TrainingSession();
        theSession.setNr_successful(10);
        theSession.setNr_trials(2);
        assertThrows(IllegalArgumentException.class, () -> trainingSessionServiceImplementation.save(theSession));
    }

    @Test
    public void testSaveTraining_negativeSuccesfulTrials(){
        TrainingSession theSession = new TrainingSession();
        theSession.setNr_successful(-10);
        theSession.setNr_trials(10);
        assertThrows(IllegalArgumentException.class, () -> trainingSessionServiceImplementation.save(theSession));
    }

    @Test
    public void testSaveTraining_negativeTotalTrials(){
        TrainingSession theSession = new TrainingSession();
        theSession.setNr_successful(-100);
        theSession.setNr_trials(-10);
        assertThrows(IllegalArgumentException.class, () -> trainingSessionServiceImplementation.save(theSession));
    }

    @Test
    public void testSaveTraining_TotalTrialNumberExceeded(){
        TrainingSession theSession = new TrainingSession();
        theSession.setNr_successful(10);
        theSession.setNr_trials(1000);
        assertThrows(IllegalArgumentException.class, () -> trainingSessionServiceImplementation.save(theSession));
    }
}
