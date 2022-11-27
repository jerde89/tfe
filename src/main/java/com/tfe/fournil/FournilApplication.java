package com.tfe.fournil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication

public class FournilApplication {


    public static void main(String[] args) {
        log.info("!!!!!!!!!!API IS READY!!!!!!!!!!");
        SpringApplication.run(FournilApplication.class, args);
              }

    }


