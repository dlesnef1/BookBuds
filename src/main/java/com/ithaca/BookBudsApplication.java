package com.ithaca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Main class that starts the spring application.
 */
@SpringBootApplication
public class BookBudsApplication {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/users/account");
		registrationBean.addUrlPatterns("/users/groups");
		registrationBean.addUrlPatterns("/users/threads");
		registrationBean.addUrlPatterns("/messages");
		registrationBean.addUrlPatterns("/messages/*");
		registrationBean.addUrlPatterns("/groups");
		registrationBean.addUrlPatterns("/groups/*");
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookBudsApplication.class, args);
	}
}
