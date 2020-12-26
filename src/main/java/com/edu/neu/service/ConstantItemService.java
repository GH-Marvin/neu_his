package com.edu.neu.service;

import com.edu.neu.entity.Constantitem;

import java.util.List;

public interface ConstantItemService {
    public List<Constantitem> findByTypeId(Integer id);
}
