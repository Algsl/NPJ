package com.zthx.npj.net.been;

import java.util.ArrayList;

/**
 * Created by huangxin on 2019/5/31.
 */

public class GoodsDetailResponseBean extends BaseReponseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean {
        private long id;
        private String goods_name;
        private String goods_desc;
        private ArrayList<String> goods_img;
        private String member_price;
        private String market_price;

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

        public String getGoods_desc() {
            return goods_desc;
        }

        public void setGoods_desc(String goods_desc) {
            this.goods_desc = goods_desc;
        }

        public ArrayList<String> getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(ArrayList<String> goods_img) {
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

        public long getInventory() {
            return inventory;
        }

        public void setInventory(long inventory) {
            this.inventory = inventory;
        }

        public long getSold() {
            return sold;
        }

        public void setSold(long sold) {
            this.sold = sold;
        }

        public int getIs_free_shipping() {
            return is_free_shipping;
        }

        public void setIs_free_shipping(int is_free_shipping) {
            this.is_free_shipping = is_free_shipping;
        }

        private long inventory;
        private long sold;
        private int is_free_shipping;
        private int yunfei;

        public int getYunfei() {
            return yunfei;
        }

        public void setYunfei(int yunfei) {
            this.yunfei = yunfei;
        }
    }
}
