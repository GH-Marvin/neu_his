package com.edu.neu.service.impl;

import com.edu.neu.entity.Department;
import com.edu.neu.mapper.DepartmentMapper;
import com.edu.neu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> findAll() {
        return departmentMapper.selectList(null);
    }
}
