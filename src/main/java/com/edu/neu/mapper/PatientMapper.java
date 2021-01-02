package com.edu.neu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.neu.entity.Patient;
import org.apache.ibatis.annotations.Select;

public interface PatientMapper extends BaseMapper<Patient> {
    @Select("SELECT MAX(patient_id) AS id\n" +
            "FROM patient")
    public Integer getMaxId();
}
