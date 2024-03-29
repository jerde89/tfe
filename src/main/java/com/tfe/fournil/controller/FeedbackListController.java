package com.tfe.fournil.controller;

import com.google.gson.Gson;
import com.tfe.fournil.entity.Feedback;
import com.tfe.fournil.repository.FeedbackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The type Feedback list controller.
 */
@Slf4j
@Controller
@RequestMapping(value = "/feedbackList")
public class FeedbackListController {

    /**
     * The Feedback repository.
     */
    @Autowired
    FeedbackRepository feedbackRepository;

    /**
     * Show feedback list string.
     *
     * @return the string
     */
    @GetMapping("")
    public String showFeedbackList() {
//        List<Feedback> feedbacks = feedbackRepository.findAll();
//        model.addAttribute("feedbacks", feedbacks);
//        //converti list javascript en json
//        model.addAttribute("feedbacksJson", new Gson().toJson(feedbacks));
//        log.info("feedbacklist log" + feedbacks.toString());

        return "feedbackList";
    }

    /**
     * Ajax show feedback list un read response entity.
     *
     * @return the response entity
     */
    @GetMapping("/unread")
    public ResponseEntity<List<Feedback>> ajaxShowFeedbackListUnRead() {
        List<Feedback> feedbacks = feedbackRepository.findAll().stream()
                .filter(Predicate.not(Feedback::getStatutFeedback))
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbacks);
    }

    /**
     * Ajax show feedback list read response entity.
     *
     * @return the response entity
     */
    @GetMapping("/read")
    public ResponseEntity<List<Feedback>> ajaxShowFeedbackListRead() {
        List<Feedback> feedbacks = feedbackRepository.findAll().stream()
                .filter(Feedback::getStatutFeedback)
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbacks);
    }


    /**
     * Is read string.
     *
     * @param feedbackId the feedback id
     * @return the string
     */
    @PostMapping(value = "/statutlu")
    public String isRead(@RequestParam Long feedbackId) {
        Optional<Feedback> feed = feedbackRepository.findById(feedbackId);
        log.info("je recois bien " + feed.toString());
        Feedback feedback = null;
        if (feed.isPresent()) {

            //je mets dans l'objet feedback le résultat da le requête da
            feedback = feed.get();
            feedback.setStatutFeedback(true);
            feedback.setUpdateAtFeedback(new Date(System.currentTimeMillis()));
            feedbackRepository.save(feedback);
        }
        return "redirect:/feedbackList";
    }

    /**
     * Is not read string.
     *
     * @param feedbackId the feedback id
     * @return the string
     */
    @PostMapping(value = "/statutNonlu")
    public String isNotRead(@RequestParam Long feedbackId) {
        Optional<Feedback> feed = feedbackRepository.findById(feedbackId);
        log.info("je recois bien " + feed.toString());
        Feedback feedback = null;
        if (feed.isPresent()) {

            //je mets dans l'objet feedback le résultat da le requête da
            feedback = feed.get();
            feedback.setStatutFeedback(false);
//            feedback.setUpdateAtFeedback(new Date(System.currentTimeMillis()));
            feedback.setUpdateAtFeedback(null);
            feedbackRepository.save(feedback);
        }
        return "redirect:/feedbackList";
    }

    /**
     * Delete feedback response entity.
     *
     * @param feedbackId the feedback id
     * @return the response entity
     */
    @PostMapping(value = "/delete")
    public ResponseEntity deleteFeedback(@RequestParam Long feedbackId) {

        Optional<Feedback> feed = feedbackRepository.findById(feedbackId);
        if (feed.isEmpty()) {
            return ResponseEntity.badRequest().body("FeedBack not found for id: " + feedbackId);
        }
        feedbackRepository.delete(feed.get());
        return ResponseEntity.ok(feedbackId);
    }
}

