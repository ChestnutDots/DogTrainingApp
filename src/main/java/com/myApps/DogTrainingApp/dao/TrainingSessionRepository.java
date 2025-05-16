package com.myApps.DogTrainingApp.dao;

import com.myApps.DogTrainingApp.entities.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Integer> {
}
