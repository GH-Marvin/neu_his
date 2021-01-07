package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.neu.entity.Disease;
import com.edu.neu.entity.Register;
import com.edu.neu.mapper.DiseaseMapper;
import com.edu.neu.service.DiseaseService;
import com.edu.neu.util.WestDiagnosisUtil;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.DiseaseVO;
import com.edu.neu.vo.WaitingItemVO;
import com.edu.neu.vo.WestDiagnoseItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DiseaseServiceImpl implements DiseaseService {
    @Resource
    private DiseaseMapper diseaseMapper;
    @Override
    public DataVO<DiseaseVO> findAll(Integer page,Integer limit) {
        DataVO<DiseaseVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Disease> itemsIPage = new Page<>(page, limit);
        IPage<Disease> result = diseaseMapper.selectPage(itemsIPage, null);
        List<Disease> list = result.getRecords();
        List<DiseaseVO> diseaseVOList = list.stream().map(e -> new DiseaseVO(
                e.getId(),
                e.getDiseaseIcd(),
                e.getDiseaseCode(),
                e.getDiseaseName()
        )).collect(Collectors.toList());
        dataVO.setCount(result.getTotal());
        dataVO.setData(diseaseVOList);
        return dataVO;
    }

    @Override
    public DataVO<DiseaseVO> findAllByName(String name, Integer page, Integer limit) {
        DataVO<DiseaseVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Disease> itemsIPage = new Page<>(page, limit);
        IPage<Disease> result = diseaseMapper.selectPage(itemsIPage, new QueryWrapper<Disease>().like("disease_name",name));
        List<Disease> list = result.getRecords();
        List<DiseaseVO> diseaseVOList = list.stream().map(e -> new DiseaseVO(
                e.getId(),
                e.getDiseaseIcd(),
                e.getDiseaseCode(),
                e.getDiseaseName()
        )).collect(Collectors.toList());
        dataVO.setCount(result.getTotal());
        dataVO.setData(diseaseVOList);
        return dataVO;
    }

    @Override
    public Disease findById(Integer id) {
        return diseaseMapper.selectOne(new QueryWrapper<Disease>().eq("id",id));
    }
}
