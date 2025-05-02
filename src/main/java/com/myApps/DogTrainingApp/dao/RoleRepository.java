package com.myApps.DogTrainingApp.dao;

import com.myApps.DogTrainingApp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
