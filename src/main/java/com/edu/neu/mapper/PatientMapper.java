package com.edu.neu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.neu.entity.Patient;
import org.apache.ibatis.annotations.Select;

public interface PatientMapper extends BaseMapper<Patient> {
    @Select("SELECT\n" +
            "\tAuto_increment \n" +
            "FROM\n" +
            "\tinformation_schema.`TABLES` \n" +
            "WHERE\n" +
            "\tTable_Schema = 'his'\n" +
            "\tAND table_name = 'patient'\n" +
            "\tLIMIT 1;")
    public Integer getMaxId();
}
