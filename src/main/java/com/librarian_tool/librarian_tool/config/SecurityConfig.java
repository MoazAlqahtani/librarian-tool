package com.librarian_tool.librarian_tool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * Configuration class for Spring Security to define the following:
 *   - security filter chain.
 *   - authentication provider.
 *   - authentication manager.
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;
    /**
     * Configuring the {@link SecurityFilterChain} for the application.
     *
     * @param http {@link HttpSecurity} object to configure.
     * @return configured {@link SecurityFilterChain}.
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests(request -> request.requestMatchers("/api/v1/auth/login","/api/v1/auth/register")
                        .permitAll()
                        .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    /**
     * Configuring the {@link AuthenticationProvider} using {@link DaoAuthenticationProvider}.
     * Setting the password encoder and user details service for authentication.
     *
     * @return the configured {@link AuthenticationProvider}.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

    /**
     * Configuring the {@link AuthenticationManager} for the application.
     *
     * @param config the {@link AuthenticationConfiguration} to use for authentication management.
     * @return the configured {@link AuthenticationManager}.
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}