package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
@RestController
@PropertySource("classpath:dbconfig.properties")
public class UserApplication {
	@RequestMapping(value = "/")
	public String home() {
		return "Eureka Client application";
	}

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
}
