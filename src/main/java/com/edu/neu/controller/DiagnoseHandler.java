package com.edu.neu.controller;



import com.edu.neu.enums.RegisterStatusEnum;
import com.edu.neu.form.DiagnosisForm;
import com.edu.neu.service.*;
import com.edu.neu.util.ResultUtil;
import com.edu.neu.util.WestDiagnosisUtil;
import com.edu.neu.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/diagnose")
public class DiagnoseHandler {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private DiseaseService diseaseService;
    @Autowired
    private MedicalRecordService medicalRecordService;
    @Autowired
    private MedicalDiseaseService medicalDiseaseService;
    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    @GetMapping("/initWaitingList/{doctor_id}")
    public DataVO<WaitingItemVO> initWaitingList(@PathVariable("doctor_id") String doctor_id , Integer page, Integer limit){
       if(doctor_id!=""&&doctor_id!=null){
            return registerService.findRegistrationsByDoctorId(Integer.valueOf(doctor_id),page,limit,1);//待诊
       }else {
           return null;
       }
    }
    @GetMapping("/initDoneList/{doctor_id}")
    public DataVO<WaitingItemVO> initDoneList(@PathVariable("doctor_id") String doctor_id , Integer page, Integer limit){
        if(doctor_id!=""&&doctor_id!=null){
            return registerService.findRegistrationsByDoctorId(Integer.valueOf(doctor_id),page,limit,2);//已诊
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

    @PostMapping("/submit")
    @ResponseBody
    public ResultVO submit(@RequestBody DiagnosisForm diagnosisForm){
        medicalRecordService.saveOrUpdate(diagnosisForm);
        Integer medical_id = medicalRecordService.findRecordByRegistId(diagnosisForm.getRegistId()).getMedicalId();
        medicalDiseaseService.saveOrUpdate(diagnosisForm,medical_id);
        registerService.updateVisitState(diagnosisForm.getRegistId(), RegisterStatusEnum.DIAGNOSIS.getCode());
        return ResultUtil.success("开立成功！",null);
    }
}
