package com.baizhi.mapper;

import com.baizhi.entity.Pro;
import com.baizhi.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    public Integer selectStatisticsCount(Integer day);
    public List<Pro> selectProvince(String sex);
}
