package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.neu.entity.Department;
import com.edu.neu.mapper.DepartmentMapper;
import com.edu.neu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> findAll() {
        return departmentMapper.selectList(null);
    }

    @Override
    public Department findById(Integer id) {
        return departmentMapper.selectOne(new QueryWrapper<Department>().eq("id",id));
    }
}
