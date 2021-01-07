package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.neu.entity.Medicaldisease;
import com.edu.neu.entity.Medicalrecord;
import com.edu.neu.form.DiagnosisForm;
import com.edu.neu.mapper.MedicalDiseaseMapper;
import com.edu.neu.service.MedicalDiseaseService;
import com.edu.neu.vo.WestDiagnoseItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class MedicalDiseaseServiceImpl implements MedicalDiseaseService {
    @Resource
    private MedicalDiseaseMapper medicalDiseaseMapper;
    @Override
    public void saveOrUpdate(DiagnosisForm diagnosisForm,Integer medicalId) {
        if(diagnosisForm.getCaseState()==1){
            //Update
        }else if(diagnosisForm.getCaseState()==2){
            //Save

            List<WestDiagnoseItemVO> list = diagnosisForm.getData();
            for(WestDiagnoseItemVO w:list){
                if(w.getTime()!=null&&w.getTime()!="") {
                    Medicaldisease medicaldisease = new Medicaldisease();
                    medicaldisease.setDiagnoseType(1);
                    medicaldisease.setDiseaseId(w.getId());
                    try {
                        medicaldisease.setSiskDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(w.getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    medicaldisease.setDiagnoseCate(diagnosisForm.getDiagnoseCate());
                    medicaldisease.setRegistId(diagnosisForm.getRegistId());
                    medicaldisease.setMedicalId(medicalId);
                    medicalDiseaseMapper.insert(medicaldisease);
                }
            }

        }
    }

    @Override
    public List<Medicaldisease> findDiagnosisByRegistId(Integer registId) {
        return medicalDiseaseMapper.selectList(new QueryWrapper<Medicaldisease>().eq("regist_id",registId));
    }
}
