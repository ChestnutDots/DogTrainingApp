package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.dao.DogRepository;
import com.myApps.DogTrainingApp.entities.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogServiceImplementation implements DogService{

    private DogRepository dogRepository;

    @Autowired
    public DogServiceImplementation(DogRepository theDogRepository){
        this.dogRepository = theDogRepository;
    }

    public Dog save(Dog theDog){
        return dogRepository.save(theDog);
    }
}
