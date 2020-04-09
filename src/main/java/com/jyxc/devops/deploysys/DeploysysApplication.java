package com.jyxc.devops.deploysys;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SuppressWarnings("SpringBootApplicationSetup")
@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class DeploysysApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeploysysApplication.class, args);
    }

}
