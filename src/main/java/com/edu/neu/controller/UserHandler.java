package com.edu.neu.controller;

import com.edu.neu.entity.User;
import com.edu.neu.service.UserService;
import com.edu.neu.util.ResultUtil;
import com.edu.neu.vo.ResultVO;
import com.edu.neu.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private UserService userService;

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
        User user = userService.findByUserName(userVO.getUsername());
        if(user != null){
            if(userVO.getPassword().equals(user.getPassword())){
                if(user.getRoleId() == 2){
                    return ResultUtil.success("patientMenu");
                }
                else if(user.getRoleId() == 3){
                    return ResultUtil.success("doctorMenu");
                }else {
                    return ResultUtil.fail("暂时无权限访问进一步页面！");
                }
            }else {
                return ResultUtil.fail("账号或密码有误！");
            }
        }else {
            return ResultUtil.fail("没有该账户！");
        }
    }






}
