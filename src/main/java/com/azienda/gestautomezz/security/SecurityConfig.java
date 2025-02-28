package com.azienda.gestautomezz.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.azienda.gestautomezz.service.UserService;



@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

   
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .logout((logout) -> logout
        		.logoutUrl("/logout")
        		.logoutSuccessUrl("/pages/login?logout")
        		.invalidateHttpSession(true)
        		.deleteCookies("JSESSIONID")
        		.permitAll()
        	)
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers( "/css/**", "/js/**", "/webjars/**", "/img/**","/videos/**").permitAll()
                .requestMatchers("/", "/pages/login").permitAll()
                .requestMatchers("/pages/**","/automezzi/**","/filiali/**","/users/**").hasRole("ADMIN")
                .requestMatchers("/pages/**","/automezzi/**","/filiali/**").hasRole("USER")
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/pages/login")
                .defaultSuccessUrl("/pages/dashboard", true)
                .failureUrl("/pages/login?error=true")
                .permitAll()
            );
            

        return http.build();
    }
    
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}