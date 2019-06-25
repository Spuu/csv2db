package com.nordea.csv2db.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceLoaderConfig {

    @Bean
    @Qualifier("core")
    AnnotationConfigApplicationContext getCoreResourceLoader() {
        return new AnnotationConfigApplicationContext();
    }
}
