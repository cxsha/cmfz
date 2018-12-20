package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("login")
    public String login(HttpSession session,User user, String enCode){
        try {
            String code = (String) session.getAttribute("code");
            if (!code.equalsIgnoreCase(enCode)){
                throw new RuntimeException("验证码错误！");
            }
            User user1 = userService.selectUserOne(user);
            session.setAttribute("user1",user1);
            return "ok";
        }catch (Exception e){
            String str = new String(e.getMessage());
            return str;
        }
    }
}
