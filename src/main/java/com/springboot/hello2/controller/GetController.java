package com.springboot.hello2.controller;

import com.springboot.hello2.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Map;

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

    @GetMapping("/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param){
        param.entrySet().forEach(map ->{
            System.out.printf("key : %s, value : %s\n", map.getKey(), map.getValue());
        });
        return "request2가 호출되었습니다";
    }

    @GetMapping("/request3")
    public String getRequestParam3(MemberDto memberDto){
        return memberDto.toString();
    }
}
