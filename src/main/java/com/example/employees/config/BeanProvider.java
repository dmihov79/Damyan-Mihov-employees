package com.example.employees.config;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanProvider {
    @Bean
    public DateUtils getDateUtils() { return new DateUtils(); }
}
