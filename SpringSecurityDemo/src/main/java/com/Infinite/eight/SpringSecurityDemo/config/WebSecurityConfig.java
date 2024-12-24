package com.Infinite.eight.SpringSecurityDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails vishalUserDetails = User.withUsername("Vishal").
                password("{noop}password") //noop means use plain text as password else we will get exception
                .roles("USER")
                .build();

        UserDetails playerUserDetails = User.withUsername("Player").
                password("password")
                .roles("USER")
                .build();
        var userDetailsList = new ArrayList<>(Arrays.asList(vishalUserDetails,playerUserDetails));

        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(userDetailsList);
        return userDetailsService;
    }
}
