package com.edu.neu.controller;

import com.edu.neu.entity.Invoice;
import com.edu.neu.entity.Patientcosts;
import com.edu.neu.form.PaymentForm;
import com.edu.neu.service.InvoiceService;
import com.edu.neu.service.PatientCostsService;
import com.edu.neu.service.RegisterService;
import com.edu.neu.util.ResultUtil;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.PatientCostsVO;
import com.edu.neu.vo.RegisterVO;
import com.edu.neu.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/back")
public class BackHandler {
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

    @GetMapping("/findRegisterDetailsById")
    public DataVO<RegisterVO> findRegisterDetailsVO(String caseNumber) {
        List<RegisterVO> list = registerService.findRegisterVOByCaseNumber(caseNumber , 1);
        DataVO<RegisterVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        dataVO.setCount(Long.valueOf(list.size()));
        dataVO.setData(list);
        return dataVO;
    }

    @GetMapping("/backRegister/{regist_ids}")
    public ResultVO backRegister(@PathVariable("regist_ids") String regist_ids) {
        String[] registIdArray = regist_ids.split("&");
        for(int i = 0 ; i < registIdArray.length; i++) {
            registerService.updateVisitState(Integer.valueOf(registIdArray[i]) , -1);
        }
        return ResultUtil.success("退号成功！");
    }

    @GetMapping("/findPaymentDetailsById")
    public DataVO<PatientCostsVO> findPaymentDetailsVO(String caseNumber) {
        return patientCostsService.findAllByCaseNumber(caseNumber , 2);
    }
    @PostMapping("/backPay")
    @ResponseBody
    public ResultVO backPay(@RequestBody PaymentForm paymentForm) {
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(paymentForm , invoice);
        invoice.setMoney(-paymentForm.getMoney());
        invoice.setState(-1);
        invoice.setFeeType(171);
        invoice.setCreationTime(new Date());
        invoiceService.insert(invoice);
        Invoice temp_invoice = invoiceService.findByInvoiceNum(paymentForm.getInvoiceNum());
        String[] patientCostsArray = paymentForm.getPatientCostsData().split("&");
        for(int i = 0; i < patientCostsArray.length; i++) {
            Patientcosts temp_pc = patientCostsService.findById(Integer.valueOf(patientCostsArray[i]));
            Patientcosts res = new Patientcosts();
            res.setInvoiceId(temp_invoice.getInvoiceId());
            res.setRegistId(temp_pc.getRegistId());
            res.setName(temp_pc.getName());
            res.setCreateTime(temp_invoice.getCreationTime());
            res.setFeeType(171);
            res.setRegisterId(temp_invoice.getUserId());
            res.setAmount(-temp_pc.getAmount());
            res.setItemId(temp_pc.getItemId());
            res.setPrice(temp_pc.getPrice());
            res.setState(-1);
            res.setPayTime(temp_pc.getPayTime());
            res.setItemType(temp_pc.getItemType());
            res.setDeptId(temp_pc.getDeptId());
            res.setCreateOperId(temp_pc.getCreateOperId());
            res.setCaseNumber(temp_pc.getCaseNumber());
            patientCostsService.addPatientCosts(res);
            temp_pc.setState(7);
            patientCostsService.update(temp_pc);
            registerService.updateVisitState(temp_pc.getRegistId() , -4);
        }

        return ResultUtil.success("病历号："+paymentForm.getCaseNumber()+" 退费成功");
    }


}
