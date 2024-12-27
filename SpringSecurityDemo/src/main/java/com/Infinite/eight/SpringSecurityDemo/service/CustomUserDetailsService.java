package com.Infinite.eight.SpringSecurityDemo.service;

import com.Infinite.eight.SpringSecurityDemo.CustomUserDetails;
import com.Infinite.eight.SpringSecurityDemo.entity.User;
import com.Infinite.eight.SpringSecurityDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

        if(Objects.isNull(user)){
            System.out.println("User not avaialbel");
            throw new UsernameNotFoundException("User not avaialbel");
        }

        System.out.println("User is available");
        return new CustomUserDetails(user);
    }
}
