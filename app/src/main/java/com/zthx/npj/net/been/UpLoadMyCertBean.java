package com.zthx.npj.net.been;

/**
 * Created by huangxin on 2019/6/13.
 */

public class UpLoadMyCertBean {

    private String user_id;
    private String token;
    private String name;
    private String identity_number;
    private String card_face;
    private String card_back;
    private String cert_id;

    public String getCert_id() {
        return cert_id;
    }

    public void setCert_id(String cert_id) {
        this.cert_id = cert_id;
    }

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

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getCard_face() {
        return card_face;
    }

    public void setCard_face(String card_face) {
        this.card_face = card_face;
    }

    public String getCard_back() {
        return card_back;
    }

    public void setCard_back(String card_back) {
        this.card_back = card_back;
    }

    public String getCard_hand() {
        return card_hand;
    }

    public void setCard_hand(String card_hand) {
        this.card_hand = card_hand;
    }

    private String card_hand;

}
