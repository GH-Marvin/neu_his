package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.neu.entity.Register;
import com.edu.neu.mapper.RegisterMapper;
import com.edu.neu.service.RegisterService;
import com.edu.neu.util.KeyUtil;
import com.edu.neu.vo.RegisterVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterMapper registerMapper;
    @Override
    public String initCaseNumber() {
        return KeyUtil.createUniqueKey();
    }

    @Override
    public RegisterVO findVOById(String caseNumber) {
        RegisterVO registerVO = new RegisterVO();
        BeanUtils.copyProperties(findById(caseNumber),registerVO);
        return registerVO;
    }

    @Override
    public Register findById(String caseNumber) {
        return registerMapper.selectOne(new QueryWrapper<Register>().eq("case_number",caseNumber));
    }

    @Override
    public void createRegister(Register register) {

    }
}
