package com.codemechsolutions.crud.controller;

import com.codemechsolutions.crud.entity.Movie;
import com.codemechsolutions.crud.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;
    @GetMapping("/movie")
    public String showMovieForm(Model model)
    {
        model.addAttribute("movie",new Movie());
        return "create-movie";
    }

    @GetMapping("/movies")
    public String MovieTableView(Model model)
    {
        model.addAttribute("movies",movieService.getAllMovies());
        return "movie-list";
    }

    @PostMapping("/movie/save")
    public String saveMovie(@ModelAttribute("movie") Movie movie,Model model)
    {
        if(movieService.getMovieByTitle(movie.getTitle()))
        {
            model.addAttribute("error",movie.getTitle()+" movie already added");
            return "create-movie";
        }
        movieService.saveMovie(movie);
        return "redirect:/movies";
    }
}
