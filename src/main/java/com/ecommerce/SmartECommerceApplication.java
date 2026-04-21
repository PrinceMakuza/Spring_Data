package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableAutoConfiguration
@EnableCaching
public class SmartECommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartECommerceApplication.class, args);
    }
}
