package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void selectByPhone(User user) {
        User one = userMapper.selectOne(user);
        if (one==null){
            throw new RuntimeException("此账号不存在！");
        }else{
            if(!one.getPassword().equals(one.getSalt()+user.getPassword())){
               throw new RuntimeException("用户密码错误！");
            }

        }
    }
}
