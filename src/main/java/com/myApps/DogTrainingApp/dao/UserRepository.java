package com.myApps.DogTrainingApp.dao;

import com.myApps.DogTrainingApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
