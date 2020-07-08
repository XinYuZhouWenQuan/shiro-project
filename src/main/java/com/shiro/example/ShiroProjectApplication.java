package com.shiro.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.shiro.example.system.filter"})
public class ShiroProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroProjectApplication.class, args);
    }

}
