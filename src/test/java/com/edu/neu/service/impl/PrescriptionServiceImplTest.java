package com.edu.neu.service.impl;

import com.edu.neu.entity.Prescription;
import com.edu.neu.service.PrescriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PrescriptionServiceImplTest {
    @Autowired
    private PrescriptionService prescriptionService;

    @Test
    void updateStateByMedicalId() {
        prescriptionService.updateStateById(40 ,169);
        int i = 0;
    }
}