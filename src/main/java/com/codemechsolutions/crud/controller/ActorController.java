/*
package com.codemechsolutions.crud.controller;

import com.codemechsolutions.crud.constant.APIConstant;
import com.codemechsolutions.crud.entity.Actor;
import com.codemechsolutions.crud.entity.Movie;
import com.codemechsolutions.crud.exception.ResourceNotFoundException;
import com.codemechsolutions.crud.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller

@RequestMapping(APIConstant.ACTOR_URL)
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/actors")
    public String getAllActors(Model model){
        model.addAttribute("actors",actorService.getAllActors());
        model.addAttribute("actor",new Actor());
        return "actor-list";
    }


@GetMapping("/actor")
    public String getActorForm(Model model)
    {
        model.addAttribute("actor",new Actor());
        return "create-actor";
    }

    @PostMapping("/actor/save")
    public String saveActor(@ModelAttribute("actor") Actor actor, Model model)
    {
        System.out.println(actor);



if(actorService.getActorByUserName(actor.getUserName()))
        {
            model.addAttribute("error","Actor "+actor.getUserName()+" already exists!");
            return "create-actor";
        }

        actorService.saveActor(actor);

        return "redirect:/actors";
    }

    @GetMapping("/actor/edit/{id}")
    public String editActorById(@PathVariable Long id, Model model) throws ResourceNotFoundException{
        model.addAttribute("actor", actorService.getActorById(id));
        return "editActor";
    }

    @PostMapping("/actor/edit")
    public String updateUser(@ModelAttribute("actor") @Valid Actor actor,BindingResult result, Model model) {
        if(result.hasErrors()) {
            System.out.println(result.hasErrors());
            model.addAttribute("error", result);
            return "Error";
        }
        actorService.saveActor(actor); //saving the user.
        return "redirect:/actors";
    }

    @GetMapping("actor/delete/{id}")
    public String deleteActor(@PathVariable Long id) throws ResourceNotFoundException{
        Actor actor = actorService.getActorById(id);

        for (Movie movie : actor.getMovies()) {
            movie.getActors().remove(actor);
        }

        actorService.deleteActorById(id);

        return "redirect:/actors";
    }
}

}
*/
