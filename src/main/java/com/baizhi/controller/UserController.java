package com.baizhi.controller;

import com.baizhi.entity.Pro;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("count")
    public Map<String, Object> selectCount(){
        Map<String, Object> stringObjectMap = userService.selectStatisticsCount();
        return stringObjectMap;
    }

    @RequestMapping("manProvince")
    public List<Pro> selectManProvince(){
        List<Pro> list = userService.selectProvince("男");
        return list;
    }

    @RequestMapping("womanProvince")
    public List<Pro> selectWomanProvince(){
        List<Pro> list = userService.selectProvince("女");
        return list;
    }
}
