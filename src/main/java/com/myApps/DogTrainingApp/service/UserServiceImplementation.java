package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.dao.RoleRepository;
import com.myApps.DogTrainingApp.dao.UserRepository;
import com.myApps.DogTrainingApp.entities.Role;
import com.myApps.DogTrainingApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                    RoleRepository roleRepository){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
    }


    @Override
    public void saveUser(User theUser) {
        String name= theUser.getUsername();
        if(name == null || !name.matches("[A-Za-z0-9]+")){
            throw new IllegalArgumentException("Username can only have letters and numbers as characters.");
        }
        String password = theUser.getPassword();
        if(password == null || !password.matches("[A-Za-z0-9!?*_.]+")){
            throw new IllegalArgumentException("Password can only have letters, numbers and !?*_. as characters.");
        }
        theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
        userRepository.save(theUser);
        Role role= new Role("ROLE_USER", theUser);
        roleRepository.save(role);
    }

    @Override
    public User findByUsername(String username) {
        User theUser=userRepository.findByUsername(username);
        return theUser;
    }
}
