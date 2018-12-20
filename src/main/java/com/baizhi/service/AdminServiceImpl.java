package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin selectAdminOne(Admin admin) {
        Admin admin1 = new Admin();
        admin1.setName(admin.getName());
        Admin one = adminMapper.selectOne(admin1);
        if (one==null){
            throw new RuntimeException("此账号不存在！");
        }else{
            if(!one.getPassword().equals(admin.getPassword())){
               throw new RuntimeException("密码输入错误！");
            }else {
                return one;
            }

        }
    }
}
