package com.edu.neu.service;

import com.edu.neu.entity.Register;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.RegisterVO;
import com.edu.neu.vo.WaitingItemVO;


public interface RegisterService {
    String initCaseNumber();
    RegisterVO findVOById(String caseNumber);
    Register findById(String caseNumber);
    void createRegister(Register register);
    DataVO<WaitingItemVO> findRegistrationsByDoctorId(Integer doctor_id, Integer page, Integer limit , Integer visit_state);
    void updateVisitState(Integer id,Integer visit_state);
}
