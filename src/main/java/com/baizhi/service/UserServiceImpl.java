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
    public User selectUserOne(User user) {
        User user1 = new User();
        user1.setName(user.getName());
        User one = userMapper.selectOne(user1);
        if (one==null){
            throw new RuntimeException("此账号不存在！");
        }else{
            if(!one.getPassword().equals(user.getPassword())){
               throw new RuntimeException("密码输入错误！");
            }else {
                return one;
            }

        }
    }
}
