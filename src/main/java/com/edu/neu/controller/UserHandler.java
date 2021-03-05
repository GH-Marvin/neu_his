package com.edu.neu.controller;

import com.edu.neu.entity.User;
import com.edu.neu.service.DepartmentService;
import com.edu.neu.service.UserService;
import com.edu.neu.util.ResultUtil;
import com.edu.neu.vo.ResultVO;
import com.edu.neu.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultVO<String> login(@RequestBody UserVO userVO) {
        Subject subject = SecurityUtils.getSubject();
        String username = userVO.getUsername();
        String password = userVO.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            subject.getSession().setAttribute("user", user);
            Map<String,String> map = new HashMap<>();
            if(user.getPermission().equals("doctorMenu")){
                map.put("page","doctorMenu");
                map.put("registerId",user.getId().toString());
                map.put("realname",user.getRealname());
                map.put("dept",departmentService.findById(user.getDeptId()).getDeptName());
                map.put("deptId" , user.getDeptId().toString());
                return ResultUtil.success("登录成功！即将跳转至医生界面...",map);

            }else if(user.getPermission().equals("patientMenu")){
                map.put("page","patientMenu");
                map.put("registerId",user.getId().toString());
                map.put("realname",user.getRealname());
                map.put("dept","挂号");
                return ResultUtil.success("登录成功！即将跳转至挂号员界面...",map);

            }else {
                return ResultUtil.fail("暂时无权限访问进一步页面！");
            }

        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return ResultUtil.fail("账号或密码有误！");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return ResultUtil.fail("账号或密码有误！");
        }

    }
    @GetMapping("/unauth")
    @ResponseBody
    public String unauth() {
        return "未授权,无法访问!";
    }


    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

}
