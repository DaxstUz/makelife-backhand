package com.makelife.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MakelifeEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakelifeEurekaApplication.class, args);
    }
}
