package com.zthx.npj.net.been;

/**
 * Created by huangxin on 2019/6/15.
 */

public class UploadChengXinCertBean {

    private String user_id;
    private String token;
    private String price;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPay_code() {
        return pay_code;
    }

    public void setPay_code(String pay_code) {
        this.pay_code = pay_code;
    }

    private String pay_code;
}
