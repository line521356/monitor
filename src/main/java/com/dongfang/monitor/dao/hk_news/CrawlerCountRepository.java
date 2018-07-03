package com.dongfang.monitor.dao.hk_news;

import com.dongfang.monitor.model.hk_news.CrawlerCount;
import com.dongfang.monitor.support.dao.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrawlerCountRepository extends BaseRepository<CrawlerCount, Long>,JpaSpecificationExecutor<CrawlerCount> {

    List<CrawlerCount> findByCrawlerdate(String date);

    @Query("select distinct c.urlfrom from CrawlerCount c")
    List <String> findAllUrlForm();
}
