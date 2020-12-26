package com.edu.neu.service;

import com.edu.neu.entity.Register;
import com.edu.neu.vo.RegisterVO;
import com.edu.neu.vo.ResultVO;

public interface RegisterService {
    public String initCaseNumber();
    public RegisterVO findVOById(String caseNumber);
    public Register findById(String caseNumber);
    public void createRegister(Register register);
}
