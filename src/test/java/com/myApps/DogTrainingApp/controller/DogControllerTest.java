package com.myApps.DogTrainingApp.controller;

import com.myApps.DogTrainingApp.controllers.DogController;
import com.myApps.DogTrainingApp.entities.Dog;
import com.myApps.DogTrainingApp.service.DogService;
import com.myApps.DogTrainingApp.service.UserService;
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

@WebMvcTest(DogController.class)
public class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DogService dogService;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser("carol")
    void addDogProfilePageShouldLoad() throws Exception{
        mockMvc.perform(get("/addDogs"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-dog"))
                .andExpect(model().attributeExists("dog"));
    }

    @Test
    @WithMockUser("carol")
    public void testSaveNewDog_RedirectsToMain() throws Exception{

        Dog theDog = new Dog();
        theDog.setId(0);
        theDog.setName("Bean");
        theDog.setBreed("Pug");
        theDog.setAge(2);

        mockMvc.perform(post("/save")
                        .with(csrf())
                        .flashAttr("dog", theDog))
                        .andExpect(status().is3xxRedirection())
                        .andExpect(redirectedUrl("/"));

        verify(dogService).save(argThat(dog ->
                theDog.getId() ==0
        ));
    }
}
