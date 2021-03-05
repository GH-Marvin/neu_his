package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.edu.neu.entity.Invoice;
import com.edu.neu.entity.Patientcosts;
import com.edu.neu.enums.PatientCostsStatusEnum;
import com.edu.neu.form.PaymentForm;
import com.edu.neu.mapper.PatientCostsMapper;
import com.edu.neu.service.PatientCostsService;
import com.edu.neu.service.PatientService;
import com.edu.neu.util.StateUtil;
import com.edu.neu.vo.DataVO;
import com.edu.neu.vo.PatientCostsVO;
import com.edu.neu.vo.PrescriptionDetailsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientCostsServiceImpl implements PatientCostsService {
    @Resource
    private PatientCostsMapper patientCostsMapper;

    @Resource
    private PatientService patientService;
    @Override
    public void addPatientCosts(Patientcosts patientcosts) {
        patientCostsMapper.insert(patientcosts);
    }

    @Override
    public void deletePatientCostsByUniqueInfo(Integer regist_id , Integer dept_id , Integer create_oper_id , Integer item_type) {
        patientCostsMapper.delete(new QueryWrapper<Patientcosts>()
                .eq("regist_id" , regist_id)
                .eq("dept_id" , dept_id)
                .eq("create_oper_id" , create_oper_id)
                .eq("item_type" , item_type));
    }

    @Override
    public DataVO<PatientCostsVO> findAllByCaseNumber(String caseNumber , Integer item_type ,Integer state) {
        DataVO<PatientCostsVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        List<Patientcosts> list = patientCostsMapper.selectList(new QueryWrapper<Patientcosts>().eq("case_number" , caseNumber).eq("item_type" , item_type).eq("state" , state));
        if(list == null) return null;
        List<PatientCostsVO> patientCostsVOList = new ArrayList<>();
        for(Patientcosts pc : list) {
            PatientCostsVO patientCostsVO = new PatientCostsVO();
            BeanUtils.copyProperties(pc , patientCostsVO);
            patientCostsVO.setRealName(patientService.findByCaseNumber(caseNumber).getRealName());
            patientCostsVO.setPrescriptionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pc.getCreateTime()));
            if(pc.getPayTime() == null) {
                patientCostsVO.setPayTime("");
            }else {
                patientCostsVO.setPayTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pc.getPayTime()));
            }
            patientCostsVO.setPrescriptionState("已开立");
            patientCostsVO.setState(StateUtil.getStateByCode(state));
            patientCostsVOList.add(patientCostsVO);
        }
        dataVO.setCount(Long.valueOf(patientCostsVOList.size()));
        dataVO.setData(patientCostsVOList);
        return dataVO;


    }

    @Override
    public DataVO<PatientCostsVO> findAllByCaseNumber(String caseNumber, Integer state) {
        DataVO<PatientCostsVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        List<Patientcosts> list = patientCostsMapper.selectList(new QueryWrapper<Patientcosts>().eq("case_number" , caseNumber).eq("state" , state));
        if(list == null) return null;
        List<PatientCostsVO> patientCostsVOList = new ArrayList<>();
        for(Patientcosts pc : list) {
            PatientCostsVO patientCostsVO = new PatientCostsVO();
            BeanUtils.copyProperties(pc , patientCostsVO);
            patientCostsVO.setRealName(patientService.findByCaseNumber(caseNumber).getRealName());
            patientCostsVO.setPrescriptionTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pc.getCreateTime()));
            if(pc.getPayTime() == null) {
                patientCostsVO.setPayTime("");
            }else {
                patientCostsVO.setPayTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pc.getPayTime()));
            }
            patientCostsVO.setPrescriptionState("已开立");
            patientCostsVO.setState(StateUtil.getStateByCode(state));
            patientCostsVOList.add(patientCostsVO);
        }
        dataVO.setCount(Long.valueOf(patientCostsVOList.size()));
        dataVO.setData(patientCostsVOList);
        return dataVO;
    }

    @Override
    public void update(Patientcosts patientcosts) {
        UpdateWrapper<Patientcosts> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("pc_id" , patientcosts.getPcId());
        patientCostsMapper.update(patientcosts , updateWrapper);
    }

    @Override
    public Patientcosts findById(Integer id) {
        return patientCostsMapper.selectOne(new QueryWrapper<Patientcosts>().eq("pc_id" , id));
    }

}
