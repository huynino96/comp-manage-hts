package com.example.compmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CompManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompManageApplication.class, args);
    }

}
