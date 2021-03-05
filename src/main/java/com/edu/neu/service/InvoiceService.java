package com.edu.neu.service;

import com.edu.neu.entity.Invoice;

public interface InvoiceService {
    Integer getMaxId();
    void insert(Invoice invoice);
    void remove(String invoiceNum);
    Invoice findByInvoiceNum(String invoiceNum);
}
