package com.pulkit.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/users/registers").permitAll()
						.requestMatchers("/api/users/**").hasRole("ADMIN")
						.requestMatchers("/h2-console/**").permitAll()
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()).headers(headers -> headers
	                    .frameOptions(frameOptions->frameOptions.disable()).disable());
		return http.build();

	}
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails adminUser=User.builder()
				.username("admin")
				.password(passwordEncoder().encode("password"))
				.roles("ADMIN")
				.build();
		UserDetails regularUser=User.builder()
				.username("user")
				.password(passwordEncoder().encode("password"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(adminUser,regularUser);
	}
}
	
	
