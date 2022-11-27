package com.tfe.fournil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@SpringBootApplication

public class FournilApplication
{
    public static void main(String[] args)
    {
        log.info("!!!!!!!!!!API IS READY!!!!!!!!!!");
        SpringApplication.run(FournilApplication.class, args);
    }

    @GetMapping("/message")
    public String showMessage()
    {

        return "coucouc";
    }
}


