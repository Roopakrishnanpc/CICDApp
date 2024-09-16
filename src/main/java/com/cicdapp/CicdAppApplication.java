package com.cicdapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.cloud.spring.autoconfigure.core.GcpContextAutoConfiguration;

@SpringBootApplication(exclude=GcpContextAutoConfiguration.class)
public class CicdAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CicdAppApplication.class, args);
	}

}
