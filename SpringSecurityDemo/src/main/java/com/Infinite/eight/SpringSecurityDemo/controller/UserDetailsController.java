package com.Infinite.eight.SpringSecurityDemo.controller;

import com.Infinite.eight.SpringSecurityDemo.entity.User;
import com.Infinite.eight.SpringSecurityDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserDetailsController {

    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetailsController(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Saved User";
    }
//
//    @PostMapping("/login")
//    public String login(@RequestBody User user){
//        var u = userRepository.findUserByUserNameAndPassword(user.getUserName(),user.getPassword());
//
//        if(Objects.isNull(u)) {
//            return "failure invalid credentials";
//        }
//
//        return "Login Success";
//    }
}
