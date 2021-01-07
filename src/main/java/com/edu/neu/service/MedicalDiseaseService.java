package com.edu.neu.service;

import com.edu.neu.entity.Medicaldisease;
import com.edu.neu.form.DiagnosisForm;

import java.util.List;

public interface MedicalDiseaseService {
    public void saveOrUpdate(DiagnosisForm diagnosisForm,Integer medicalId);
    public List<Medicaldisease> findDiagnosisByRegistId(Integer registId);
}
