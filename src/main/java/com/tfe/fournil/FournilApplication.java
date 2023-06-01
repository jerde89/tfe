package com.tfe.fournil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@SpringBootApplication

public class FournilApplication
//public class FournilApplication extends SpringBootServletInitializer
{
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(FournilApplication.class);
//    }
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


