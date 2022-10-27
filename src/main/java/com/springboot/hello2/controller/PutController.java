package com.springboot.hello2.controller;

import com.springboot.hello2.domain.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    @RequestMapping(value = "/domain", method = RequestMethod.PUT)
    public String putExample(){
        return "Hello Put API";
    }

    @PutMapping("/member") // 에러..
    public String putMember(@RequestBody Map<String , Objects> putData){
        StringBuilder sb = new StringBuilder(); // Builder Pattern

        putData.entrySet().forEach(mep -> sb.append(mep.getKey()+":"+mep.getValue()+"\n"));
        return sb.toString();
    }

    @PutMapping ("/member2")
    public String postMember(@RequestBody MemberDto memberDto){
        return memberDto.toString();
    }

    @PutMapping ("/member3")
    public ResponseEntity<MemberDto> putMember (@RequestBody MemberDto memberDto){
        return ResponseEntity // 이쁘게 나옴
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}

