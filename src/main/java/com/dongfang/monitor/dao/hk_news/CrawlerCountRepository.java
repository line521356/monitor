package com.dongfang.monitor.dao.hk_news;

import com.dongfang.monitor.model.hk_news.CrawlerCount;
import com.dongfang.monitor.support.dao.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CrawlerCountRepository extends BaseRepository<CrawlerCount, Long>,JpaSpecificationExecutor<CrawlerCount> {
}
