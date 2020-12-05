package com.edu.neu.service.impl;

import com.edu.neu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    void findByUserName() {
        userService.findByUserName("fwb");
        int i=0;
    }
    @Test
    void findAll() {
        userService.findAll();
        int i=0;
    }
}