package com.edu.neu.service;

import com.edu.neu.entity.Disease;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.DiseaseVO;
import com.edu.neu.vo.WestDiagnoseItemVO;

import javax.xml.crypto.Data;

public interface DiseaseService {
    public DataVO<DiseaseVO> findAll(Integer page,Integer limit);
    public DataVO<DiseaseVO> findAllByName(String name,Integer page,Integer limit);
    public Disease findById(Integer id);
}
