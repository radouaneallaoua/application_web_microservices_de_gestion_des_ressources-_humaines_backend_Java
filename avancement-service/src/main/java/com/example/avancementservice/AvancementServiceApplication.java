package com.example.avancementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AvancementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvancementServiceApplication.class, args);
    }

}
