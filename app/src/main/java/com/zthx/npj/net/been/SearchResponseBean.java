package com.zthx.npj.net.been;

import java.util.ArrayList;

/**
 * Created by huangxin on 2019/5/31.
 */

public class SearchResponseBean extends BaseReponseBean {

    private ArrayList<DataBean> data;

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public class DataBean {
        private long id;
        private String goods_name;
        private String goods_img;
        private String member_price;
        private String market_price;
        private long sold;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getMember_price() {
            return member_price;
        }

        public void setMember_price(String member_price) {
            this.member_price = member_price;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public long getSold() {
            return sold;
        }

        public void setSold(long sold) {
            this.sold = sold;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }
    }
}