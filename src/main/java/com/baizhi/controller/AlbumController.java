package com.baizhi.controller;

import com.baizhi.dto.AlbumPageDto;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @RequestMapping("albumQueryByPage")
    public AlbumPageDto queryByPage(int page, int rows){
        return albumService.queryByPage(page,rows);
    }

    @RequestMapping("selectOneAlbum")
    public Album selectOneAlbum(Integer id){
        Album album1 = albumService.selectOneAlbum(id);
        return album1;
    }

    @RequestMapping("addAlbum")
    public void addAlbum(Album album){
        albumService.addAlbum(album);
    }
}
