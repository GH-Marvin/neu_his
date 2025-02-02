package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.neu.entity.Constantitem;
import com.edu.neu.mapper.ConstantItemMapper;
import com.edu.neu.service.ConstantItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConstantItemItemServiceImpl implements ConstantItemService {
    @Resource
    private ConstantItemMapper constantItemMapper;
    @Override
    public List<Constantitem> findByTypeId(Integer id) {
        return constantItemMapper.selectList(new QueryWrapper<Constantitem>().eq("constant_type_id",id));
    }

    @Override
    public String findNameById(Integer id) {
        return constantItemMapper.selectOne(new QueryWrapper<Constantitem>().eq("id",id)).getConstantName();
    }
}
