package com.edu.neu.service;

import com.edu.neu.entity.Department;
import com.edu.neu.vo.ConstantVO;

import java.util.List;

public interface DepartmentService {
    public List<Department> findAll();
}
