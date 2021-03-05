package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.neu.entity.Invoice;
import com.edu.neu.mapper.InvoiceMapper;
import com.edu.neu.service.InvoiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Resource
    private InvoiceMapper invoiceMapper;

    @Override
    public Integer getMaxId() {
        return invoiceMapper.getMaxId();
    }

    @Override
    public void insert(Invoice invoice) {
        invoiceMapper.insert(invoice);
    }

    @Override
    public void remove(String invoiceNum) {
        QueryWrapper<Invoice> queryWrapper = new QueryWrapper<>();
        invoiceMapper.delete(queryWrapper.eq("invoice_num" , invoiceNum));
    }

    @Override
    public Invoice findByInvoiceNum(String invoiceNum) {
        return invoiceMapper.selectOne(new QueryWrapper<Invoice>().eq("invoice_num" , invoiceNum));
    }
}
