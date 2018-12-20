package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @RequestMapping("login")
    public String login(HttpSession session, Admin admin, String enCode){
        try {
            String code = (String) session.getAttribute("code");
            if (!code.equalsIgnoreCase(enCode)){
                throw new RuntimeException("验证码错误！");
            }
            Admin admin1 = adminService.selectAdminOne(admin);
            session.setAttribute("admin1", admin1);
            return "ok";
        }catch (Exception e){
            String str = new String(e.getMessage());
            return str;
        }
    }
}
