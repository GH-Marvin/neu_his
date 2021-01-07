package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.neu.entity.Disease;
import com.edu.neu.entity.Drugs;
import com.edu.neu.mapper.DrugsMapper;
import com.edu.neu.service.ConstantItemService;
import com.edu.neu.service.DrugsService;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.DiseaseVO;
import com.edu.neu.vo.DrugsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugsServiceImpl implements DrugsService {

    @Resource
    private DrugsMapper drugsMapper;
    @Autowired
    private ConstantItemService constantItemService;
    @Override
    public DataVO<DrugsVO> findAll(Integer page,Integer limit) {
        DataVO<DrugsVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Drugs> itemsIPage = new Page<>(page, limit);
        IPage<Drugs> result = drugsMapper.selectPage(itemsIPage, null);
        List<Drugs> list = result.getRecords();
        List<DrugsVO> drugsVOList = list.stream().map(e->new DrugsVO(
                e.getDrugsId(),
                e.getDrugsCode(),
                e.getDrugsName(),
                e.getDrugsFormat(),
                e.getDrugsPrice(),
                e.getDrugsUnit(),
                constantItemService.findNameById(e.getDrugsDosageId()),
                constantItemService.findNameById(e.getDrugsTypeId())
        )).collect(Collectors.toList());
        dataVO.setCount(result.getTotal());
        dataVO.setData(drugsVOList);
        return dataVO;
    }
    @Override
    public DataVO<DrugsVO> findAllByName(String name, Integer page, Integer limit) {
        DataVO<DrugsVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Drugs> itemsIPage = new Page<>(page, limit);
        IPage<Drugs> result = drugsMapper.selectPage(itemsIPage, new QueryWrapper<Drugs>().like("drugs_name",name));
        List<Drugs> list = result.getRecords();
        List<DrugsVO> drugsVOList = list.stream().map(e -> new DrugsVO(
                e.getDrugsId(),
                e.getDrugsCode(),
                e.getDrugsName(),
                e.getDrugsFormat(),
                e.getDrugsPrice(),
                e.getDrugsUnit(),
                constantItemService.findNameById(e.getDrugsDosageId()),
                constantItemService.findNameById(e.getDrugsTypeId())
        )).collect(Collectors.toList());
        dataVO.setCount(result.getTotal());
        dataVO.setData(drugsVOList);
        return dataVO;
    }
    @Override
    public Drugs findById(Integer id) {
        return drugsMapper.selectOne(new QueryWrapper<Drugs>().eq("drugs_id",id));
    }
}
