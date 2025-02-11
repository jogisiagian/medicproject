package com.mini.medicproject.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UserPorfilConfig implements WebMvcConfigurer {

	String mypath = "file:///D:\\Foto\\javafile\\test\\coba";
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations(mypath);
	}
}
