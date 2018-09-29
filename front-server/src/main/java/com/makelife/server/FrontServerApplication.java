package com.makelife.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(
		basePackages = {
				"com.makelife.api"}
)
public class FrontServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontServerApplication.class, args);
	}
}
