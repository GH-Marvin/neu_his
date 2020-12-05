package com.edu.neu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.neu.entity.User;
import com.edu.neu.mapper.UserMapper;
import com.edu.neu.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String name) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", name);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

}
