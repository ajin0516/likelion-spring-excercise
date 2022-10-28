package com.springboot.hello2.controller;


import com.springboot.hello2.Dao.UserDao;
import com.springboot.hello2.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/user")
    public User addAndGet(){
        userDao.add(new User("2","leeajin","486"));
        return userDao.findById("2");
    }

    @DeleteMapping("/user")
    public void delete(){
        userDao.deleteAll();
    }

    @PostMapping("/user/{id}&{name}&{password}")
    public void add(@PathVariable String id, @PathVariable String name, @PathVariable String password){
        userDao.add(new User(id,name,password));
    }


}
