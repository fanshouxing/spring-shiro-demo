/*
 * Copyright (c) 2016. 上海趣医网络技术服务有限公司 版权所有
 * Shanghai QuYi Network Technology Co., Ltd. All Rights Reserved.
 *
 * This is NOT a freeware,use is subject to license terms.
 */

package com.quyiyuan.web;

import com.quyiyuan.service.ShiroService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Fsx on 2016/12/6.
 */

@Slf4j
@RequestMapping(value = "/shiro")
@Controller
public class ShiroController {

    @Autowired
    private ShiroService shiroService;

    @RequestMapping(value = "/testAnnotation")
    public String testMethod(){
        shiroService.testMethod();
        return "redirect:/list.jsp";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){

        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()){
            //把用户名和密码封装为UsernamePasswordToekn 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);

            //remeberme
            token.setRememberMe(true);
            try {
                //执行登录
                currentUser.login(token);
            }catch (AuthenticationException ae){
                log.error("登录失败:"+ae.getMessage());
            }
        }
        return "redirect:/list.jsp";

    }


}
