package com.edu.neu.service;

import com.edu.neu.entity.Drugsdetailed;
import com.edu.neu.entity.Drugstemplate;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.DrugsDetailedVO;
import com.edu.neu.vo.DrugsTemplateVO;

public interface DrugsDetailedService {
    public DataVO<DrugsDetailedVO> findAllByTempId(Integer temp_id, Integer page, Integer limit);
    public Drugsdetailed findById(Integer id);
}
