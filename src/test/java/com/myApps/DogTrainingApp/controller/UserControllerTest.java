package com.myApps.DogTrainingApp.controller;


import com.myApps.DogTrainingApp.controllers.UserController;
import com.myApps.DogTrainingApp.service.DogService;
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

    @Test
    public void mainPageLoads() throws Exception{

        when(dogService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/main"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andExpect(content().string(containsString("Add Dog Profile")))
                .andExpect(model().attributeExists("dogs"));

    }



}
