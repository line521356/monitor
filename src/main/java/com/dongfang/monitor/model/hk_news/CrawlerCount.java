package com.dongfang.monitor.model.hk_news;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "crawler_count")
public class CrawlerCount implements Serializable {

    @Id
    @GeneratedValue
    private Long newsId;

    @Column(name = "crawlerdate")
    private String crawlerdate;

    @Column(name = "crawlerhour")
    private String crawlerhour;

    @Column(name = "urlfrom")
    private String urlfrom;

    @Column(name = "totalnum")
    private Long totalnum;

    @Column(name = "contentfitnum")
    private Long contentfitnum;

    @Column(name = "imgfitnum")
    private Long imgfitnum;

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getCrawlerdate() {
        return crawlerdate;
    }

    public void setCrawlerdate(String crawlerdate) {
        this.crawlerdate = crawlerdate;
    }

    public String getCrawlerhour() {
        return crawlerhour;
    }

    public void setCrawlerhour(String crawlerhour) {
        this.crawlerhour = crawlerhour;
    }

    public String getUrlfrom() {
        return urlfrom;
    }

    public void setUrlfrom(String urlfrom) {
        this.urlfrom = urlfrom;
    }

    public Long getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Long totalnum) {
        this.totalnum = totalnum;
    }

    public Long getContentfitnum() {
        return contentfitnum;
    }

    public void setContentfitnum(Long contentfitnum) {
        this.contentfitnum = contentfitnum;
    }

    public Long getImgfitnum() {
        return imgfitnum;
    }

    public void setImgfitnum(Long imgfitnum) {
        this.imgfitnum = imgfitnum;
    }
}
