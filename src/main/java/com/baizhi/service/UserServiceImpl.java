package com.baizhi.service;

import com.baizhi.entity.Pro;
import com.baizhi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String, Object> selectStatisticsCount() {
        HashMap<String, Object> map = new HashMap<>();
        int[] ints = new int[]{7,15,30,90,180,360};
        int[] ints1 = new int[6];
        for (int i = 0; i < ints.length; i++) {
            Integer integer = userMapper.selectStatisticsCount(ints[i]);
            ints1[i] = integer;
        }
        System.out.println(ints1);
        map.put("data",ints1);
        map.put("intervals",new String[]{"7天", "15天", "30天", "90天", "半年", "一年"});
        return map;

    }

    @Override
    public List<Pro> selectProvince(String sex) {
        List<Pro> list = userMapper.selectProvince(sex);
        return list;
    }
}
