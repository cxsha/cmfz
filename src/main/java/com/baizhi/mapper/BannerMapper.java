package com.baizhi.mapper;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BannerMapper extends Mapper<Banner> {
    public Integer selectTotalCount();
    public List<Banner> selectByPage(@Param("curPage") int curPage, @Param("pageSize") int pageSize);
}
