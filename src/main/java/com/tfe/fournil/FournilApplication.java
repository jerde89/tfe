package com.tfe.fournil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Slf4j
//@SpringBootApplication
@Configuration
@ComponentScan("com.tfe.fournil")
@EnableAutoConfiguration
public class FournilApplication {


    public static void main(String[] args) {
        log.info("!!!!!!!!!!API IS READY!!!!!!!!!!");
        SpringApplication.run(FournilApplication.class, args);
              }

    }


