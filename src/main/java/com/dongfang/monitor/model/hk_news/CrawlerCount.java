package com.dongfang.monitor.model.hk_news;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "crawler_count")
public class CrawlerCount implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "news_id")
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
