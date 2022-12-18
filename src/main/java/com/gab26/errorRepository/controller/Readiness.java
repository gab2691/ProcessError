package com.gab26.errorRepository.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Readiness {

    @GetMapping({"/health"})
    public String liveness(){
        return "ok";
    }
}
