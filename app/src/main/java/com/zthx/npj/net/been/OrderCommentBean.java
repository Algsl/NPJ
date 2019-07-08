package com.zthx.npj.net.been;

public class OrderCommentBean {
    private String user_id;
    private String token;
    private String order_id;
    private String content;
    private String img;
    private String goods_star;
    private String logistics_star;
    private String service_star;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGoods_star() {
        return goods_star;
    }

    public void setGoods_star(String goods_star) {
        this.goods_star = goods_star;
    }

    public String getLogistics_star() {
        return logistics_star;
    }

    public void setLogistics_star(String logistics_star) {
        this.logistics_star = logistics_star;
    }

    public String getService_star() {
        return service_star;
    }

    public void setService_star(String service_star) {
        this.service_star = service_star;
    }
}
