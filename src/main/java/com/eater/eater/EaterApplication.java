package com.eater.eater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.eater.eater")
public class EaterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EaterApplication.class, args);
    }

}
