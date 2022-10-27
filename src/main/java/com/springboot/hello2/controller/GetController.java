package com.springboot.hello2.controller;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    @RequestMapping("/hello")
    public String gethello(){
        return "Hello";
    }

    @GetMapping("/name")
    public String getName(){
        return "Ajin";
    }

    @GetMapping("/variable/{variable}")
    public String getVariable(@PathVariable String variable){
        return variable;
    }
}
