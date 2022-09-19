package com.isaachome.balance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.isaachome.balance.repo.BaseRepositoryImpl;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class BalanceAppConfig {

}
