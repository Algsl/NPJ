package com.zthx.npj.net.been;

import java.util.ArrayList;

public class MySupplyListResponseBean extends BaseReponseBean{
    public class DataBean{
        /*private long id;
        private String goods_name;
        private String market_price;
        private String member_price;
        private String user_price;
        private String sold;
        private String inventory;
        private String goods_img;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getMember_price() {
            return member_price;
        }

        public void setMember_price(String member_price) {
            this.member_price = member_price;
        }

        public String getUser_price() {
            return user_price;
        }

        public void setUser_price(String user_price) {
            this.user_price = user_price;
        }

        public String getSold() {
            return sold;
        }

        public void setSold(String sold) {
            this.sold = sold;
        }

        public String getInventory() {
            return inventory;
        }

        public void setInventory(String inventory) {
            this.inventory = inventory;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }*/
        private String user_id;
        private String update_time;
        private String title;
        private long status;
        private String sold;
        private String remark;
        private String price;
        private String lat;
        private String lng;
        private String id;
        private String hits;
        private String goods_unit;
        private String goods_num;
        private String goods_name;
        private String goods_img;
        private String create_time;
        private String content;
        private String city;
        private String buy_num;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
            this.status = status;
        }

        public String getSold() {
            return sold;
        }

        public void setSold(String sold) {
            this.sold = sold;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public String getGoods_unit() {
            return goods_unit;
        }

        public void setGoods_unit(String goods_unit) {
            this.goods_unit = goods_unit;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getBuy_num() {
            return buy_num;
        }

        public void setBuy_num(String buy_num) {
            this.buy_num = buy_num;
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
