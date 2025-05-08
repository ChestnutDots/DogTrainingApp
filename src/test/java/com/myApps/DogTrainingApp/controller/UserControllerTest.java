package com.myApps.DogTrainingApp.controller;


import com.myApps.DogTrainingApp.controllers.UserController;
import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.entities.User;
import com.myApps.DogTrainingApp.service.DogService;
import com.myApps.DogTrainingApp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters=false)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DogService dogService;

    @MockBean
    UserService userService;

    @Test
    public void mainPageLoads() throws Exception{

        when(dogService.findAllByUser(new User())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andExpect(content().string(containsString("Add Dog Profile")))
                .andExpect(model().attributeExists("dogs"));

    }

    @Test
    public void addUserSuccessfully() throws Exception{

        mockMvc.perform(get("/addUser"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-registration-form"));
    }

    @Test
    public void openDogProfile() throws Exception{

        Dog mockDog = new Dog("Fido", new User(), 3);
        when(dogService.findById(1)).thenReturn(mockDog);
        mockMvc.perform(get("/showDogProfile")
                .param("dogId","1"))
                .andExpect(status().isOk())
                .andExpect(view().name("dog-profile"))
                .andExpect(model().attributeExists("dog"))
                .andExpect(content().string(containsString("New Training Session")));
    }



}
