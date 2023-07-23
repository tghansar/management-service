package com.example.managementservice;

import com.example.managementservice.controllers.BookRESTController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(BookRESTController.class);
    }
}