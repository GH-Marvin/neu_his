package com.edu.neu.service.impl;

import com.edu.neu.service.ConstantItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConstantItemItemServiceImplTest {

    @Autowired
    private ConstantItemService constantItemService;
    @Test
    void findNameById() {
        String name = constantItemService.findNameById(71);
        int i = 0;
    }
}