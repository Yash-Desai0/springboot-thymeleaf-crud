package com.codemechSolutions.crud.controller;

import com.codemechSolutions.crud.constant.JwtConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {

    @GetMapping({"/","/index"})
    public String showIndexPage() { return "index"; }

    @GetMapping("/register")
    public String showRegistrationPage() { return "register"; }

    @GetMapping("/login")
    public String showLoginPage() { return "login"; }

    @PostMapping("/login")
    public String afterLogin(@CookieValue(name = JwtConstant.JWT_HEADER, required = false) String jwtToken){
        if (jwtToken != null) {
            return "redirect:/actors";
        }else{
            return "redirect:/login";
        }
    }
    @GetMapping("/actors")                                                                                      // Controller method to render actor-list page after login
    public String showActorListPage(@CookieValue(name = JwtConstant.JWT_HEADER, required = false) String jwtToken) {
        if (jwtToken != null) {
            return "actor-list";
        } else {
            return "redirect:/login";                                                                           // Redirect to login page if token is not valid or missing
        }
    }

    @GetMapping("/movies")                                                                                      // Controller method to render movie-list page after login
    public String showMovieListPage(@CookieValue(name = JwtConstant.JWT_HEADER, required = false) String jwtToken) {
        if (jwtToken != null) {                                                                                 // Check if JWT token exists in the cookie
            return "movie-list";                                                                                // Return movie-list.html from templates directory
        } else {
            return "redirect:/login";                                                                           // Redirect to login page if token is not valid or missing
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok().build();
    }
}