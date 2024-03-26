package com.gilson.pedidoservice.configTest;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.gilson.pedidoservice")
public class AppConfig {
    @Bean
    public JavaTimeModule javaTimeModule() {
        return new JavaTimeModule();
    }
}
