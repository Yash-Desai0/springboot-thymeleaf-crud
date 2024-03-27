package com.codemechsolutions.crud.controller;

import com.codemechsolutions.crud.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @Autowired
    ActorService actorService;
    @GetMapping("")
    public String getDashboard(){
        return "actor-list";
    }

    @GetMapping("/movies")
    public String getMovies(){
        return "movie-list";
    }

}
