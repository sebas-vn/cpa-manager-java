package com.skillstorm.taxr_manager;

import java.util.Arrays;
import java.util.LinkedList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@SpringBootApplication
public class TaxrManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxrManagerApplication.class, args);
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		// starting off the process
		http.httpBasic(Customizer.withDefaults());
		
		http.csrf().disable();
				
		// bypassing CORS
		http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(request -> {
	           CorsConfiguration cc = new CorsConfiguration().applyPermitDefaultValues();
	           cc.setAllowedMethods(new LinkedList<>(Arrays.asList("GET", "POST", "PUT", "DELETE")));
	           return cc;
			})
	    );
		
		http.authorizeHttpRequests(requests -> {
			
			// saying whether or not requests of certain methods/endpoints are allowed or denied
			// once a request matches one of these, top-down, the rest are ignored!
			requests.requestMatchers(HttpMethod.GET, "/**").permitAll();
			requests.anyRequest().permitAll();
			
		});
		
		return http.build();
		
	}

}
