package com.tfe.fournil.controller;

import com.tfe.fournil.entity.Feedback;
import com.tfe.fournil.repository.FeedbackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/contact")
public class ContactController {

    //injecte la classe
    @Autowired
    FeedbackRepository feedbackRepository;

    @GetMapping("")
    public String showContact()
    {

        log.info("test Controller");
        //Nom de la JSP
        return "contact";

    }

    //model ajouter attribut pour vue
    //Bindinresult récupère erreurs validation
    @PostMapping(value="/sendFeedback")
    public String saveFeedback(Model model, @Valid Feedback feedback, BindingResult bindingResult, HttpSession session)
    {
        log.info("call send feedback " + feedback);
        session.removeAttribute("errors");
        session.removeAttribute("success");
        if(bindingResult.hasErrors()){
            log.error("call error" );
            List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            log.error(errors.toString());
           // model.addAttribute("errors", errors);
            session.setAttribute("errors", errors);
        }else{
            log.info("save feedback success");
            feedbackRepository.save(feedback);
            //model.addAttribute("success", "Votre feedback a été sauvé");
            session.setAttribute("success", "Votre feedback a été enregistré");

        }
       return "redirect:/contact";

    }

}
