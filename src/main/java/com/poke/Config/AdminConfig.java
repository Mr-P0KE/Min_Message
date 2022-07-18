package com.poke.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminConfig implements WebMvcConfigurer {
    @Autowired
    private StringRedisTemplate template;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor(template))
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login/**", "/enroll/**", "/img/**", "/js/**", "/sql", "/favicon.ico");
    }
}
