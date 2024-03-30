package com.codemechSolutions.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    /*Temporary Dashboard*/
    @GetMapping("")
    public String getDashboard(){
        return "actor-list";
    }


    /*Movie Page*/
    @GetMapping("/movies")
    public String getMovies(){
        return "movie-list";
    }

}
