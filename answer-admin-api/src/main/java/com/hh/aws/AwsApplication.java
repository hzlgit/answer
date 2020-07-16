package com.hh.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan(basePackages = {"com.hh.aws.repository.*"})
public class AwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsApplication.class, args);
    }
}
