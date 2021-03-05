package com.edu.neu.service;

import com.edu.neu.entity.Register;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.PatientVO;
import com.edu.neu.vo.RegisterVO;
import com.edu.neu.vo.WaitingItemVO;

import java.util.List;


public interface RegisterService {
    String initCaseNumber();
    PatientVO findVOById(String caseNumber);
    List<RegisterVO> findRegisterVOByCaseNumber(String caseNumber , Integer visit_state);
    Register findById(String caseNumber);
    void createRegister(Register register);
    DataVO<WaitingItemVO> findRegistrationsByDoctorId(Integer doctor_id, Integer page, Integer limit , Integer visit_state);
    void updateVisitState(Integer id,Integer visit_state);
    List<Register> findByCaseNumberAndVisitState(String caseNumber , Integer visitState);
}
