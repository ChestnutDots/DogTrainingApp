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
        if(theDog.getAge()<0 || theDog.getAge()>99){
            throw new IllegalArgumentException("Age out of range 0-99.");
        }
        String name=theDog.getName();
        if(!name.matches("[A-Za-z]+") || name==null){
            throw new IllegalArgumentException("Name can only contain letters.");
        }
        String breed=theDog.getBreed();
        if(!breed.matches("[A-Za-z]+") || breed==null){
            throw new IllegalArgumentException("Breed can only contain letters.");
        }
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

    @Override
    public void deleteById(int theId) {
        dogRepository.deleteById(theId);
    }

}
