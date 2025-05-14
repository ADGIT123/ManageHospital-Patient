package com.ehr.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EhrMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhrMicroserviceApplication.class, args);
    }
}