package com.springboot.hello2.domain.dto;

public class MemberDto {
    private String name;
    private String email;
    private String age;

    public MemberDto(String name, String email, String age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }
    @Override
    public String toString(){
        return String.format("%s %s %s", this.name, this.email, this.age);
    }
}
