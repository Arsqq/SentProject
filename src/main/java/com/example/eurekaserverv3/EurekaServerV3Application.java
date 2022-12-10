package com.example.eurekaserverv3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerV3Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerV3Application.class, args);
    }

}
