package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.entities.User;

public interface UserService {
    void saveUser(User theUser);
    User findByUsername(String username);
}
