package com.example.managementservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ManagementServiceApplication extends SpringBootServletInitializer {

    public static void main(String[] args)
    {
        new ManagementServiceApplication().configure(
                new SpringApplicationBuilder(ManagementServiceApplication.class)).run(args);
    }
}
