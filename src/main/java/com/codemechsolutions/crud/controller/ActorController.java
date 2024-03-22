package com.codemechsolutions.crud.controller;

import com.codemechsolutions.crud.constant.APIConstant;
import com.codemechsolutions.crud.entity.Actor;
import com.codemechsolutions.crud.exception.ResourceNotFoundException;
import com.codemechsolutions.crud.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
/*@RequestMapping(APIConstant.ACTOR_URL)*/
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/actors")
    public String getAllActors(Model model){
        model.addAttribute("actors",actorService.getAllActors());
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


        /*if(actorService.getActorByUserName(actor.getUserName()))
        {
            model.addAttribute("error","Actor "+actor.getUserName()+" already exists!");
            return "create-actor";
        }*/

        actorService.saveActor(actor);

        return "redirect:/actors";
    }





}
