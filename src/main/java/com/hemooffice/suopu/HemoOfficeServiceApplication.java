package com.hemooffice.suopu;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HemoOfficeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HemoOfficeServiceApplication.class, args);
    }

}
