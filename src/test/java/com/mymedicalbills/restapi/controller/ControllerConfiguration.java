package com.mymedicalbills.restapi.controller;

import org.springframework.boot.autoconfigure.data.rest.RepositoryRestProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfiguration {
    @Bean
    public RepositoryRestProperties repositoryRestProperties() {
        var repositoryRestProperties = new RepositoryRestProperties();
        repositoryRestProperties.setReturnBodyOnCreate(true);
        repositoryRestProperties.setReturnBodyOnUpdate(true);
        return repositoryRestProperties;
    }
}
