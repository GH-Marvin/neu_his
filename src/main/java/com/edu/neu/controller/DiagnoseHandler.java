package com.edu.neu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.neu.dto.WestDiagnoseDTO;
import com.edu.neu.entity.Disease;
import com.edu.neu.entity.Register;
import com.edu.neu.service.ConstantItemService;
import com.edu.neu.service.DiseaseService;
import com.edu.neu.service.RegisterService;
import com.edu.neu.util.ResultUtil;
import com.edu.neu.util.WestDiagnosisUtil;
import com.edu.neu.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/diagnose")
public class DiagnoseHandler {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private ConstantItemService constantItemService;
    @Autowired
    private DiseaseService diseaseService;
    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    @GetMapping("/initWaitingList/{doctor_id}")
    public DataVO<WaitingItemVO> initWaitingList(@PathVariable("doctor_id") String doctor_id , Integer page, Integer limit){
       if(doctor_id!=""&&doctor_id!=null){
            return registerService.findRegistrationsByDoctorId(Integer.valueOf(doctor_id),page,limit);
       }else {
           return null;
       }
    }

    @GetMapping("/initWestDiagnosisList")
    public DataVO<DiseaseVO> initWestDiagnosisList(Integer page,Integer limit){
        return diseaseService.findAll(page,limit);
    }
    @GetMapping("/initWestDiagnosisListByName")
    public DataVO<DiseaseVO> initWestDiagnosisListByName(String name,Integer page,Integer limit){
        return diseaseService.findAllByName(name,page,limit);
    }

    @GetMapping("/initWestDiagnosisList/{data}")
    public DataVO<WestDiagnoseItemVO> updateDiagnose(@PathVariable("data") String data){
        System.out.println("data:"+data);
        return diseaseService.updateDiagnoseList(data);

    }
}
