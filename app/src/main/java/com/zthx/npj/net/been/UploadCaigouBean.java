package com.zthx.npj.net.been;

/**
 * Created by huangxin on 2019/6/15.
 */

public class UploadCaigouBean {

    private String user_id;
    private String token;
    private String name;
    private String purchase_identity;
    private String stall_name;
    private String location;
    private String address;
    private String business_license;
    private String cert_id;

    public String getCert_id() {
        return cert_id;
    }

    public void setCert_id(String cert_id) {
        this.cert_id = cert_id;
    }

    public String getStall_name() {
        return stall_name;
    }

    public void setStall_name(String stall_name) {
        this.stall_name = stall_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusiness_license() {
        return business_license;
    }

    public void setBusiness_license(String business_license) {
        this.business_license = business_license;
    }

    public String getStall_image() {
        return stall_image;
    }

    public void setStall_image(String stall_image) {
        this.stall_image = stall_image;
    }

    public String getBusiness_card() {
        return business_card;
    }

    public void setBusiness_card(String business_card) {
        this.business_card = business_card;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    private String stall_image;
    private String business_card;
    private String company_name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurchase_identity() {
        return purchase_identity;
    }

    public void setPurchase_identity(String purchase_identity) {
        this.purchase_identity = purchase_identity;
    }
}
