package com.CICD.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.CICD.component.CustomActuatorEndpoint;
import com.CICD.component.UserManagementEndpoint;
import com.CICD.service.CICDService;

@Configuration
public class ActuatorConfiguration {

    @Bean
    public CustomActuatorEndpoint customActuatorEndpoint() {
        return new CustomActuatorEndpoint();
    }
    @Bean
    public UserManagementEndpoint userManagementEndpoint(CICDService cicdService) {
        return new UserManagementEndpoint(cicdService);
    }
}
