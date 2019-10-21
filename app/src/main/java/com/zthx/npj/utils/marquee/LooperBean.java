package com.zthx.npj.utils.marquee;

public class LooperBean {
    private String headImg;
    private String msg;

    public LooperBean(){

    }

    public LooperBean(String headImg,String msg){
        this.headImg=headImg;
        this.msg=msg;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
