package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.neu.entity.Register;
import com.edu.neu.mapper.RegisterMapper;
import com.edu.neu.service.ConstantItemService;
import com.edu.neu.service.RegisterService;
import com.edu.neu.util.KeyUtil;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.RegisterVO;
import com.edu.neu.vo.WaitingItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private ConstantItemService constantItemService;
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
        registerMapper.insert(register);
    }

    @Override
    public DataVO<WaitingItemVO> findRegistrationsByDoctorId(Integer doctor_id,Integer page,Integer limit) {
        QueryWrapper<Register> morning_queryWrapper = new QueryWrapper<>();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Register> itemsIPage = new Page<>(page, limit);
        IPage<Register> result = registerMapper.selectPage(itemsIPage, morning_queryWrapper.eq("user_id",doctor_id).eq("visit_date",today).eq("visit_state","1"));
        List<Register> list = result.getRecords();
        List<WaitingItemVO> waitingItemVOList = list.stream().map(e -> new WaitingItemVO(
                e.getId(),
                e.getCaseNumber(),
                e.getRealName(),
                constantItemService.findNameById(e.getGender()),
                e.getAge()
        )).collect(Collectors.toList());
        dataVO.setCount(result.getTotal());
        dataVO.setData(waitingItemVOList);
        return dataVO;

    }
}
