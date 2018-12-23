package com.baizhi.service;

import com.baizhi.dto.AlbumPageDto;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumMapper albumMapper;

    @Autowired
    ChapterMapper chapterMapper;

    @Override
    public AlbumPageDto queryByPage(int curPage, int pageSize) {
        AlbumPageDto albumPageDto = new AlbumPageDto();
        albumPageDto.setTotal(albumMapper.selectTotalCount());
        albumPageDto.setRows(albumMapper.selectByPage(curPage,pageSize));
        return albumPageDto;
    }

    @Override
    public Album selectOneAlbum(Integer id) {
        Album album = new Album();
        album.setId(id);
        Album album1 = albumMapper.selectOne(album);
        return album1;
    }

    @Override
    public void addAlbum(Album album) {
        albumMapper.insert(album);
        Chapter chapter = new Chapter();
        chapter.setAlbumId(album.getId());
        int i = chapterMapper.selectCount(chapter);
        album.setCount(i);
        albumMapper.updateByPrimaryKey(album);

    }
}
