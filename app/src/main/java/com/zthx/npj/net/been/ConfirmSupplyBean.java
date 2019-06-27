package com.zthx.npj.net.been;

/**
 * Created by huangxin on 2019/6/27.
 */

public class ConfirmSupplyBean {

    private String user_id;
    private String token;

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

    public String getSupply_id() {
        return supply_id;
    }

    public void setSupply_id(String supply_id) {
        this.supply_id = supply_id;
    }

    private String supply_id;
}
