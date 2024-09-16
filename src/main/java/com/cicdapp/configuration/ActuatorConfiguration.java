package com.cicdapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cicdapp.component.CustomActuatorEndpoint;
import com.cicdapp.component.UserManagementEndpoint;
import com.cicdapp.service.CICDService;

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
