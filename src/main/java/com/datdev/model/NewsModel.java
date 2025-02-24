package com.datdev.model;

import java.sql.Timestamp;

public class NewsModel extends AbstractModel<NewsModel> {

    private String title;
    private String thumbNail;
    private String shortDescription;
    private String content;
    private Long categoryId;
    private String categoryCode;


    public NewsModel() {
        super();
        // TODO Auto-generated constructor stub
    }

    public NewsModel(String title, String thumbnNail, String shortDescription, String content, Long categoryId) {
        super();
        this.title = title;
        this.thumbNail = thumbnNail;
        this.shortDescription = shortDescription;
        this.content = content;
        this.categoryId = categoryId;
    }


    public NewsModel(long id, Timestamp createDate, Timestamp modifireDate, String createBy, String modifireBy) {
        super(id, createDate, modifireDate, createBy, modifireBy);
        // TODO Auto-generated constructor stub
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryid() {
        return categoryId;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryId = categoryid;
    }

    public void setPassWord(String string) {
        // TODO Auto-generated method stub

    }


    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
