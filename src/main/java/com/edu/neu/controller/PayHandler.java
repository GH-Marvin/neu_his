package com.edu.neu.controller;

import com.edu.neu.entity.Invoice;
import com.edu.neu.entity.Patientcosts;
import com.edu.neu.form.PaymentForm;
import com.edu.neu.service.*;
import com.edu.neu.util.ResultUtil;
import com.edu.neu.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pay")
public class PayHandler {


    @Autowired
    private ConstantItemService constantItemService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private PatientCostsService patientCostsService;

    @Autowired
    private RegisterService registerService;

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    @GetMapping("/findPaymentDetailsById")
    public DataVO<PatientCostsVO> findPaymentDetailsVO(String caseNumber) {

        return patientCostsService.findAllByCaseNumber(caseNumber , 1);
    }

    @GetMapping("/initConstants")
    public ResultVO initConstants() {
        Map<String, List<ConstantVO>> map = new HashMap<>();

        //map加入初始化随机发票号
        Invoice invoice = new Invoice();
        int id = invoiceService.getMaxId();
        String invoiceNum = String.valueOf(id+800801);
        invoice.setInvoiceNum(invoiceNum);
        invoiceService.insert(invoice);
        ConstantVO constantVO = new ConstantVO();
        constantVO.setId(0);
        constantVO.setName(invoiceNum);
        List<ConstantVO> invoiceNumList = new ArrayList<>();
        invoiceNumList.add(constantVO);
        map.put("bill_id" , invoiceNumList);
        invoiceService.remove(invoiceNum);

        //map加入初始化支付方式列表
        List<ConstantVO> payList = constantItemService.findByTypeId(14).stream().map(e -> new ConstantVO(
                e.getId(),
                e.getConstantName()
        )).collect(Collectors.toList());
        map.put("pay" , payList);
        return ResultUtil.success("发票初始化成功！", map);
    }

    @PostMapping("/submit")
    @ResponseBody
    public ResultVO submit(@RequestBody PaymentForm paymentForm) {
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(paymentForm , invoice);
        invoice.setState(1);
        invoice.setCreationTime(new Date());
        invoiceService.insert(invoice);
        Invoice temp = invoiceService.findByInvoiceNum(paymentForm.getInvoiceNum());
        String[] patientCostsArray = paymentForm.getPatientCostsData().split("&");
        for(int i = 0; i < patientCostsArray.length; i++) {
            Patientcosts pc = patientCostsService.findById(Integer.valueOf(patientCostsArray[i]));
            pc.setInvoiceId(temp.getInvoiceId());
            pc.setPayTime(temp.getCreationTime());
            pc.setFeeType(temp.getFeeType());
            pc.setRegisterId(temp.getUserId());
            pc.setState(2);
            patientCostsService.update(pc);
            registerService.updateVisitState(pc.getRegistId() , 4);
        }

        return ResultUtil.success("病历号："+paymentForm.getCaseNumber()+" 缴费成功");
    }
}
