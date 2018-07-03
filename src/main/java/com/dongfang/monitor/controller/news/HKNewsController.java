package com.dongfang.monitor.controller.news;

import com.dongfang.monitor.service.HKNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hk_news")
public class HKNewsController {

    @Autowired
    HKNewsService hkNewsService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("currentCountList",hkNewsService.findCrawlerCountInCurrentDay());
        model.addAttribute("urlFormList",hkNewsService.findAllUrlForm());
        return "sites/hk-news";
    }
}
