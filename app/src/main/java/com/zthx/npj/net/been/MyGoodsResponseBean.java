package com.zthx.npj.net.been;

import java.util.ArrayList;

public class MyGoodsResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private String goods_name;
        private String market_price;
        private String member_price;
        private String user_price;
        private long sold;
        private long inventory;
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

        public long getSold() {
            return sold;
        }

        public void setSold(long sold) {
            this.sold = sold;
        }

        public long getInventory() {
            return inventory;
        }

        public void setInventory(long inventory) {
            this.inventory = inventory;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    private ArrayList<DataBean> data;
}
