package com.project;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.project.jwtfilter.AuthFilter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
public class FoodApplication {
	
	@Bean
	public FilterRegistrationBean<Filter> jwtFilter() {
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new AuthFilter());
		bean.addUrlPatterns("/api/v1/*");
		return bean;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FoodApplication.class, args);
	}


}
