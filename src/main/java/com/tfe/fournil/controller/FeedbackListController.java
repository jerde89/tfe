package com.tfe.fournil.controller;

import com.google.gson.Gson;
import com.tfe.fournil.entity.Feedback;
import com.tfe.fournil.repository.FeedbackRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Slf4j
@Controller
@RequestMapping(value ="/feedbackList")
public class FeedbackListController
{

    @Autowired
    FeedbackRepository feedbackRepository;

    @GetMapping("")
    public String showFeedbackList(Model model)
    {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        model.addAttribute("feedbacks", feedbacks);
        //converti list javascript en json
        model.addAttribute("feedbacksJson", new Gson().toJson(feedbacks));
        log.info("feedbacklist log" + feedbacks.toString());

        return "feedbackList";
    }

    @GetMapping("/unread")
    public ResponseEntity<List<Feedback>> ajaxShowFeedbackListUnRead()
    {
        List<Feedback> feedbacks = feedbackRepository.findAll().stream()
                .filter(Predicate.not(Feedback::getStatutFeedback))
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/read")
    public ResponseEntity<List<Feedback>> ajaxShowFeedbackListRead()
    {
        List<Feedback> feedbacks = feedbackRepository.findAll().stream()
                .filter(Feedback::getStatutFeedback)
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbacks);
    }



    @PostMapping(value = "/statutlu")
    public String isRead(@RequestParam Long feedbackId)
    {
        Optional<Feedback> feed = feedbackRepository.findById(feedbackId);
        log.info("je recois bien " +feed.toString());
        Feedback feedback = null;
        if (feed.isPresent())
        {

            //je mets dans l'objet feedback le résultat da le requête da
            feedback = feed.get();
            feedback.setStatutFeedback(true);
            feedback.setUpdateAtFeedback(new Date(System.currentTimeMillis()));
            feedbackRepository.save(feedback);
        }
        return "redirect:/feedbackList";
    }

    @PostMapping(value = "/statutNonlu")
    public String isNotRead(@RequestParam Long feedbackId)
    {
        Optional<Feedback> feed = feedbackRepository.findById(feedbackId);
        log.info("je recois bien " +feed.toString());
        Feedback feedback = null;
        if (feed.isPresent())
        {

            //je mets dans l'objet feedback le résultat da le requête da
            feedback = feed.get();
            feedback.setStatutFeedback(false);
//            feedback.setUpdateAtFeedback(new Date(System.currentTimeMillis()));
            feedback.setUpdateAtFeedback(null);
            feedbackRepository.save(feedback);
        }
        return "redirect:/feedbackList";
    }

    @PostMapping(value = "/delete")
    public ResponseEntity deleteFeedback(@RequestParam Long feedbackId, HttpSession session) {

        Optional<Feedback> feed = feedbackRepository.findById(feedbackId);
        Feedback feedback = null;
        if (feed.isPresent()) {
            feedback = feed.get();
            feedbackRepository.delete(feedback);
        }
            else {
                log.error("feed is null");
                }
            return ResponseEntity.ok(feedbackId);

    }
}
