package com.edu.neu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.neu.dto.PrescriptionDTO;
import com.edu.neu.entity.Drugs;
import com.edu.neu.entity.Prescriptiondetailed;
import com.edu.neu.mapper.PrescriptionDetailedMapper;
import com.edu.neu.service.DrugsService;
import com.edu.neu.service.PrescriptionDetailedService;
import com.edu.neu.service.PrescriptionService;
import com.edu.neu.service.RegisterService;
import com.edu.neu.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionDetailedServiceImpl implements PrescriptionDetailedService {
    @Resource
    private PrescriptionDetailedMapper prescriptionDetailedMapper;


    @Autowired
    private DrugsService drugsService;
    @Resource
    private RegisterService registerService;
    @Resource
    private PrescriptionService prescriptionService;

    @Override
    public Prescriptiondetailed findByUniqueInfo(Integer prescriptionId, Integer drugsId) {
        Prescriptiondetailed prescriptiondetailed = prescriptionDetailedMapper.selectOne(new QueryWrapper<Prescriptiondetailed>().eq("prescription_id",prescriptionId).eq("drugs_id",drugsId));
        return prescriptiondetailed;
    }

    @Override
    public void save(PrescriptionDTO prescriptionDTO) {
            Prescriptiondetailed prescriptiondetailed = new Prescriptiondetailed();
            BeanUtils.copyProperties(prescriptionDTO,prescriptiondetailed);
            prescriptionDetailedMapper.insert(prescriptiondetailed);
    }

    @Override
    public void deleteByPrescriptionId(Integer id) {
        prescriptionDetailedMapper.delete(new QueryWrapper<Prescriptiondetailed>().eq("prescription_id",id));
    }

    @Override
    public void deleteByPdId(Integer id) {
        prescriptionDetailedMapper.delete(new QueryWrapper<Prescriptiondetailed>().eq("pd_id",id));

    }

    @Override
    public DataVO<PrescriptionDetailsVO> findAllByPrescriptionId(Integer id , Integer page, Integer limit) {
        DataVO<PrescriptionDetailsVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Prescriptiondetailed> itemsIPage = new Page<>(page, limit);
        Integer prescription_id = id;
        IPage<Prescriptiondetailed> result = prescriptionDetailedMapper.selectPage(itemsIPage, new QueryWrapper<Prescriptiondetailed>().eq("prescription_id",prescription_id));
        List<Prescriptiondetailed> list = result.getRecords();
        List<PrescriptionDetailsVO> prescriptionDetailsVOList = new ArrayList<>();
        for(Prescriptiondetailed p:list){
            PrescriptionDetailsVO prescriptionDetailsVO = new PrescriptionDetailsVO();
            BeanUtils.copyProperties(p, prescriptionDetailsVO);
            Drugs drugs = drugsService.findById(p.getDrugsId());
            BeanUtils.copyProperties(drugs, prescriptionDetailsVO);
            prescriptionDetailsVOList.add(prescriptionDetailsVO);
        }
        dataVO.setCount(result.getTotal());
        dataVO.setData(prescriptionDetailsVOList);
        return dataVO;
    }

    @Override
    public List<Prescriptiondetailed> findAllByPrescriptionId(Integer id) {
        return prescriptionDetailedMapper.selectList(new QueryWrapper<Prescriptiondetailed>().eq("prescription_id" , id));
    }

    @Override
    public Double calculateTotalPrice(Integer id) {
        List<Prescriptiondetailed> list = prescriptionDetailedMapper.selectList(new QueryWrapper<Prescriptiondetailed>().eq("prescription_id",id));
        Double total_price = 0.00;
        if(list!=null){
            for(Prescriptiondetailed p:list){
                Double price = drugsService.findById(p.getDrugsId()).getDrugsPrice();
                total_price += price*p.getAmount();
            }
        }
        return total_price;
    }

//    @Override
//    public DataVO<PatientCostsVO> findPaymentDetailsVOById(String caseNumber , String name) {
//        List<Register> registerList = registerService.findByCaseNumberAndVisitState(caseNumber , RegisterStatusEnum.PAY.getCode());
//        List<Prescription> prescriptionList = new ArrayList<>();
//        if(registerList != null) {
//            for(Register register : registerList) {
//                List<Prescription> temp = prescriptionService.findByRegistId(register.getRegistId());
//                if(temp != null) {
//                    prescriptionList.addAll(temp);
//                }
//            }
//        }
//        List<Prescriptiondetailed> list = new ArrayList<>();
//        if(prescriptionList.size() > 0) {
//            for(Prescription p : prescriptionList) {
//                list.addAll(prescriptionDetailedMapper.selectList(new QueryWrapper<Prescriptiondetailed>().eq("prescription_id", p.getPrescriptionId())));
//            }
//        }
//        if(list.size() > 0) {
//            DataVO<PatientCostsVO> dataVO = new DataVO<>();
//            dataVO.setCode(0);
//            dataVO.setMsg("");
//            List<PatientCostsVO> PaymentDetailsVOList = list.stream().map(e->new PatientCostsVO(
//                    caseNumber,
//                    name,
//                    drugsService.findById(e.getDrugsId()).getDrugsName(),
//                    drugsService.findById(e.getDrugsId()).getDrugsPrice(),
//                    e.getAmount(),
//                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(prescriptionService.findById(e.getPrescriptionId()).getPrescriptionTime()),
//                    PrescriptionStatusEnum.SUBMITTED.getMsg()
//            )).collect(Collectors.toList());
//            dataVO.setCount(Long.valueOf(list.size()));
//            dataVO.setData(PaymentDetailsVOList);
//            return dataVO;
//        }
//        return null;
//    }
}
