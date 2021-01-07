package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.neu.entity.Drugs;
import com.edu.neu.entity.Drugstemplate;
import com.edu.neu.mapper.DrugsTemplateMapper;
import com.edu.neu.service.ConstantItemService;
import com.edu.neu.service.DrugsTemplateService;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.DrugsTemplateVO;
import com.edu.neu.vo.DrugsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugsTemplateServiceImpl implements DrugsTemplateService {
    @Resource
    private DrugsTemplateMapper drugsTemplateMapper;
    @Autowired
    private ConstantItemService constantItemService;
    @Override
    public DataVO<DrugsTemplateVO> findAll(Integer page, Integer limit) {
        DataVO<DrugsTemplateVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Drugstemplate> itemsIPage = new Page<>(page, limit);
        IPage<Drugstemplate> result = drugsTemplateMapper.selectPage(itemsIPage, null);
        List<Drugstemplate> list = result.getRecords();
        List<DrugsTemplateVO> drugsTemplateVOList = list.stream().map(e->new DrugsTemplateVO(
                e.getTempId(),
                e.getName(),
                constantItemService.findNameById(e.getScopeId())
        )).collect(Collectors.toList());
        dataVO.setCount(result.getTotal());
        dataVO.setData(drugsTemplateVOList);
        return dataVO;
    }
    @Override
    public DataVO<DrugsTemplateVO> findAllByName(String name, Integer page, Integer limit) {
        DataVO<DrugsTemplateVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Drugstemplate> itemsIPage = new Page<>(page, limit);
        IPage<Drugstemplate> result = drugsTemplateMapper.selectPage(itemsIPage, new QueryWrapper<Drugstemplate>().like("name",name));
        List<Drugstemplate> list = result.getRecords();
        List<DrugsTemplateVO> drugsTemplateVOList = list.stream().map(e -> new DrugsTemplateVO(
                e.getTempId(),
                e.getName(),
                constantItemService.findNameById(e.getScopeId())
        )).collect(Collectors.toList());
        dataVO.setCount(result.getTotal());
        dataVO.setData(drugsTemplateVOList);
        return dataVO;
    }
    @Override
    public Drugstemplate findById(Integer id) {
        return drugsTemplateMapper.selectOne(new QueryWrapper<Drugstemplate>().eq("temp_id",id));
    }
}
