package com.kabz.scheeels.controllers;

import com.kabz.scheeels.infrastructure.RestApiController;
import org.springframework.web.bind.annotation.GetMapping;

@RestApiController("/hello")
public class HelloController {

    @GetMapping
    public String hello(){
        return "Hello from Scheeels!";
    }
}
