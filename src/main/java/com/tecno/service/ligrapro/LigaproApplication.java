package com.tecno.service.ligrapro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.tecno.service.ligrapro.security.JWTAuthorizationFilter;



@SpringBootApplication
public class LigaproApplication {

	public static void main(String[] args) {
		SpringApplication.run(LigaproApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		/*
		 .antMatchers(HttpMethod.GET, "/cardService/v1/**").permitAll()
					.antMatchers(HttpMethod.POST, "/cardService/v1/**").permitAll()
					.antMatchers(HttpMethod.PUT, "/cardService/v1/**").permitAll()
					.antMatchers(HttpMethod.DELETE, "/cardService/v1/**").permitAll()
		 */
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.anyRequest().permitAll();
			http.cors();
		}

		@Bean
		public CorsFilter corsFilter() {
			final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			final CorsConfiguration config = new CorsConfiguration();
			config.setAllowCredentials(false);
			config.addAllowedOrigin("*"); // this allows all origin
			config.addAllowedHeader("*"); // this allows all headers
			config.addAllowedMethod("OPTIONS");
			config.addAllowedMethod("HEAD");
			config.addAllowedMethod("GET");
			config.addAllowedMethod("PUT");
			config.addAllowedMethod("POST");
			config.addAllowedMethod("DELETE");
			config.addAllowedMethod("PATCH");
			source.registerCorsConfiguration("/**", config);
			return new CorsFilter(source);
		}
	}

}
