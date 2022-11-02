package com.springboot.hello2.parser;

import com.springboot.hello2.Dao.HospitalDao;
import com.springboot.hello2.domain.Hospital;
import com.springboot.hello2.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalParserTest {

    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";

    @Autowired
    ReadLineContext<Hospital> hospitalReadLineContext;

    @Autowired
    HospitalDao hospitalDao;

    @Autowired
    HospitalService hospitalService;


    @Test
    @DisplayName("csv 1줄을 hospital로 잘 만드는지")
    void convertToHospital() {
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId());
        assertEquals("의원", hospital.getOpenServiceName());
        assertEquals(3620000, hospital.getOpenLocalGovernmentCode());
        assertEquals("PHMA119993620020041100004", hospital.getManagementNumber());
        assertEquals(LocalDateTime.of(1999, 06, 12, 0, 0, 0), hospital.getLicenseDate());
        assertEquals(1, hospital.getBusinessStatus());
        assertEquals(13, hospital.getBusinessStatusCode());
        assertEquals("062-515-2875", hospital.getPhone());
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress());
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress());
        assertEquals("효치과의원", hospital.getHospitalName());
        assertEquals("치과의원", hospital.getBusinessTypeName());
        assertEquals(1, hospital.getHealthcareProviderCount());
        assertEquals(0, hospital.getPatientRoomCount());
        assertEquals(0, hospital.getTotalNumberOfBeds());
        assertEquals(52.29f, hospital.getTotalAreaSize());
    }

    @Test
    @DisplayName("10만건 이상 데이터가 파싱이 되는지")
    void oneHundredThousandRows() throws IOException {
        // 서버환경에서 build할 때 문제가 생길 수 있습니다.
        // 어디에서든지 실행할 수 있게 짜는 것이 목표.

        hospitalDao.deleteAll();
        String filename = "/Users/ajin/Desktop/fulldata_01_01_02_P_의원 (1).csv";
        int cnt = this.hospitalService.insertLargeVolumeHospitalData(filename);
//        List<Hospital> hospitalList = hospitalReadLineContext.readByLine(filename);
//        assertTrue(hospitalList.size() > 1000);
//        assertTrue(hospitalList.size() > 10000);
        assertTrue(cnt > 1000);
        assertTrue(cnt > 10000);
//        for (int i = 0; i < 10; i++) {
//            System.out.println(hospitalList.get(i).getHospitalName());
//        }
        System.out.printf("파싱된 데이터 개수:%d ",cnt);
    }

    @Test
    @DisplayName("Hospital이 INSERT가 잘 되는지")
    void add(){
        hospitalDao.deleteAll();
        assertEquals(0, hospitalDao.getCount());
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);
        hospitalDao.add(hospital);
        assertEquals(1, hospitalDao.getCount());

        Hospital selectedHospital = hospitalDao.findById(hospital.getId());
        assertEquals(selectedHospital.getId(),hospital.getId());
        assertEquals(selectedHospital.getOpenServiceName(),hospital.getOpenServiceName());
        assertEquals(selectedHospital.getHospitalName(),hospital.getHospitalName());
        assertEquals(selectedHospital.getOpenLocalGovernmentCode(),hospital.getOpenLocalGovernmentCode());
        assertEquals(selectedHospital.getManagementNumber(),hospital.getManagementNumber());
        assertEquals(selectedHospital.getBusinessStatus(),hospital.getBusinessStatus());
        assertEquals(selectedHospital.getBusinessStatusCode(),hospital.getBusinessStatusCode());
        assertEquals(selectedHospital.getPhone(),hospital.getPhone());
        assertEquals(selectedHospital.getFullAddress(),hospital.getFullAddress());
        assertEquals(selectedHospital.getRoadNameAddress(),hospital.getRoadNameAddress());
        assertEquals(selectedHospital.getBusinessTypeName(),hospital.getBusinessTypeName());
        assertEquals(selectedHospital.getHealthcareProviderCount(),hospital.getHealthcareProviderCount());
        assertEquals(selectedHospital.getPatientRoomCount(),hospital.getPatientRoomCount());
        assertEquals(selectedHospital.getTotalNumberOfBeds(),hospital.getTotalNumberOfBeds());
        assertEquals(selectedHospital.getTotalAreaSize(),hospital.getTotalAreaSize());

        // 날짜 , float
        assertEquals(selectedHospital.getLicenseDate(),hospital.getLicenseDate());

        // findById, delete, getCount
    }


    @Test
    @DisplayName("getCount가 잘 되는지")
    void count() {

    }
//    @Test
//    @DisplayName("한줄만 출력")
//    void readByLineTest(String filename) throws IOException {
//        BufferedReader reader
//                = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "euc-kr"));
//        String str = reader.readLine();
//        System.out.println(str);
//    }
}


