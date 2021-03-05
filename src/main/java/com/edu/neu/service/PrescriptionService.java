package com.edu.neu.service;

import com.edu.neu.entity.Prescription;
import com.edu.neu.form.PrescriptionFrom;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.PrescriptionVO;

import java.util.List;

public interface PrescriptionService {
    void save(PrescriptionFrom prescriptionFrom);
    Prescription findById(Integer id);
    void deleteById(Integer id);
    Integer findByUniqueInfo(Integer regist_id,Integer user_id,String prescription_name);
    DataVO<PrescriptionVO> findAllByMedicalId(Integer id,Integer page,Integer limit);
    void updateStateById(Integer id , Integer prescription_state);
    List<Prescription> findByMedicalId(Integer medical_id);
    List<Prescription> findByRegistId(Integer regist_id);
}
