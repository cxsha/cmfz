package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/poi")
public class POIController {
    @Autowired
    AlbumMapper albumMapper;
    @RequestMapping("testExport")
    public void testExport(HttpServletRequest request, HttpServletResponse response) {
        List<Album> albums = albumMapper.selectByPage(1, albumMapper.selectTotalCount());
        String realPath = request.getSession().getServletContext().getRealPath("/image");
        for (Album album : albums) {
            album.setCoverImg(realPath+"/"+album.getCoverImg());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑章节汇总", "专辑表"),
                Album.class, albums);
       /* try {
            workbook.write(new FileOutputStream(new File("D:/album.xls")));
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }*/

        response.setHeader("Content-Disposition", "attachment;fileName=album.xls");
        response.setContentType("application/vnd.ms-excel");
        try {
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            if (out == null) {
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
