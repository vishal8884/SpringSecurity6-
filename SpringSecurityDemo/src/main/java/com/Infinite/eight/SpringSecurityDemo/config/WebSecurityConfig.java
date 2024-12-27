package com.Infinite.eight.SpringSecurityDemo.config;

import com.Infinite.eight.SpringSecurityDemo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("register", "login").permitAll()
                        .anyRequest()
                        .authenticated())
                //.formLogin(Customizer.withDefaults()) //gives default spring login page
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    //@Bean
    public UserDetailsService userDetailsService() {
        UserDetails vishalUserDetails = User.withUsername("Vishal").
                password("{noop}password") //noop means use plain text as password else we will get exception
                .roles("USER")
                .build();

        UserDetails playerUserDetails = User.withUsername("Player").
                password("{noop}password")
                .roles("USER")
                .build();
        List<UserDetails> userDetailsList = new ArrayList<>(Arrays.asList(vishalUserDetails, playerUserDetails));

        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(userDetailsList);
        return userDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setPasswordEncoder(bCryptPasswordEncoder());

        return provider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(14);
    }

    /**
     * This is for basic authentication with default UserDetailsService implementation
     */
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails vishalUserDetails = User.withUsername("Vishal").
//                password("{noop}password") //noop means use plain text as password else we will get exception
//                .roles("USER")
//                .build();
//
//        UserDetails playerUserDetails = User.withUsername("Player").
//                password("{noop}password")
//                .roles("USER")
//                .build();
//        var userDetailsList = new ArrayList<>(Arrays.asList(vishalUserDetails,playerUserDetails));
//
//        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(userDetailsList);
//        return userDetailsService;
//    }
}