package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.neu.entity.Medicaldisease;
import com.edu.neu.entity.Register;
import com.edu.neu.mapper.RegisterMapper;
import com.edu.neu.service.*;
import com.edu.neu.util.KeyUtil;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.DoneItemVO;
import com.edu.neu.vo.RegisterVO;
import com.edu.neu.vo.WaitingItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private RegisterMapper registerMapper;
    @Autowired
    private ConstantItemService constantItemService;
    @Autowired
    private MedicalDiseaseService medicalDiseaseService;
    @Autowired
    private DiseaseService diseaseService;
    @Autowired
    private MedicalRecordService medicalRecordService;
    @Override
    public String initCaseNumber() {
        return KeyUtil.createUniqueKey();
    }

    @Override
    public RegisterVO findVOById(String caseNumber) {
        RegisterVO registerVO = new RegisterVO();
        BeanUtils.copyProperties(findById(caseNumber),registerVO);
        return registerVO;
    }

    @Override
    public Register findById(String caseNumber) {
        return registerMapper.selectOne(new QueryWrapper<Register>().eq("case_number",caseNumber));
    }

    @Override
    public void createRegister(Register register) {
        registerMapper.insert(register);
    }

    @Override
    public DataVO findRegistrationsByDoctorId(Integer doctor_id,Integer page,Integer limit,Integer visit_state) {
        QueryWrapper<Register> morning_queryWrapper = new QueryWrapper<>();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Register> itemsIPage = new Page<>(page, limit);
        IPage<Register> result = registerMapper.selectPage(itemsIPage, morning_queryWrapper.eq("user_id",doctor_id).eq("visit_date",today).eq("visit_state",visit_state));
        List<Register> list = result.getRecords();
        dataVO.setCount(result.getTotal());
        if(visit_state==1){
            List<WaitingItemVO> waitingItemVOList = list.stream().map(e -> new WaitingItemVO(
                    e.getCaseNumber(),
                    e.getRealName(),
                    constantItemService.findNameById(e.getGender()),
                    e.getAge(),
                    e.getRegistId()
            )).collect(Collectors.toList());
            dataVO.setData(waitingItemVOList);
            return dataVO;
        }else if(visit_state==2){

            List<DoneItemVO> doneItemVOList = new ArrayList<>();
            for(Register r: list){
                DoneItemVO doneItemVO = new DoneItemVO();
                doneItemVO.setCaseNumber(r.getCaseNumber());
                doneItemVO.setRealName(r.getRealName());
                doneItemVO.setAge(r.getAge());
                doneItemVO.setRegistId(r.getRegistId());
                doneItemVO.setGender(constantItemService.findNameById(r.getGender()));
                doneItemVO.setDiagnosis(transformToDiagnosis(r));
                doneItemVO.setMedical_id(medicalRecordService.findRecordByRegistId(r.getRegistId()).getMedicalId());
                doneItemVOList.add(doneItemVO);
            }
            dataVO.setData(doneItemVOList);
            return dataVO;
        }else {
            return null;
        }



    }

    @Override
    public void updateVisitState(Integer id, Integer visit_state) {
        UpdateWrapper<Register> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("regist_id",id).set("visit_state",visit_state);
        registerMapper.update(null,updateWrapper);
    }

    public String transformToDiagnosis(Register r){
        List<Medicaldisease> medicaldiseaseList = medicalDiseaseService.findDiagnosisByRegistId(r.getRegistId());
        String diagnosis = "";
        for(Medicaldisease m:medicaldiseaseList){
            diagnosis = diagnosis + diseaseService.findById(m.getDiseaseId()).getDiseaseName()+"&";
        }
        diagnosis = diagnosis.substring(0,diagnosis.length()-1);
        return diagnosis;
    }
}
