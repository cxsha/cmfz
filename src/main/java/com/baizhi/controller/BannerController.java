package com.baizhi.controller;

import com.baizhi.dto.BannerPageDto;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    BannerService bannerService;

    @RequestMapping("/queryByPage")
    public BannerPageDto queryByPage(int page, int rows){
        return bannerService.queryByPage(page, rows);
    }

    @RequestMapping("addBanner")
    public void addBanner(Banner banner){
        bannerService.addBanner(banner);
    }

    @RequestMapping("deleteBanner")
    public void deleteBanner(Banner banner){
        bannerService.deleteBanner(banner);
    }

    @RequestMapping("updateBanner")
    public void updateBanner(Banner banner){
        bannerService.updateBanner(banner);
    }
}
