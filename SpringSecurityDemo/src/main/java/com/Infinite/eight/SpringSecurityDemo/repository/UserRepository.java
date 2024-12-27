package com.Infinite.eight.SpringSecurityDemo.repository;

import com.Infinite.eight.SpringSecurityDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {


    @Query("Select u from User u where u.userName = ?1 and u.password = ?2")
    public User findUserByUserNameAndPassword(String userName, String password);


    @Query("Select u from User u where u.userName = ?1")
    public User findByUserName(String userName);
}
