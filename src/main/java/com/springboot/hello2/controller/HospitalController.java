package com.springboot.hello2.controller;


import com.springboot.hello2.Dao.HospitalDao;
import com.springboot.hello2.domain.Hospital;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api/v1")
public class HospitalController {

    private final HospitalDao hospitalDao;

    public HospitalController(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    // "/hospital/{id}" 안 넣어줘서 에러났음
    @GetMapping("/hospital/{id}")
    public ResponseEntity<Hospital> get(@PathVariable Integer id){
        Hospital hospital = hospitalDao.findById(id);
        Optional<Hospital> opt = Optional.of(hospital);

        if(!opt.isEmpty()){
            return ResponseEntity.ok().body(hospital);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Hospital());
        }
    }

}
