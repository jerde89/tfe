package com.tfe.fournil.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    /**     * Send email.     *
     * * @param toEmail the to email
     * * @param subject the subject
     * * @param body    the body     */
    public void sendEmail(String toEmail, String subject, String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setFrom("fournil@gmail.com");
        // to change with client
        message.setText(body + " " + new Date(System.currentTimeMillis()));
        message.setTo(toEmail);
        mailSender.send(message);
        log.info("Mail sent successfully to {}", toEmail);
    }
}
