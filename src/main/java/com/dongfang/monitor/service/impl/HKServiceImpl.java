package com.dongfang.monitor.service.impl;

import com.dongfang.monitor.dao.hk_news.CrawlerCountRepository;
import com.dongfang.monitor.model.hk_news.CrawlerCount;
import com.dongfang.monitor.service.HKNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HKServiceImpl implements HKNewsService {

    @Resource
    CrawlerCountRepository crawlerCountRepository;

    @Override
    public List<CrawlerCount> findCrawlerCountInCurrentDay() {
        return crawlerCountRepository.findByCrawlerdate("20180629");
    }

    @Override
    public List<String> findAllUrlForm() {
        return crawlerCountRepository.findAllUrlForm();
    }
}
