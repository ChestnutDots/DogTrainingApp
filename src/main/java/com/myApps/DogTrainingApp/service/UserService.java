package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.entities.User;
import org.springframework.stereotype.Service;

public interface UserService {
    void saveUser(User theUser);
    User findByUsername(String username);
}
