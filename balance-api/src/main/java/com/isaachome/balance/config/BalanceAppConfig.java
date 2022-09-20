package com.isaachome.balance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.isaachome.balance.repo.BaseRepositoryImpl;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = {"com.isaachome.balance.repo"},repositoryBaseClass = BaseRepositoryImpl.class)
public class BalanceAppConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*").allowedMethods("*");
	}
}
