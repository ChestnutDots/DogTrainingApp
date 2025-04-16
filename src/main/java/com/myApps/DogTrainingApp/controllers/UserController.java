package com.myApps.DogTrainingApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/main")
    public String showMainPage(){
        return "main";
    }
}
