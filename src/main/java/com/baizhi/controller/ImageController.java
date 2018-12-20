package com.baizhi.controller;

import com.baizhi.conf.CreateValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/image")
public class ImageController {

    @RequestMapping("/code")
    public void code(HttpSession session,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        CreateValidateCode createValidateCode = new CreateValidateCode();
        String code = createValidateCode.getCode();
        session.setAttribute("code",code);
        createValidateCode.write(httpServletResponse.getOutputStream());
    }
}
