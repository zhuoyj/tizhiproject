package com.common.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("在CorsConfig里了");
        registry.addMapping("/**")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
        System.out.println("结束CorsConfig");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\imgs\\";
        registry.addResourceHandler("/imgs/**").addResourceLocations("file:"+path);
    }

}
