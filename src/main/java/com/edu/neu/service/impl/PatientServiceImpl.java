package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.edu.neu.dto.PatientDTO;
import com.edu.neu.entity.Patient;
import com.edu.neu.form.RegisterForm;
import com.edu.neu.mapper.PatientMapper;
import com.edu.neu.service.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientMapper patientMapper;
    @Override
    public void insert(Patient patient) {
        patientMapper.insert(patient);
    }

    @Override
    public Patient findById(Integer id) {
        return patientMapper.selectOne(new QueryWrapper<Patient>().eq("patient_id",id));
    }

    @Override
    public Patient findByCaseNumber(String caseNumber) {
        return patientMapper.selectOne(new QueryWrapper<Patient>().eq("case_number",caseNumber));
    }

    @Override
    public void saveOrUpdate(RegisterForm registerForm) {
        if (findByCaseNumber(registerForm.getCaseNumber())!=null){
            UpdateWrapper<Patient> patientUpdateWrapper = new UpdateWrapper<>();
            patientUpdateWrapper.eq("case_number",registerForm.getCaseNumber())
                    .set("real_name",registerForm.getRealName())
                    .set("id_number",registerForm.getIdNumber())
                    .set("gender",registerForm.getGender())
                    .set("birth_date",registerForm.getBirthDate())
                    .set("age",registerForm.getAge())
                    .set("age_type","岁")
                    .set("home_address",registerForm.getHomeAddress());
            patientMapper.update(findByCaseNumber(registerForm.getCaseNumber()),patientUpdateWrapper);

        }else {
            Patient patient = new Patient();
            BeanUtils.copyProperties(registerForm,patient);
            insert(patient);
        }
    }

    @Override
    public Integer getMaxId() {
        return patientMapper.getMaxId();
    }

    @Override
    public void remove(String caseNumber) {
        QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
        patientMapper.delete(queryWrapper.eq("case_number",caseNumber));
    }

}
