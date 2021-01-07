package com.edu.neu.service;


import com.edu.neu.entity.Drugstemplate;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.DrugsTemplateVO;


public interface DrugsTemplateService {
    DataVO<DrugsTemplateVO> findAll(Integer page, Integer limit);
    DataVO<DrugsTemplateVO> findAllByName(String name, Integer page, Integer limit);
    Drugstemplate findById(Integer id);
}
