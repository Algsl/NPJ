package com.zthx.npj.net.been;

/**
 * Created by huangxin on 2019/6/14.
 */

public class UploadCompanyBean {

    private String user_id;
    private String token;
    private String company_name;
    private String company_desc;
    private String company_type;

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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_desc() {
        return company_desc;
    }

    public void setCompany_desc(String company_desc) {
        this.company_desc = company_desc;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    public String getBussiness_license() {
        return bussiness_license;
    }

    public void setBussiness_license(String bussiness_license) {
        this.bussiness_license = bussiness_license;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    private String bussiness_license;
    private String authorization;
}
