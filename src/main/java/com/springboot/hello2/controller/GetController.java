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
    // URL 경로에 값을 담아 보내기
    @GetMapping("/variable/{variable}")
    public String getVariable(@PathVariable String variable){
        return variable;
    }
    // 쿼리 형식으로 값 전당
    @GetMapping("/request")
    public String getRequestParam(@RequestParam String name, @RequestParam String email, @RequestParam String age){
        return name + " " + email + " " + age;
    }
}
