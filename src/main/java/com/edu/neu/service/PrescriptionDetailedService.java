package com.edu.neu.service;

import com.edu.neu.dto.PrescriptionDTO;
import com.edu.neu.entity.Prescriptiondetailed;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.PrescriptionDetailsVO;

import java.util.List;

public interface PrescriptionDetailedService {
    Prescriptiondetailed findByUniqueInfo(Integer prescriptionId, Integer drugsId);
    void save(PrescriptionDTO prescriptionDTO);
    void deleteByPrescriptionId(Integer id);
    void deleteByPdId(Integer id);
    DataVO<PrescriptionDetailsVO> findAllByPrescriptionId(Integer id, Integer page, Integer limit);
    List<Prescriptiondetailed> findAllByPrescriptionId(Integer id);
    Double calculateTotalPrice(Integer id);

//    DataVO<PatientCostsVO> findPaymentDetailsVOById(String caseNumber , String name);
}
