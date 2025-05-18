package com.myApps.DogTrainingApp.controller;


import com.myApps.DogTrainingApp.service.TrainingSessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TrainingSessionControllerTest.class)
public class TrainingSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingSessionService trainingSessionService;


}
