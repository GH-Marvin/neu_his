package com.edu.neu.service;

import com.edu.neu.entity.Prescription;
import com.edu.neu.form.PrescriptionFrom;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.PrescriptionVO;

public interface PrescriptionService {
    void save(PrescriptionFrom prescriptionFrom);
    void deleteById(Integer id);
    Integer findByUniqueInfo(Integer regist_id,Integer user_id,String prescription_name);
    DataVO<PrescriptionVO> findAllByRegistId(Integer id,Integer page,Integer limit);


}
