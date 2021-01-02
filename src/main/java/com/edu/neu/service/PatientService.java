package com.edu.neu.service;

import com.edu.neu.dto.PatientDTO;
import com.edu.neu.entity.Patient;
import com.edu.neu.entity.Register;
import com.edu.neu.form.RegisterForm;

public interface PatientService {
    public void insert(Patient patient);
    public Patient findById(Integer id);
    public Patient findByCaseNumber(String caseNumber);
    public void saveOrUpdate(RegisterForm registerForm);
    public Integer getMaxId();
    public void remove(String caseNumber);
}
