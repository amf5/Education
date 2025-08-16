package com.com.Courses.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.com.Courses.filter.RequestFilter;
import com.com.Courses.serviceImpl.CustomerUserDetailsServiceImpl;

@Configuration
public class SecurityConfig {
	
    private final CustomerUserDetailsServiceImpl customUserDetailsService;

    
    private final RequestFilter requestFilter;
    
    
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
    
    
    public SecurityConfig(CustomerUserDetailsServiceImpl customUserDetailsService,
    		RequestFilter requestFilter	,JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
    		CustomOAuth2SuccessHandler customOAuth2SuccessHandler) {
    	
    	this.customUserDetailsService=customUserDetailsService;
    	this.jwtAuthenticationEntryPoint=jwtAuthenticationEntryPoint;
    	this.requestFilter=requestFilter;
    	this.customOAuth2SuccessHandler=customOAuth2SuccessHandler;
    	
    	
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

  /*  @Bean
    public OAuth2AuthorizedClientService authorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }
*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .securityMatcher("/**") 
        .cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(List.of("*")); 
            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            config.setAllowedHeaders(List.of("*"));
            config.setAllowCredentials(false);
            return config;
        }))
       
        .csrf(csrf -> csrf.disable()) 
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth
            	
                .requestMatchers("/education/login",
                		 "/education/signup",
                		"/education/resentcode","/education/activate","/education/forgot","/user/check","/user/code","reset-password.html").permitAll()
             //   .requestMatchers("/user/**").hasRole("USER") 
               
                .anyRequest().authenticated()
            )
            .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
            .oauth2Login(oauth2 -> oauth2
                    .successHandler(customOAuth2SuccessHandler)
                    .failureUrl("/login-failed.html"));

        http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
   /* @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5500")); 
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); 
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type")); 
        configuration.setAllowCredentials(true); 

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
       

        return source;
    }*/
   /* @Bean
    public RequestFilter requestFilter() {
        return new RequestFilter();
    }*/

}
