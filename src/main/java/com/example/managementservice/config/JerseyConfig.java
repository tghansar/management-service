package com.example.managementservice.config;

import com.example.managementservice.controller.BookController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(BookController.class);
    }
}