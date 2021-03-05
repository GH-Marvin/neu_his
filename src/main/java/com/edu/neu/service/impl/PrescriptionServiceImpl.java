package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.neu.entity.Drugsdetailed;
import com.edu.neu.entity.Prescription;
import com.edu.neu.form.PrescriptionFrom;
import com.edu.neu.mapper.PrescriptionMapper;
import com.edu.neu.service.ConstantItemService;
import com.edu.neu.service.PrescriptionService;
import com.edu.neu.util.PrescriptionUtil;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.DrugsDetailedVO;
import com.edu.neu.vo.PrescriptionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Resource
    private PrescriptionMapper prescriptionMapper;

    @Autowired
    private ConstantItemService constantItemService;

    @Override
    public void save(PrescriptionFrom prescriptionFrom) {
        Prescription prescription = new Prescription();
        BeanUtils.copyProperties(prescriptionFrom,prescription);
        prescription.setPrescriptionTime(new Date());
        prescriptionMapper.insert(prescription);
    }

    @Override
    public Prescription findById(Integer id) {
        return prescriptionMapper.selectOne(new QueryWrapper<Prescription>().eq("prescription_id" , id));
    }

    @Override
    public void deleteById(Integer id) {
        prescriptionMapper.delete(new QueryWrapper<Prescription>().eq("prescription_id",id));
    }

    @Override
    public Integer findByUniqueInfo(Integer regist_id,Integer user_id,String prescription_name) {
        Prescription prescription = prescriptionMapper.selectOne(new QueryWrapper<Prescription>().eq("regist_id",regist_id).eq("user_id",user_id).eq("prescription_name",prescription_name));
        if(prescription==null){
            return null;
        }else {
            return prescription.getPrescriptionId();
        }
    }

    @Override
    public DataVO<PrescriptionVO> findAllByMedicalId(Integer medical_id, Integer page, Integer limit) {
        DataVO<PrescriptionVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Prescription> itemsIPage = new Page<>(page, limit);
        IPage<Prescription> result = prescriptionMapper.selectPage(itemsIPage, new QueryWrapper<Prescription>().eq("medical_id",medical_id));
        List<Prescription> list = result.getRecords();
        List<PrescriptionVO> prescriptionVOList = list.stream().map(e -> new PrescriptionVO(
                e.getPrescriptionId(),
                PrescriptionUtil.transformToName(e.getPrescriptionName()),
                constantItemService.findNameById(e.getPrescriptionState())
        )).collect(Collectors.toList());
        dataVO.setCount(result.getTotal());
        dataVO.setData(prescriptionVOList);
        return dataVO;
    }

    @Override
    public void updateStateById(Integer id , Integer prescription_state) {
        UpdateWrapper<Prescription> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("prescription_id" , id).set("prescription_state" , prescription_state);
        prescriptionMapper.update(null , updateWrapper);
    }

    @Override
    public List<Prescription> findByMedicalId(Integer medical_id) {
        return prescriptionMapper.selectList(new QueryWrapper<Prescription>().eq("medical_id" , medical_id));
    }

    @Override
    public List<Prescription> findByRegistId(Integer regist_id) {
        return prescriptionMapper.selectList(new QueryWrapper<Prescription>().eq("regist_id" , regist_id));

    }
}
