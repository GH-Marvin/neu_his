package com.edu.neu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.neu.entity.Invoice;
import org.apache.ibatis.annotations.Select;

public interface InvoiceMapper extends BaseMapper<Invoice> {
    @Select("SELECT\n" +
            "\tAuto_increment \n" +
            "FROM\n" +
            "\tinformation_schema.`TABLES` \n" +
            "WHERE\n" +
            "\tTable_Schema = 'his'\n" +
            "\tAND table_name = 'invoice'\n" +
            "\tLIMIT 1;")
    public Integer getMaxId();
}
