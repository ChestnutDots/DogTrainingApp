package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.dao.DogRepository;
import com.myApps.DogTrainingApp.entities.Dog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DogServiceTest {

    @Mock
    private DogRepository dogRepository;

    @InjectMocks
    private DogServiceImplementation dogServiceImplementation;

    @Test
    public void testSaveDog(){
        Dog theDog = new Dog();
        theDog.setAge(2);
        theDog.setName("Annie");
        theDog.setBreed("Mix");
        when(dogRepository.save(ArgumentMatchers.any(Dog.class))).thenReturn(theDog);
        Dog result=dogServiceImplementation.save(theDog);
        assertEquals(2, result.getAge());
        assertEquals("Annie", result.getName());
        assertEquals("Mix", result.getBreed());
        verify(dogRepository, times(1)).save(theDog);
    }

    @Test
    public void testSaveDog_throwException_whenAgeIsOutOfRange(){
        Dog theDogNeg = new Dog();
        theDogNeg.setAge(-1);
        assertThrows(IllegalArgumentException.class, () -> dogServiceImplementation.save(theDogNeg));

        Dog theDogPos = new Dog();
        theDogPos.setAge(150);
        assertThrows(IllegalArgumentException.class, () -> dogServiceImplementation.save(theDogPos));
    }

    @Test
    public void testSaveDog_throwException_invalidName(){
        Dog theDog = new Dog();
        theDog.setName("!..!");
        theDog.setBreed("German Shepherd");
        assertThrows(IllegalArgumentException.class, () -> dogServiceImplementation.save(theDog));
    }

    @Test
    public void testSaveDog_throwException_invalidBreed(){
        Dog theDog = new Dog();
        theDog.setBreed("???");
        theDog.setName("Charles");
        assertThrows(IllegalArgumentException.class, () -> dogServiceImplementation.save(theDog));
    }

    @Test
    public void testFindById_Found(){
        int theId=15;
        Dog theDog = new Dog();
        theDog.setId(theId);
        when(dogRepository.findById(ArgumentMatchers.any(Integer.class))).thenReturn(Optional.of(theDog));
        Dog result=dogServiceImplementation.findById(theId);
        assertEquals(15, result.getId());
        verify(dogRepository, times(1)).findById(ArgumentMatchers.eq(theId));
    }

    @Test
    public void deleteDog(){
        int theId=3;
        dogServiceImplementation.deleteById(theId);
        verify(dogRepository, times(1)).deleteById(theId);

    }


}
