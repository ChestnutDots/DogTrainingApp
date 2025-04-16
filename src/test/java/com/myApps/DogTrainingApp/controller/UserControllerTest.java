package com.myApps.DogTrainingApp.controller;


import com.myApps.DogTrainingApp.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters=false)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void mainPageLoads() throws Exception{

        mockMvc.perform(get("/main"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andExpect(content().string(containsString("Add Dog Profile")));
    }



}
