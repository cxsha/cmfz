package com.baizhi.service;

import com.baizhi.entity.Pro;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Map<String, Object> selectStatisticsCount();
    public List<Pro> selectProvince(String sex);
}
