package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.neu.entity.Medicalrecord;
import com.edu.neu.form.DiagnosisForm;
import com.edu.neu.mapper.MedicalRecordMapper;
import com.edu.neu.service.MedicalRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    @Resource
    private MedicalRecordMapper medicalRecordMapper;
    @Override
    public void saveOrUpdate(DiagnosisForm diagnosisForm) {
        if(diagnosisForm.getCaseState()==1){
            //Update
        }else if(diagnosisForm.getCaseState()==2){
            //Save
            Medicalrecord medicalrecord = new Medicalrecord();
            BeanUtils.copyProperties(diagnosisForm,medicalrecord);
            medicalRecordMapper.insert(medicalrecord);
        }
    }

    @Override
    public Medicalrecord findRecordByRegistId(Integer registId) {
        return medicalRecordMapper.selectOne(new QueryWrapper<Medicalrecord>().eq("regist_id",registId));
    }
}
