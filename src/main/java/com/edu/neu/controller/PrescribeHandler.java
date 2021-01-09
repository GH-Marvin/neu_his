package com.edu.neu.controller;


import com.edu.neu.form.PrescriptionFrom;
import com.edu.neu.service.*;
import com.edu.neu.service.impl.PrescriptionDetailedServiceImpl;
import com.edu.neu.util.ResultUtil;
import com.edu.neu.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
            prescriptionDetailedService.save(prescriptionFrom);
            return ResultUtil.success("使用处方模板成功!", prescriptionService.findByUniqueInfo(prescriptionFrom.getRegistId(),prescriptionFrom.getUserId(),prescriptionFrom.getPrescriptionName()));
        }
    }

    @GetMapping("/initPrescriptionList")
    public DataVO<PrescriptionVO> initPrescriptionList(Integer id,Integer page,Integer limit){
        return prescriptionService.findAllByRegistId(id,page,limit);
    }
    @GetMapping("/initPrescriptionDetailedList")
    public DataVO<PrescriptionDetailedVO> initPrescriptionDetailedList(Integer id, Integer page,Integer limit){
        return prescriptionDetailedService.findAllByPrescriptionId(id,page,limit);
    }

    @GetMapping("/delete/{id}")
    public ResultVO delete(@PathVariable("id") Integer id){
        prescriptionService.deleteById(id);
        prescriptionDetailedService.deleteById(id);
        return ResultUtil.success("删除成功!", null);
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

}
