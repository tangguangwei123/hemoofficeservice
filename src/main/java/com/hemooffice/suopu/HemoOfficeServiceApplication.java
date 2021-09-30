package com.hemooffice.suopu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.hemooffice.suopu.mapper")
@SpringBootApplication
public class HemoOfficeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HemoOfficeServiceApplication.class, args);
    }

}
