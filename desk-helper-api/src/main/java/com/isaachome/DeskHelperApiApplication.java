package com.isaachome;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.isaachome.api.entity.User;
import com.isaachome.api.enums.ProfileEnum;
import com.isaachome.api.repos.UserRepo;

@SpringBootApplication
public class DeskHelperApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeskHelperApiApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Bean
	CommandLineRunner init(UserRepo userRepository,PasswordEncoder passwordEncoder) {
		return args ->{
			initUsers(userRepository, passwordEncoder);
		};
	}
	
	private void initUsers(UserRepo userRepository, PasswordEncoder passwordEncoder) {
		User admin = new User();
		admin.setEmail("admin@admin.com");
		admin.setPassword(passwordEncoder.encode("123456"));
		admin.setProfile(ProfileEnum.ROLE_ADMIN);
		
		User find = userRepository.findByEmail(admin.getEmail());
		if(find==null) {
			userRepository.save(admin);
		}
	}
}
