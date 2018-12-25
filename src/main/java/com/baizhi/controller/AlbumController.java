package com.baizhi.controller;

import com.baizhi.dto.AlbumPageDto;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @RequestMapping("albumQueryByPage")
    public AlbumPageDto queryByPage(int page, int rows){
        return albumService.queryByPage(page,rows);
    }

    @RequestMapping("addAlbum")
    public void addAlbum(Album album, MultipartFile image, HttpServletRequest request){
        //获取当前文件的保存路径
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String img = realPath + "image" ;
        File file = new File(img);
        if (!file.exists()){
            file.mkdir();
        }
        //重命名
        String originalFilename = image.getOriginalFilename();
        long time = new Date().getTime();
        String newName = time + originalFilename;
        File file1 = new File(realPath + "/" + newName);
        //上传
        try {
            image.transferTo(new File(img,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        album.setCoverImg(newName);
        albumService.addAlbum(album);
    }
}
