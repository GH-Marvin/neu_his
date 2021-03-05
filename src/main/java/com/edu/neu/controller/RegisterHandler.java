package com.edu.neu.controller;

import com.edu.neu.entity.Patient;
import com.edu.neu.entity.Register;
import com.edu.neu.entity.Registlevel;
import com.edu.neu.entity.User;
import com.edu.neu.form.RegisterForm;
import com.edu.neu.service.*;
import com.edu.neu.util.ResultUtil;
import com.edu.neu.vo.ConstantVO;
import com.edu.neu.vo.PatientVO;
import com.edu.neu.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
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
    @Autowired
    private PatientService patientService;
    @Autowired
    private UserService userService;


    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    @GetMapping("/initConstant")
    public ResultVO initConstant() {
        Map<String, List<ConstantVO>> map = new HashMap<>();
        //常值map加入初始化的病历号
        Patient patient = new Patient();
        int id = patientService.getMaxId();
        String caseNum = String.valueOf(id+600601);
        patient.setCaseNumber(caseNum);
        patientService.insert(patient);
        ConstantVO constantVO = new ConstantVO();
        constantVO.setId(0);
        constantVO.setName(caseNum);
        List<ConstantVO> caseNumList = new ArrayList<>();
        caseNumList.add(constantVO);
        map.put("caseNumber",caseNumList);
        patientService.remove(caseNum);
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
        System.out.println(record_id);
        if(record_id != null){
            Patient patient = patientService.findByCaseNumber(record_id);
            if(patient != null){
                PatientVO patientVO = new PatientVO();
                BeanUtils.copyProperties(patient, patientVO);
                if(patient.getBirthDate()!=null) {
                    patientVO.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(patient.getBirthDate()));
                }else {
                    patientVO.setBirthDate("");
                }
                return ResultUtil.success("查询成功！", patientVO);
            }else {
                return ResultUtil.fail("查询失败，未找到该患者信息！");
            }
        }else {
            return ResultUtil.fail("查询失败，输入为空！");
        }

    }

    @GetMapping("/findDoctorsByDeptId/{dept_id}")
    @ResponseBody
    public ResultVO findDoctorsByDeptId(@PathVariable("dept_id") String dept_id){
        if(dept_id!=null&&dept_id!=""){
            List<User> userList = userService.findByDeptId(Integer.valueOf(dept_id));
            Map<String, List<ConstantVO>> map = new HashMap<>();
            if(userList!=null&&userList.size()!=0){
                List<ConstantVO> doctors = userList.stream().map(e->new ConstantVO(
                        e.getId(),
                        e.getRealname()
                )).collect(Collectors.toList());
                map.put("doctor",doctors);
                return ResultUtil.success("查询医生列表成功！",map);
            }else {
                return ResultUtil.fail("该科室暂无医生值班！");
            }
        }else {
            return null;
        }
    }


    @PostMapping("/submit")
    @ResponseBody
    public ResultVO submit(@RequestBody RegisterForm registerForm){
        Register register = new Register();
        BeanUtils.copyProperties(registerForm,register);
        String noon = registerForm.getNoon();
        if(noon.equals("0")){
            noon = "上午";
        }else {
            noon = "下午";
        }
        register.setNoon(noon);
        register.setRegistTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));
        register.setVisitState(1);
        register.setAgeType("岁");
        registerService.createRegister(register);
        patientService.saveOrUpdate(registerForm);
        return ResultUtil.success("挂号成功！",null);
    }
}
