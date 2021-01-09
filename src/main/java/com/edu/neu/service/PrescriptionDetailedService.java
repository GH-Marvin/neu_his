package com.edu.neu.service;

import com.edu.neu.form.PrescriptionFrom;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.PrescriptionDetailedVO;
import com.edu.neu.vo.PrescriptionVO;

public interface PrescriptionDetailedService {
    void save(PrescriptionFrom prescriptionFrom);
    void deleteById(Integer id);
    DataVO<PrescriptionDetailedVO> findAllByPrescriptionId(Integer id, Integer page, Integer limit);

}
