package com.zthx.npj.net.been;

import java.util.ArrayList;

/**
 * Created by huangxin on 2019/6/27.
 */

public class NeedDetailResponseBean extends BaseReponseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean{

        private long id;
        private String user_id;
        private String title;
        private ArrayList<String> img;
        private String spec;
        private String amount;
        private String unit;
        private String city;
        private String min_price;
        private String max_price;
        private ArrayList<String> content;
        private String create_time;
        private String update_time;
        private String remark;
        private String lat;
        private String lng;
        private int status;
        private String baojia_status;
        private String is_top;
        private String mobile;
        private String head_img;
        private String nick_name;
        private int level;
        private String certification;
        private int reputation;
        private String hits;
        private int user_count;

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getMin_price() {
            return min_price;
        }

        public void setMin_price(String min_price) {
            this.min_price = min_price;
        }

        public String getMax_price() {
            return max_price;
        }

        public void setMax_price(String max_price) {
            this.max_price = max_price;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getBaojia_status() {
            return baojia_status;
        }

        public void setBaojia_status(String baojia_status) {
            this.baojia_status = baojia_status;
        }

        public String getIs_top() {
            return is_top;
        }

        public void setIs_top(String is_top) {
            this.is_top = is_top;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ArrayList<String> getImg() {
            return img;
        }

        public void setImg(ArrayList<String> img) {
            this.img = img;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public ArrayList<String> getContent() {
            return content;
        }

        public void setContent(ArrayList<String> content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getCertification() {
            return certification;
        }

        public void setCertification(String certification) {
            this.certification = certification;
        }

        public int getReputation() {
            return reputation;
        }

        public void setReputation(int reputation) {
            this.reputation = reputation;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public int getUser_count() {
            return user_count;
        }

        public void setUser_count(int user_count) {
            this.user_count = user_count;
        }
    }
}
