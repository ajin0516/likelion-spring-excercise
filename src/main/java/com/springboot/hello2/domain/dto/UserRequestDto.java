package com.springboot.hello2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    private String id;
    private String name;
    private String password;

}
