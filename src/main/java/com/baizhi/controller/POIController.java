package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/poi")
public class POIController {
    @Autowired
    AlbumMapper albumMapper;
    @RequestMapping("testExport")
    public String testExport(HttpServletRequest request){
        List<Album> albums = albumMapper.selectByPage(1, albumMapper.selectTotalCount());
        String realPath = request.getSession().getServletContext().getRealPath("/image");
        for (Album album : albums) {
            album.setCoverImg(realPath+"/"+album.getCoverImg());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑章节汇总", "专辑表"),
                Album.class, albums);
        try {
            workbook.write(new FileOutputStream(new File("D:/album.xls")));
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
