package com.zthx.npj.net.been;

import java.util.HashMap;

public class CartOrderOneBean {
    private String user_id;
    private String token;
    private String cart_id;
    private String address_id;
    private String pay_code;
    private String item_id;



    public String getUser_id() {
        return user_id;
    }


    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
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

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getPay_code() {
        return pay_code;
    }

    public void setPay_code(String pay_code) {
        this.pay_code = pay_code;
    }



    private HashMap<String,String> type;
    private HashMap<String,String> remark;
    private HashMap<String,String> ziti_id;


    public HashMap<String, String> getType() {
        return type;
    }

    public void setType(HashMap<String, String> type) {
        this.type = type;
    }

    public HashMap<String, String> getRemark() {
        return remark;
    }

    public void setRemark(HashMap<String, String> remark) {
        this.remark = remark;
    }

    public HashMap<String, String> getZiti_id() {
        return ziti_id;
    }

    public void setZiti_id(HashMap<String, String> ziti_id) {
        this.ziti_id = ziti_id;
    }
}
