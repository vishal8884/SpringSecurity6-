package com.Infinite.eight.SpringSecurityDemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.stream.Collectors;

@RestController
public class StudentController {


    private record Student(String name,Integer age) {
    }

    List<Student> students = new ArrayList<>(List.of(new Student("Vishal",26),new Student("Learner",28),new Student("Water",18)));


    @GetMapping("/")
    public String defaultEndpoint(){
        return "called default endpoint";
    }

    @GetMapping("/students")
    public String getStudents() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String studentsString = objectMapper.writeValueAsString(students);

        return studentsString;
    }

    @PostMapping("/students")
    public Student saveStudents(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest){
        CsrfToken csrfToken = (CsrfToken) httpServletRequest.getAttribute("_csrf");

        return csrfToken;
    }
}
