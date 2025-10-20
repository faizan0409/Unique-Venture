package com.ozone.application.config;

import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.CacheControl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = { "com.ozone.application.config", "com.ozone.user.component","com.ozone.announcement.component",
		"com.ozone.component.datamodel", "com.ozone.project.component","com.ozone.team.component",
		"com.ozone.notification.component", "com.ozone.timelog.component", "com.ozone.document.component", "com.ozone.company.component","com.ozone.productpage.compoent","com.ozone.roles.compoent","com.ozone.department.compoent","com.ozone.salary.component" })
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.ozone.application.config", "com.ozone.user.component","com.ozone.salary.component",
		"com.ozone.component.datamodel", "com.ozone.project.component","com.ozone.team.component",
		"com.ozone.notification.component" ,"com.ozone.announcement.component", "com.ozone.timelog.component", "com.ozone.document.component", "com.ozone.company.component","com.ozone.productpage.compoent","com.ozone.project.component","com.ozone.roles.compoent","com.ozone.department.compoent" })
@EntityScan(basePackages = { "com.ozone.application.config", "com.ozone.user.component","com.ozone.salary.component",
		"com.ozone.component.datamodel", "com.ozone.project.component","com.ozone.team.component",
		"com.ozone.notification.component" , "com.ozone.timelog.component","com.ozone.announcement.component", "com.ozone.document.component", "com.ozone.company.component","com.ozone.productpage.compoent","com.ozone.roles.compoent","com.ozone.department.compoent" })

@Configuration
@EnableScheduling
public class EmployeeManagement extends SpringBootServletInitializer implements WebMvcConfigurer{

	public static void main(String[] args) throws JAXBException {
		SpringApplication.run(EmployeeManagement.class, args);
		
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EmployeeManagement.class);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

	    // Register resource handler for images
	    registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
	            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	}

}
