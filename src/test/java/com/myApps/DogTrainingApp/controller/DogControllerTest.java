package com.myApps.DogTrainingApp.controller;

import com.myApps.DogTrainingApp.controllers.DogController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(DogController.class)
public class DogControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void addDogProfilePageShouldLoad() throws Exception{
        mockMvc.perform(get("/addDogs"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-dog"));
    }
}
