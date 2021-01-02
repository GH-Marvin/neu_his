package com.edu.neu.realm;


import com.edu.neu.entity.User;
import com.edu.neu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权-权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取当前登录用户的信息
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        //设置角色
        Set<String> roles = new HashSet<>();
        roles.add(user.getRole());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        //设置权限
        info.addStringPermission(user.getPermission());

        return info;
    }


    /**
     * 验证-角色
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByUserName(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
            /*
            SimpleAuthenticationInfo传参(对象本身，对象包含的密码，当前名字)
             */
        }
        return null;
    }
}
