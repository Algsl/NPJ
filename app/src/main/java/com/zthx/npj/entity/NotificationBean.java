package com.zthx.npj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class NotificationBean implements Serializable {
    private String title;
    private String content;
    private Date nowTime;
    public NotificationBean(String title,String content,Date nowTime){
        this.title=title;
        this.content=content;
        this.nowTime=nowTime;
    }

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}