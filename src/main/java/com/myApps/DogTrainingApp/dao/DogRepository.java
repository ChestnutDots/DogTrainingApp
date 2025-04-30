package com.myApps.DogTrainingApp.dao;

import com.myApps.DogTrainingApp.entities.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Integer> {
}
