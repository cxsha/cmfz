package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @RequestMapping("addChapter")
    public void addChapter(Chapter chapter) {
        chapterService.addChapter(chapter);
    }
}
