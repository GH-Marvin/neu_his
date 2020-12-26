package com.edu.neu.service.impl;

import com.edu.neu.entity.Registlevel;
import com.edu.neu.mapper.RegistLevelMapper;
import com.edu.neu.service.RegistLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistLevelServiceImpl implements RegistLevelService {
    @Autowired
    private RegistLevelMapper registLevelMapper;
    @Override
    public List<Registlevel> findAll() {
        return registLevelMapper.selectList(null);
    }
}
