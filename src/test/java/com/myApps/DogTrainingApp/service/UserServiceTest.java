package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.dao.RoleRepository;
import com.myApps.DogTrainingApp.dao.UserRepository;
import com.myApps.DogTrainingApp.entities.Role;
import com.myApps.DogTrainingApp.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImplementation userServiceImplementation;

    @Test
    public void saveUser(){
        User theUser = new User();
        theUser.setUsername("carol");
        theUser.setId(3);
        theUser.setPassword("pass123!");
        when(passwordEncoder.encode("pass123!")).thenReturn("encodedPassword");
        userServiceImplementation.saveUser(theUser);
        verify(userRepository, times(1)).save(theUser);
        verify(roleRepository).save(any(Role.class));
        assertEquals("encodedPassword", theUser.getPassword());
    }

    @Test
    public void saveUser_invalidUsername(){
        User theUser = new User();
        theUser.setUsername("carol!");
        assertThrows(IllegalArgumentException.class, () ->userServiceImplementation.saveUser(theUser));
    }

    @Test
    public void saveUser_usernameTooLong(){
        User theUser = new User();
        theUser.setUsername("carolcarolcarolcarolcarolcarolcarolcarolcarolcarol");
        theUser.setPassword("pass123");
        assertThrows(IllegalArgumentException.class, () ->userServiceImplementation.saveUser(theUser));
    }

    @Test
    public void saveUser_invalidPassword(){
        User theUser = new User();
        theUser.setUsername("carol");
        theUser.setPassword("´><:");
        assertThrows(IllegalArgumentException.class, () ->userServiceImplementation.saveUser(theUser));
    }

    @Test
    public void saveUser_passwordTooLong(){
        User theUser = new User();
        theUser.setUsername("carol");
        theUser.setPassword("passwordpasswordpasswordpasswordpasswordpasswordpasswordpassword");
        assertThrows(IllegalArgumentException.class, () ->userServiceImplementation.saveUser(theUser));
    }
}
