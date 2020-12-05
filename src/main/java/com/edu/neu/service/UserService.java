package com.edu.neu.service;

import com.edu.neu.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface UserService {
    public User findByUserName(String userName);
    public List<User> findAll();
}
