package com.example.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // JWT = stateless
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            // CORS
            .cors()

            // CSRF disabled for APIs
            .and()
            .csrf().disable()

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
            	    // allow preflight
            	    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

            	    // allow ADMIN APIs (offline admin module)
            	    .requestMatchers("/api/admin/**").permitAll()

            	    // allow user auth APIs (if still present here)
            	    .requestMatchers(HttpMethod.POST,
            	        "/api/v1/customer/login",
            	        "/api/v1/customer/register"
            	    ).permitAll()

            	    // everything else secured
            	    .anyRequest().authenticated()
            	);

        return http.build();
    }

    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}

