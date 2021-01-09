package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.neu.dto.PrescriptionDTO;
import com.edu.neu.entity.Drugs;
import com.edu.neu.entity.Prescription;
import com.edu.neu.entity.Prescriptiondetailed;
import com.edu.neu.form.PrescriptionFrom;
import com.edu.neu.mapper.PrescriptionDetailedMapper;
import com.edu.neu.service.DrugsService;
import com.edu.neu.service.PrescriptionDetailedService;
import com.edu.neu.service.PrescriptionService;
import com.edu.neu.util.PrescriptionUtil;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.PrescriptionDetailedVO;
import com.edu.neu.vo.PrescriptionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionDetailedServiceImpl implements PrescriptionDetailedService {
    @Resource
    private PrescriptionDetailedMapper prescriptionDetailedMapper;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private DrugsService drugsService;

    @Override
    public void save(PrescriptionFrom prescriptionFrom) {
        List<PrescriptionDTO> list = prescriptionFrom.getData();
        for(PrescriptionDTO prescriptionDTO: list){
            Prescriptiondetailed prescriptiondetailed = new Prescriptiondetailed();
            BeanUtils.copyProperties(prescriptionDTO,prescriptiondetailed);
            prescriptiondetailed.setPrescriptionId(prescriptionService.findByUniqueInfo(prescriptionFrom.getRegistId(),prescriptionFrom.getUserId(),prescriptionFrom.getPrescriptionName()));
            prescriptionDetailedMapper.insert(prescriptiondetailed);
        }
    }

    @Override
    public void deleteById(Integer id) {
        prescriptionDetailedMapper.delete(new QueryWrapper<Prescriptiondetailed>().eq("prescription_id",id));
    }

    @Override
    public DataVO<PrescriptionDetailedVO> findAllByPrescriptionId(Integer id , Integer page, Integer limit) {
        DataVO<PrescriptionDetailedVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Prescriptiondetailed> itemsIPage = new Page<>(page, limit);
        Integer prescription_id = id;
        IPage<Prescriptiondetailed> result = prescriptionDetailedMapper.selectPage(itemsIPage, new QueryWrapper<Prescriptiondetailed>().eq("prescription_id",prescription_id));
        List<Prescriptiondetailed> list = result.getRecords();
        List<PrescriptionDetailedVO> prescriptionDetailedVOList = new ArrayList<>();
        for(Prescriptiondetailed p:list){
            PrescriptionDetailedVO prescriptionDetailedVO = new PrescriptionDetailedVO();
            BeanUtils.copyProperties(p,prescriptionDetailedVO);
            Drugs drugs = drugsService.findById(p.getDrugsId());
            BeanUtils.copyProperties(drugs,prescriptionDetailedVO);
            prescriptionDetailedVOList.add(prescriptionDetailedVO);
        }
        dataVO.setCount(result.getTotal());
        dataVO.setData(prescriptionDetailedVOList);
        return dataVO;
    }
}
