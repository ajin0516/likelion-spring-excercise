package com.springboot.hello2.service;

import com.springboot.hello2.Dao.HospitalDao;
import com.springboot.hello2.domain.Hospital;
import com.springboot.hello2.parser.ReadLineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

// Component와 같은 기능
// ComponentScan 할때 Bean으로 등록
@Service
public class HospitalService {

    private final ReadLineContext<Hospital> hospitalReadLineContext;

    private final HospitalDao hospitalDao;

    public HospitalService(ReadLineContext<Hospital> hospitalReadLineContext, HospitalDao hospitalDao) {
        this.hospitalReadLineContext = hospitalReadLineContext;
        this.hospitalDao = hospitalDao;
    }

    @Transactional
    public int insertLargeVolumeHospitalData(String filename){
        List<Hospital> hospitalList;
        try {
            hospitalList = hospitalReadLineContext.readByLine(filename);
            System.out.println("파싱이 끝났습니다.");
            hospitalList.stream()
                    .parallel()
                    .forEach(hospital -> {
                        try {
                            this.hospitalDao.add(hospital);
                        }catch (Exception e){
                            System.out.printf("id:%d 레코드에 문제가 있습니다.\n", hospital.getId());
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!Optional.of(hospitalList).isEmpty()){
            return hospitalList.size();
        }else {
            return 0;
        }
    }
}
