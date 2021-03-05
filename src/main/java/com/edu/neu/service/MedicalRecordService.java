package com.edu.neu.service;

import com.edu.neu.entity.Medicalrecord;
import com.edu.neu.form.DiagnosisForm;

public interface MedicalRecordService {

    void saveOrUpdate(DiagnosisForm diagnosisForm);
    Medicalrecord findRecordByRegistId(Integer registId);

}
