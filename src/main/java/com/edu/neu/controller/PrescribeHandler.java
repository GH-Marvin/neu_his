package com.edu.neu.controller;


import com.edu.neu.dto.PatientCostsDTO;
import com.edu.neu.dto.PrescriptionDTO;
import com.edu.neu.entity.Patientcosts;
import com.edu.neu.entity.Prescription;
import com.edu.neu.entity.Prescriptiondetailed;
import com.edu.neu.enums.PrescriptionStatusEnum;
import com.edu.neu.form.PrescriptionFrom;
import com.edu.neu.service.*;
import com.edu.neu.util.ResultUtil;
import com.edu.neu.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/prescribe")
public class PrescribeHandler {

    @Autowired
    private DrugsService drugsService;

    @Autowired
    private DrugsTemplateService drugsTemplateService;

    @Autowired
    private DrugsDetailedService drugsDetailedService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PrescriptionDetailedService prescriptionDetailedService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private PatientCostsService patientCostsService;

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    @GetMapping("/initDrugsList")
    public DataVO<DrugsVO> initDrugsList(Integer page, Integer limit){
        return drugsService.findAll(page,limit);
    }

    @GetMapping("/initDrugsListByName")
    public DataVO<DrugsVO> initDrugsListByName(String name,Integer page,Integer limit){
        return drugsService.findAllByName(name,page,limit);
    }

    @GetMapping("/initDrugsTemplateList")
    public DataVO<DrugsTemplateVO> initDrugsTemplateList(Integer page, Integer limit){
        return drugsTemplateService.findAll(page,limit);
    }

    @GetMapping("/initDrugsTemplateListByName")
    public DataVO<DrugsTemplateVO> initDrugsTemplateListByName(String name,Integer page,Integer limit){
        return drugsTemplateService.findAllByName(name,page,limit);
    }

    @GetMapping("/initDrugsTemplateItemListByTempId/{id}")
    public DataVO<DrugsDetailedVO> initDrugsTemplateItemListByTempId(@PathVariable("id") String id, Integer page, Integer limit){
        return drugsDetailedService.findAllByTempId(Integer.valueOf(id),page,limit);
    }

    @PostMapping("/save")
    @ResponseBody
    public ResultVO save(@RequestBody PrescriptionFrom prescriptionFrom){
        Integer prescription_id = prescriptionService.findByUniqueInfo(prescriptionFrom.getRegistId(),prescriptionFrom.getUserId(),prescriptionFrom.getPrescriptionName());
        if(prescription_id != null){
            return ResultUtil.fail("此模板已经使用,不能重复使用！");
        }else{
            prescriptionService.save(prescriptionFrom);
            Integer pid = prescriptionService.findByUniqueInfo(prescriptionFrom.getRegistId(),prescriptionFrom.getUserId(),prescriptionFrom.getPrescriptionName());
            for(PrescriptionDTO prescriptionDTO:prescriptionFrom.getData()){
                prescriptionDTO.setPrescriptionId(pid);
                prescriptionDetailedService.save(prescriptionDTO);
            }
            return ResultUtil.success("使用处方模板成功!", pid);
        }
    }

    @GetMapping("/initPrescriptionList")
    public DataVO<PrescriptionVO> initPrescriptionList(Integer id,Integer page,Integer limit){
        return prescriptionService.findAllByMedicalId(id,page,limit);
    }
    @GetMapping("/initPrescriptionDetailedList")
    public DataVO<PrescriptionDetailsVO> initPrescriptionDetailedList(Integer id, Integer page, Integer limit){
        return prescriptionDetailedService.findAllByPrescriptionId(id,page,limit);
    }

    @GetMapping("/deletePrescription/{id}")
    public ResultVO deletePrescription(@PathVariable("id") Integer id){
        prescriptionService.deleteById(id);
        prescriptionDetailedService.deleteByPrescriptionId(id);
        return ResultUtil.success("处方删除成功!");
    }
    @GetMapping("/deletePrescriptionItem/{id}")
    public ResultVO deletePrescriptionItem(@PathVariable("id") Integer id){
        prescriptionDetailedService.deleteByPdId(id);
        return ResultUtil.success("药品删除成功!");
    }
    @PostMapping("/savePrescription")
    @ResponseBody
    public ResultVO savePrescription(@RequestBody PrescriptionFrom prescriptionFrom){
        Integer prescription_id = prescriptionService.findByUniqueInfo(prescriptionFrom.getRegistId(),prescriptionFrom.getUserId(),prescriptionFrom.getPrescriptionName());
        if(prescription_id != null){
            return ResultUtil.fail("此模板名已被添加,不能重复添加！");
        }else{
            prescriptionService.save(prescriptionFrom);
            return ResultUtil.success("新增处方模板成功!", prescriptionService.findByUniqueInfo(prescriptionFrom.getRegistId(),prescriptionFrom.getUserId(),prescriptionFrom.getPrescriptionName()));
        }
    }

    @PostMapping("/saveMedicine")
    @ResponseBody
    public ResultVO saveMedicine(@RequestBody PrescriptionDTO prescriptionDTO){
        Prescriptiondetailed prescriptiondetailed = prescriptionDetailedService.findByUniqueInfo(prescriptionDTO.getPrescriptionId(),prescriptionDTO.getDrugsId());
        if(prescriptiondetailed != null){
            return ResultUtil.fail("此药品已被添加,不能重复添加！");
        }else{
            prescriptionDetailedService.save(prescriptionDTO);
            return ResultUtil.success("添加药品成功!");
        }
    }

    /**
     * 计算当前处方总价
     * @param id
     * @return
     */
    @GetMapping("/calculateTotalPrice/{id}")
    public ResultVO calculateTotalPrice(@PathVariable("id") Integer id) {
        Double total_price = prescriptionDetailedService.calculateTotalPrice(id);
        return ResultUtil.success("计算总价成功！",total_price);
    }


    @PostMapping("/submit")
    @ResponseBody
    public ResultVO submit(@RequestBody PatientCostsDTO patientCostsDTO) {
        Integer medical_id = patientCostsDTO.getMedicalId();
        List<Prescription> list = prescriptionService.findByMedicalId(medical_id);
        boolean flag = false;
        List<Patientcosts> data = new ArrayList<>();
        for(Prescription p : list) {
            if(p.getPrescriptionState() == 168) {
                flag = true;
                prescriptionService.updateStateById(p.getPrescriptionId() , PrescriptionStatusEnum.SUBMITTED.getCode());
                List<PrescriptionDetailsVO> voList = prescriptionDetailedService.findAllByPrescriptionId(p.getPrescriptionId() , 1 , 99).getData();
                for(PrescriptionDetailsVO vo : voList) {
                    Patientcosts patientcosts = new Patientcosts();
                    BeanUtils.copyProperties(patientCostsDTO , patientcosts);
                    patientcosts.setItemId(vo.getDrugsId());
                    patientcosts.setInvoiceId(-1);
                    patientcosts.setName(vo.getDrugsName());
                    patientcosts.setPrice(vo.getDrugsPrice());
                    patientcosts.setAmount(vo.getAmount());
                    patientcosts.setCreateTime(new Date());
                    patientcosts.setState(1);
                    data.add(patientcosts);
                    patientCostsService.addPatientCosts(patientcosts);
                }
            }else {
                continue;
            }
        }
        if(flag == true) {
            registerService.updateVisitState(patientCostsDTO.getRegistId() , 3);
            return ResultUtil.success("处方开立成功！" , data);
        }else {
            switch (list.get(list.size() - 1).getPrescriptionState()) {
                case 169:
                    return ResultUtil.fail("已开立，无需重复开立！");
                case 170:
                    return ResultUtil.fail("已作废，不能开立！");
            }
        }
        return ResultUtil.fail("未知错误！");

    }


    @PostMapping("/cancel")
    @ResponseBody
    public ResultVO cancel(@RequestBody PatientCostsDTO patientCostsDTO) {
        Integer medical_id = patientCostsDTO.getMedicalId();
        Integer regist_id = patientCostsDTO.getRegistId();
        Integer dept_id = patientCostsDTO.getDeptId();
        Integer create_oper_id = patientCostsDTO.getCreateOperId();
        Integer item_type = patientCostsDTO.getItemType();
        List<Prescription> list = prescriptionService.findByMedicalId(medical_id);
        boolean flag = false;
        for (Prescription p : list) {
            if(p.getPrescriptionState() == 169) {
                flag = true;
                prescriptionService.updateStateById(p.getPrescriptionId() , PrescriptionStatusEnum.CANCEL.getCode());
                patientCostsService.deletePatientCostsByUniqueInfo(regist_id , dept_id , create_oper_id , item_type);

            }
        }
        if(flag == true) {
            registerService.updateVisitState(regist_id, 2);
            return ResultUtil.success("处方作废成功！");
        }
        return ResultUtil.fail("已作废，无需重复作废！");
    }
}
