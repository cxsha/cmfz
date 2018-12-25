package com.baizhi.service;

import com.baizhi.dto.AlbumPageDto;
import com.baizhi.entity.Album;

public interface AlbumService {
    public AlbumPageDto queryByPage(int curPage, int pageSize);
    public void addAlbum(Album album);
}
