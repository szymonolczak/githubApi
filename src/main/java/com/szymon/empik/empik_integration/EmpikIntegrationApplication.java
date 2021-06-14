package com.szymon.empik.empik_integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EmpikIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpikIntegrationApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplateBean() {
		return new RestTemplate();
	}
}
