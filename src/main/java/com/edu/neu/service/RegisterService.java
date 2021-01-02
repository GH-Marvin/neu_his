package com.edu.neu.service;

import com.edu.neu.entity.Register;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.RegisterVO;
import com.edu.neu.vo.ResultVO;
import com.edu.neu.vo.WaitingItemVO;

import java.util.List;

public interface RegisterService {
    public String initCaseNumber();
    public RegisterVO findVOById(String caseNumber);
    public Register findById(String caseNumber);
    public void createRegister(Register register);
    public DataVO<WaitingItemVO> findRegistrationsByDoctorId(Integer doctor_id, Integer page, Integer limit);
}
