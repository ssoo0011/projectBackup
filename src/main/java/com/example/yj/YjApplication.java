package com.example.yj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class YjApplication {
    public static void main(String[] args) {


        SpringApplication.run(YjApplication.class, args);
    }
}
