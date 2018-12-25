package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
public class Album implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Excel(name = "编号",needMerge = true)
    private Integer id;
    @Excel(name = "标题",needMerge = true,width = 20)
    private String title;
    @Excel(name = "集数",needMerge = true)
    private Integer count;
    @Excel(name = "封面",type = 2,needMerge = true)
    private String coverImg;
    @Excel(name = "评分",needMerge = true)
    private String score;
    @Excel(name = "作者",needMerge = true,width = 20)
    private String author;
    @Excel(name = "播音",needMerge = true)
    private String broadcast;
    @Excel(name = "内容简介",needMerge = true,width = 50)
    private String brief;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布日期",format = "YYYY年MM月dd日",width = 20,needMerge = true)
    private Date pubDate;
    @ExcelCollection(name = "专辑章节")
    private List<Chapter> children;
}
