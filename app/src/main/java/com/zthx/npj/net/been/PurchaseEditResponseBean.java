package com.zthx.npj.net.been;

import java.util.ArrayList;

public class PurchaseEditResponseBean extends BaseReponseBean{
    public class DataBean{
        private String user_id;
        private String update_time;
        private String unit;
        private String title;
        private String status;
        private String spec;
        private String remark;
        private String min_price;
        private String max_price;
        private String lng;
        private String lat;
        private String is_top;
        private ArrayList<String> img;
        private String id;
        private String hits;
        private String create_time;
        private ArrayList<String> content;
        private String city;
        private String baojia_status;
        private String amount;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getIs_top() {
            return is_top;
        }

        public void setIs_top(String is_top) {
            this.is_top = is_top;
        }

        public ArrayList<String> getImg() {
            return img;
        }

        public void setImg(ArrayList<String> img) {
            this.img = img;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public ArrayList<String> getContent() {
            return content;
        }

        public void setContent(ArrayList<String> content) {
            this.content = content;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getBaojia_status() {
            return baojia_status;
        }

        public void setBaojia_status(String baojia_status) {
            this.baojia_status = baojia_status;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
