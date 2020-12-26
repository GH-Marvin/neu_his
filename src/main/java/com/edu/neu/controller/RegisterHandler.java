package com.edu.neu.controller;

import com.edu.neu.entity.Register;
import com.edu.neu.entity.Registlevel;
import com.edu.neu.service.ConstantItemService;
import com.edu.neu.service.DepartmentService;
import com.edu.neu.service.RegistLevelService;
import com.edu.neu.service.RegisterService;
import com.edu.neu.util.ResultUtil;
import com.edu.neu.vo.ConstantVO;
import com.edu.neu.vo.RegisterVO;
import com.edu.neu.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/register")
public class RegisterHandler {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private RegistLevelService registLevelService;
    @Autowired
    private ConstantItemService constantItemService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    @GetMapping("/initConstant")
    public ResultVO initConstant() {
        Map<String, List<ConstantVO>> map = new HashMap<>();
        //常值map加入初始化的病历号
        ConstantVO constantVO = new ConstantVO();
        constantVO.setId(0);
        constantVO.setName(registerService.initCaseNumber());
        List<ConstantVO> caseNum = new ArrayList<>();
        caseNum.add(constantVO);
        map.put("caseNumber",caseNum);
        //map加入初始化号别列表
        List<ConstantVO> registlevelList = new ArrayList<>();
        for(Registlevel rl : registLevelService.findAll()){
            ConstantVO registLevel = new ConstantVO();
            registLevel.setId(rl.getId());
            registLevel.setName(rl.getRegistName()+" ¥"+rl.getRegistFee());
            registlevelList.add(registLevel);
        }
        map.put("registLevel",registlevelList);
        //map加入初始化性别列表
        List<ConstantVO> genderList = constantItemService.findByTypeId(7).stream().map(e -> new ConstantVO(
                e.getId(),
                e.getConstantName()
                )).collect(Collectors.toList());
        map.put("gender",genderList);
        //map加入初始化科室列表
        List<ConstantVO> departmentList = departmentService.findAll().stream().map(e -> new ConstantVO(
                e.getId(),
                e.getDeptName() + " " + e.getDeptCode()
        )).collect(Collectors.toList());
        map.put("dept",departmentList);
        //map加入初始化支付方式列表
        List<ConstantVO> payList = constantItemService.findByTypeId(5).stream().map(e -> new ConstantVO(
                e.getId(),
                e.getConstantName()
        )).collect(Collectors.toList());
        map.put("pay",payList);
        //返回map
        return ResultUtil.success("数据接口正常！",map);
    }


    @GetMapping("/findById/{record_id}")
    public ResultVO findById(@PathVariable("record_id") String record_id) {
        if(record_id!=null&&record_id!=""){
            Register register = registerService.findById(record_id);
            if(register!=null){
                RegisterVO registerVO = new RegisterVO();
                BeanUtils.copyProperties(register,registerVO);
                registerVO.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(register.getBirthDate()));
                return ResultUtil.success("查询成功！",registerVO);
            }else {
                return ResultUtil.fail("未找到该患者信息！");
            }
        }else {
            return ResultUtil.fail("输入为空！");
        }

    }

}
