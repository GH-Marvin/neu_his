package com.edu.neu.service;

import com.edu.neu.dto.PatientDTO;
import com.edu.neu.entity.Patient;
import com.edu.neu.entity.Register;
import com.edu.neu.form.RegisterForm;

public interface PatientService {
    void insert(Patient patient);
    void remove(String caseNumber);
    Patient findById(Integer id);
    Patient findByCaseNumber(String caseNumber);
    void saveOrUpdate(RegisterForm registerForm);
    Integer getMaxId();

}
