package com.zthx.npj.net.been;

import java.util.ArrayList;

public class BaojiaListResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private long user_id;
        private String title;
        private String img;
        private String spec;
        private String amount;
        private String unit;
        private String city;
        private String min_price;
        private String max_price;
        private String content;
        private String create_time;
        private String update_time;
        private String remark;
        private String lat;
        private String lng;
        private long status;
        private long baojia_status;
        private long is_top;
        private long hits;
        private long baojia_num;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
            this.status = status;
        }

        public long getBaojia_status() {
            return baojia_status;
        }

        public void setBaojia_status(long baojia_status) {
            this.baojia_status = baojia_status;
        }

        public long getIs_top() {
            return is_top;
        }

        public void setIs_top(long is_top) {
            this.is_top = is_top;
        }

        public long getHits() {
            return hits;
        }

        public void setHits(long hits) {
            this.hits = hits;
        }

        public long getBaojia_num() {
            return baojia_num;
        }

        public void setBaojia_num(long baojia_num) {
            this.baojia_num = baojia_num;
        }
    }

    private ArrayList<DataBean> data;

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }
}
