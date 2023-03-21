package com.example.spring_caching_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;



@SpringBootApplication
public class SpringCachingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCachingProjectApplication.class, args);
    }

}
