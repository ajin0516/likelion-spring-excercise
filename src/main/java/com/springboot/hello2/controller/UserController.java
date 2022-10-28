package com.springboot.hello2.controller;


import com.springboot.hello2.Dao.UserDao;
import com.springboot.hello2.domain.User;
import com.springboot.hello2.domain.dto.UserRequestDto;
import io.swagger.models.auth.In;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/user")
    public User addAndGet() {
        userDao.add(new User("2", "leeajin", "486"));
        return userDao.findById("2");
    }

//    @DeleteMapping("/user")
//    public void delete(){
//        userDao.deleteAll();
//    }
    // Dto 적용
    @DeleteMapping("/user")
    public ResponseEntity<Integer> deleteAll() {
        return ResponseEntity
                .ok()
                .body(userDao.deleteAll());
    }


//    @PostMapping("/user/{id}&{name}&{password}")
//    public void add(@PathVariable String id, @PathVariable String name, @PathVariable String password) {
//        userDao.add(new User(id, name, password));
//    }

    // <Integer> 타입으로 바뀌면서 UserDao의 add를 void에서 int로 바꿔주니 에러 해결
    @PostMapping("/user")
    public ResponseEntity<Integer> add(@RequestBody UserRequestDto userRequestDto){
        User user = new User(userRequestDto.getId(), userRequestDto.getName(), userRequestDto.getPassword());
        return ResponseEntity
                .ok()
                .body(userDao.add(user));
    }

}
