package com.isaachome.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.csrf((c)->c.disable())
                .authorizeHttpRequests(
                        (authorize)->{
                            authorize
                                    .requestMatchers(HttpMethod.GET,"/api/**")
                                    .permitAll()
                    .requestMatchers("/api/auth/**").permitAll()
                    .anyRequest()
                    .authenticated();
        }).build();
    }
}
