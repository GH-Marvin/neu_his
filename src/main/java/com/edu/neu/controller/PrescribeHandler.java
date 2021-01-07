package com.edu.neu.controller;


import com.edu.neu.service.DrugsDetailedService;
import com.edu.neu.service.DrugsService;
import com.edu.neu.service.DrugsTemplateService;
import com.edu.neu.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/prescribe")
public class PrescribeHandler {

    @Autowired
    private DrugsService drugsService;

    @Autowired
    private DrugsTemplateService drugsTemplateService;

    @Autowired
    private DrugsDetailedService drugsDetailedService;

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

    @GetMapping("initDrugsTemplateItemListByTempId/{id}")
    public DataVO<DrugsDetailedVO> initDrugsTemplateItemListByTempId(@PathVariable("id") String id, Integer page, Integer limit){
        return drugsDetailedService.findAllByTempId(Integer.valueOf(id),page,limit);
    }

}
