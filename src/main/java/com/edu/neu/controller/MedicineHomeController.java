package com.edu.neu.controller;


import com.edu.neu.entity.Patientcosts;
import com.edu.neu.service.InvoiceService;
import com.edu.neu.service.PatientCostsService;
import com.edu.neu.service.RegisterService;
import com.edu.neu.util.ResultUtil;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.PatientCostsVO;
import com.edu.neu.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicineHome")
public class MedicineHomeController {
    @Autowired
    private RegisterService registerService;

    @Autowired
    private PatientCostsService patientCostsService;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    @GetMapping("/findMedicineDetailsById")
    public DataVO<PatientCostsVO> findMedicineDetailsVO(String caseNumber) {
        return patientCostsService.findAllByCaseNumber(caseNumber , 2, 2);
    }
    @GetMapping("/findBackMedicineDetailsById")
    public DataVO<PatientCostsVO> findBackMedicineDetailsVO(String caseNumber) {
        return patientCostsService.findAllByCaseNumber(caseNumber , 2, 3);
    }

    @GetMapping("/output/{pc_ids}")
    public ResultVO output(@PathVariable("pc_ids") String pc_ids) {
        String[] patientCostsIdArray = pc_ids.split("&");
        for(int i = 0 ; i < patientCostsIdArray.length; i++) {
            Patientcosts patientcosts = patientCostsService.findById(Integer.valueOf(patientCostsIdArray[i]));
            patientcosts.setState(3);
            registerService.updateVisitState(Integer.valueOf(patientCostsIdArray[i]) , 5);
            patientCostsService.update(patientcosts);
        }
        return ResultUtil.success("发药成功！");
    }

    @GetMapping("/backMedicine/{pc_ids}")
    public ResultVO backMedicine(@PathVariable("pc_ids") String pc_ids) {
        String[] patientCostsIdArray = pc_ids.split("&");
        for(int i = 0 ; i < patientCostsIdArray.length; i++) {
            Patientcosts patientcosts = patientCostsService.findById(Integer.valueOf(patientCostsIdArray[i]));
            patientcosts.setState(2);
            registerService.updateVisitState(Integer.valueOf(patientCostsIdArray[i]) , -5);
            patientCostsService.update(patientcosts);
        }
        return ResultUtil.success("退药成功！");
    }

}
