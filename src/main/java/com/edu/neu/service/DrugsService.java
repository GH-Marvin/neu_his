package com.edu.neu.service;

import com.edu.neu.entity.Drugs;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.DrugsVO;

import javax.xml.crypto.Data;

public interface DrugsService {
    public DataVO<DrugsVO> findAll(Integer page,Integer limit);
    public DataVO<DrugsVO> findAllByName(String name, Integer page, Integer limit);
    public Drugs findById(Integer id);
}
