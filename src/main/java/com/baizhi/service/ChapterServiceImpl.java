package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterMapper chapterMapper;

    @Autowired
    AlbumMapper albumMapper;

    @Override
    public void addChapter(Chapter chapter) {
        String replace = UUID.randomUUID().toString().replace("-", "");
        chapter.setId(replace);
        chapterMapper.insert(chapter);
        Album album = new Album();
        album.setId(chapter.getAlbumId());
        album = albumMapper.selectOne(album);
        Chapter chapter1 = new Chapter();
        chapter1.setAlbumId(chapter.getAlbumId());
        int i = chapterMapper.selectCount(chapter1);
        album.setCount(i);
        albumMapper.updateByPrimaryKey(album);
    }
}
