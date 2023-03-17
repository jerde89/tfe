package com.tfe.fournil.controller;

import com.tfe.fournil.entity.User;
import com.tfe.fournil.repository.UserRepository;
import com.tfe.fournil.service.GeneratePassword;
import com.tfe.fournil.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/sendEmailPassword")
@Slf4j
public class SendEmailPasswordController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    @PostMapping (value ="")
    public String sendEmailPassword (String email){
        User user = userRepository.findByUsername(email);
        if (user != null){
            //création d'une variable newpassword qui va chercher le résultat du service GeneratePassword
            String newPassword = GeneratePassword.generatePassword(10);
          mailService.sendEmail(user.getEmail(), "mot de passe oublié", "votre mot de passe est " + newPassword);
          user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
          userRepository.save(user);
        }
        return "myPersonalData";
    }
}
