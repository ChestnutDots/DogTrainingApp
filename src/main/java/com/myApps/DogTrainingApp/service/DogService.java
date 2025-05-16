package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.entities.User;

import java.util.List;
import java.util.Optional;

public interface DogService {
    Dog save(Dog theDog);
    List<Dog> findAllByUser(User theUser);
    Dog findById(int theId);
    void deleteById(int theId);
}
