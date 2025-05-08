package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.dao.DogRepository;
import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Dog> findAllByUser(User theUser) {
        return dogRepository.findAllByUser(theUser);
    }

    @Override
    public Dog findById(int theId) {
        Optional<Dog> result= dogRepository.findById(theId);
        Dog theDog;
        if(result.isPresent()){
            theDog=result.get();
        }else{
            throw new RuntimeException("Did not find dog id - "+theId);
        }
        return theDog;
    }

}
