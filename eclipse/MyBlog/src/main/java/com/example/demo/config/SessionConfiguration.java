package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.SessionInterceptor;

@Configuration
public class SessionConfiguration implements WebMvcConfigurer{
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/admin/**");
	}
}
