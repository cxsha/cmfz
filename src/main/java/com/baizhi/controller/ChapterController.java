package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.util.Audioutil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.UUID;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @RequestMapping("addChapter")
    public void addChapter(Chapter chapter, MultipartFile audio, HttpServletRequest request) {
        DecimalFormat df = new DecimalFormat("#.##");
        long size = audio.getSize();
        String format = df.format(size / (1024 * 1024));
        //获取当前文件的保存路径
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String dir = realPath + "audio" ;
        File file = new File(dir);
        if (!file.exists()){
            file.mkdir();
        }
        //重命名
        String originalFilename = audio.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String newName = UUID.randomUUID().toString();
        newName = newName + "." + extension;
        //上传
        try {
            audio.transferTo(new File(dir,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        chapter.setUrl(newName);
        chapter.setSize(format+"MB");
        String duration = Audioutil.getDuration(new File(dir + "/" + newName)).toString();
        Double aDouble = Double.valueOf(duration)/60;
        String newDuration = String.format("%.2f", aDouble);
        chapter.setDuration(newDuration+"分钟");
        chapterService.addChapter(chapter);
    }

    @RequestMapping("down")
    public void down(String url, String title, HttpSession session, HttpServletResponse response){
        String realPath = session.getServletContext().getRealPath("/audio");
        String filePath = realPath + "/" + url;
        File file = new File(filePath);
        String extension = FilenameUtils.getExtension(url);
        String oldName = title + "." + extension;
        String encode = null;
        try {
            //String string = new String(oldName.getBytes("utf-8"),"utf-8");
            encode = URLEncoder.encode(oldName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + encode);
        response.setContentType("audio/mpeg");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
