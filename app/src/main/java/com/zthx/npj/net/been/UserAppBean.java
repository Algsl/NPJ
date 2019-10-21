package com.zthx.npj.net.been;

public class UserAppBean {
    private String user_id;
    private String token;
    private long type;
    private long app_level;

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

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public long getApp_level() {
        return app_level;
    }

    public void setApp_level(long app_level) {
        this.app_level = app_level;
    }
}
