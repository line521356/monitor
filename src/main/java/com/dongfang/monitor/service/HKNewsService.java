package com.dongfang.monitor.service;

import com.dongfang.monitor.model.hk_news.CrawlerCount;

import java.util.List;

public interface HKNewsService {

    List <CrawlerCount> findCrawlerCountInCurrentDay();

    List <String> findAllUrlForm();
}
