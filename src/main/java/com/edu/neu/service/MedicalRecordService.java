package com.edu.neu.service;

import com.edu.neu.entity.Medicalrecord;
import com.edu.neu.form.DiagnosisForm;

public interface MedicalRecordService {
    public void saveOrUpdate(DiagnosisForm diagnosisForm);
    public Medicalrecord findRecordByRegistId(Integer registId);
}
