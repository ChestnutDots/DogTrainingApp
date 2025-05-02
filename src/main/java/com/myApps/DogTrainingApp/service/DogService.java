package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.entities.User;

import java.util.List;

public interface DogService {
    Dog save(Dog theDog);
    List<Dog> findAllByUser(User theUser);
}
