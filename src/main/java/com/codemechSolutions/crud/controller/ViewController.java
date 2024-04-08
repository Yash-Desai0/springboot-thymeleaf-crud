package com.codemechSolutions.crud.controller;

import com.codemechSolutions.crud.entity.User;
import com.codemechSolutions.crud.util.UtilityMethods;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/")
    public String getDashboard(){
        User user = UtilityMethods.getLoggedInPersonInfo();
        if (user != null) {
            return "actor-list";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping("/register")
    public String showRegistrationPage() {` return "register"; }

    @GetMapping("/login")
    public String showLoginPage() { return "login"; }



    @GetMapping("/movies")
    public String getMovies(){
        User user = UtilityMethods.getLoggedInPersonInfo();
        if (user != null) {
            return "movie-list";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/actors")
    public String getActors(){
        /*User user = UtilityMethods.getLoggedInPersonInfo();
        if (user != null) {*/
            return "actor-list";
        /*} else {
            return "redirect:/login";
        }*/
    }
}