package com.edu.neu.service;

import com.edu.neu.entity.Invoice;
import com.edu.neu.entity.Patientcosts;
import com.edu.neu.form.PaymentForm;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.PatientCostsVO;
import com.edu.neu.vo.PrescriptionDetailsVO;

public interface PatientCostsService {
    void addPatientCosts(Patientcosts patientcosts);
    void deletePatientCostsByUniqueInfo(Integer regist_id , Integer dept_id , Integer create_oper_id , Integer item_type);
    DataVO<PatientCostsVO> findAllByCaseNumber(String caseNumber , Integer item_type , Integer state);
    DataVO<PatientCostsVO> findAllByCaseNumber(String caseNumber , Integer state);
    void update(Patientcosts patientcosts);
    Patientcosts findById(Integer id);
}
