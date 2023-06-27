package com.common.server.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.math.BigInteger;

@Configuration
public class HouseCorsConfigration implements SchedulingConfigurer {
    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        //允许跨域的域名 如果要携带cookie 则不能写*（*代表所有域名 都可以跨域访问）
        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedOrigin("http://localhost");
        configuration.addAllowedOrigin("http://127.0.0.1");
        configuration.addAllowedOrigin("http://localhost:9001");
        configuration.addAllowedOrigin("http://127.0.0.1:9001");
        configuration.addAllowedOrigin("http://127.0.0.1:9002");
        configuration.addAllowedOrigin("http://localhost:9002");
        configuration.addAllowedOrigin("http://127.0.0.1:10086");
        //是否允许携带cookie
        configuration.setAllowCredentials(true);
        //代表所有请求类型 get post ...
        configuration.addAllowedMethod("*");
        //允许携带任何头信息
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(configurationSource);
    }

    @Bean("jackson2ObjectMapperBuilderCustomizer")
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
                .serializerByType(BigInteger.class, ToStringSerializer.instance)
                .serializerByType(Long.class, ToStringSerializer.instance)
                .serializerByType(Long.TYPE, ToStringSerializer.instance);
    }


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

    }
}
