/*
package com.codemechsolutions.crud.controller;

import com.codemechsolutions.crud.entity.Actor;
import com.codemechsolutions.crud.entity.Movie;
import com.codemechsolutions.crud.exception.ResourceNotFoundException;
import com.codemechsolutions.crud.service.ActorService;
import com.codemechsolutions.crud.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;
    */
/*@GetMapping("/movie")
    public String showMovieForm(Model model)
    {
        model.addAttribute("movie",new Movie());
        return "create-movie";
    }
*//*

    @GetMapping("/movies")
    public String MovieTableView(Model model)
    {
        model.addAttribute("movie",new Movie());
        model.addAttribute("movies",movieService.getAllMovies());
        return "movie-list";
    }

    @PostMapping("/movie/save")
    public String saveMovie(@ModelAttribute("movie") Movie movie,Model model)
    {
        */
/*if(movieService.getMovieByTitle(movie.getTitle()))
        {
            model.addAttribute("error",movie.getTitle()+" movie already added");
            return "create-movie";
        }*//*

        movieService.saveMovie(movie);
        return "redirect:/movies";
    }

   */
/* @GetMapping("/movie/edit/{id}")
    public String editActorById(@PathVariable Long id, Model model) throws ResourceNotFoundException {
        model.addAttribute("movie", movieService.getMovieById(id));
        List<Actor> cactors = actorService.getAllActorsByMovieId(id);
        Set<Actor> actors =  new HashSet<Actor>();
        for(Actor actor:actorService.getAllActors())
        {
            actors.add(actor); // store unique actors
        }
        actors.removeAll(cactors);
        actors.remove(actorService.getAllActorsByMovieId(id)); // unselected actors
        model.addAttribute("uncheckedActors",actors);
        //model.addAttribute("checkedActors",cactors);
        return "editMovie";
    }*//*


    @PostMapping("/movie/edit")
    public String updateUser(@ModelAttribute("movie") @Valid Movie movie,BindingResult result, Model model) {

        if(result.hasErrors()) {
            System.out.println(result.hasErrors());
            model.addAttribute("error", result);
            return "Error";
        }
        movieService.saveMovie(movie); //saving the user.
        return "redirect:/movies";
    }

    @GetMapping("movie/delete/{id}")
    public String deleteActor(@PathVariable Long id) throws ResourceNotFoundException {

        movieService.deleteMovieById(id);
        return "redirect:/movies";
    }
}
*/
