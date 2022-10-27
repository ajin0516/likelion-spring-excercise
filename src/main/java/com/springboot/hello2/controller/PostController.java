package com.springboot.hello2.controller;

import com.springboot.hello2.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    @RequestMapping(value = "/domain",method = RequestMethod.POST)
    public String postExample(){
        return "Hello Post API";
    }


    // 계속 에러남...
    @PostMapping("/member")
    public String postMember(@RequestBody Map<String , Objects> postData){
        StringBuilder sb =new StringBuilder();

        postData.entrySet().forEach(map -> sb.append(map.getKey() + ":" + map.getValue() + "\n"));
        return sb.toString();
    }

    // 정상 작동..
    @PostMapping("/member2")
    public String postMember2(@RequestBody MemberDto memberDto){
        return memberDto.toString();
    }

}
