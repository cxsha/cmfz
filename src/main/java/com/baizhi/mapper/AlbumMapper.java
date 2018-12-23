package com.baizhi.mapper;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbumMapper extends Mapper<Album> {
    public Integer selectTotalCount();
    public List<Album> selectByPage(@Param("curPage") int curPage, @Param("pageSize") int pageSize);
}
