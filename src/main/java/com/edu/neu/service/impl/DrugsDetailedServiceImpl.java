package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.neu.entity.Drugsdetailed;
import com.edu.neu.entity.Drugstemplate;
import com.edu.neu.mapper.DrugsDetailedMapper;
import com.edu.neu.service.DrugsDetailedService;
import com.edu.neu.service.DrugsService;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.DrugsDetailedVO;
import com.edu.neu.vo.DrugsTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugsDetailedServiceImpl implements DrugsDetailedService {
    @Resource
    private DrugsDetailedMapper drugsDetailedMapper;
    @Autowired
    private DrugsService drugsService;
    @Override
    public DataVO<DrugsDetailedVO> findAllByTempId(Integer temp_id, Integer page, Integer limit) {
        DataVO<DrugsDetailedVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Drugsdetailed> itemsIPage = new Page<>(page, limit);
        IPage<Drugsdetailed> result = drugsDetailedMapper.selectPage(itemsIPage, new QueryWrapper<Drugsdetailed>().like("temp_id",temp_id));
        List<Drugsdetailed> list = result.getRecords();
        List<DrugsDetailedVO> drugsDetailedVOList = list.stream().map(e -> new DrugsDetailedVO(
                e.getDetailId(),
                e.getDrugsId(),
                e.getTempId(),
                drugsService.findById(e.getDrugsId()).getDrugsName(),
                drugsService.findById(e.getDrugsId()).getDrugsFormat(),
                drugsService.findById(e.getDrugsId()).getDrugsUnit(),
                e.getDrugsUsage(),
                e.getDosage(),
                e.getFrequency()
        )).collect(Collectors.toList());
        dataVO.setCount(result.getTotal());
        dataVO.setData(drugsDetailedVOList);
        return dataVO;
    }

    @Override
    public Drugsdetailed findById(Integer id) {
        return drugsDetailedMapper.selectOne(new QueryWrapper<Drugsdetailed>().eq("detail_id",id));
    }
}
