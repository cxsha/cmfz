package com.baizhi.service;

import com.baizhi.dto.BannerPageDto;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;
    @Override
    public BannerPageDto queryByPage(int curPage, int pageSize) {
        BannerPageDto dto = new BannerPageDto();
        dto.setTotal(bannerMapper.selectTotalCount());
        dto.setRows(bannerMapper.selectByPage(curPage,pageSize));
        return dto;
    }

    @Override
    public void addBanner(Banner banner) {
        bannerMapper.insert(banner);
    }

    @Override
    public void deleteBanner(Banner banner) {
        bannerMapper.delete(banner);
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerMapper.updateByPrimaryKey(banner);
    }
}
