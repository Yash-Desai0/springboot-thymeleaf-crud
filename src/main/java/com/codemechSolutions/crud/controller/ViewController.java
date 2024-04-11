package com.codemechSolutions.crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; 

@Controller
public class ViewController {

    @GetMapping({"/","/index"})
    public String showIndexPage() { return "index"; }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/actors")
    public String showActorListPage() {
            return "actor-list";
    }

    @GetMapping("/movies")                                                                                      // Controller method to render movie-list page after login
    public String showMovieListPage() {                                                                                      // Check if JWT token exists in the cookie
            return "movie-list";                                                                                // Return movie-list.html from templates directory
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok().build();
    }


}