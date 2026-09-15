package com.example.ed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EdApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdApplication.class, args);
    }

}
