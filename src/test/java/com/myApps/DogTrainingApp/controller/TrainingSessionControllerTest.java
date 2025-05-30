package com.myApps.DogTrainingApp.controller;


import com.myApps.DogTrainingApp.controllers.TrainingSessionController;
import com.myApps.DogTrainingApp.entities.TrainingSession;
import com.myApps.DogTrainingApp.service.DogService;
import com.myApps.DogTrainingApp.service.TrainingSessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrainingSessionController.class)
public class TrainingSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingSessionService trainingSessionService;

    @MockBean
    private DogService dogService;

    @Test
    @WithMockUser("carol")
    public void saveTrainingSession() throws Exception{
        TrainingSession theSession = new TrainingSession();
        theSession.setId(1);
        theSession.setNr_trials(5);
        theSession.setNr_successful(3);

        mockMvc.perform(post("/saveTraining")
                        .with(csrf())
                        .param("command", "Sit")
                        .param("dogId", "1")
                        .flashAttr("trainingSession", theSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(trainingSessionService).save(argThat(trainingSession ->
                theSession.getId() ==1
        ));
    }

}
