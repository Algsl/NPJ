package com.zthx.npj.net.been;

import java.util.ArrayList;

public class GoodsInfoResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private String goods_name;
        private String goods_desc;
        private ArrayList<String> goods_img;
        private String platform_price;
        private String member_price;
        private String market_price;
        private long inventory;
        private long cate_id;
        private long is_free_shipping;
        private ArrayList<String> goods_content;
        private long goods_type;
        private String cate_name;

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

        public String getPlatform_price() {
            return platform_price;
        }

        public void setPlatform_price(String platform_price) {
            this.platform_price = platform_price;
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

        public long getCate_id() {
            return cate_id;
        }

        public void setCate_id(long cate_id) {
            this.cate_id = cate_id;
        }

        public long getIs_free_shipping() {
            return is_free_shipping;
        }

        public void setIs_free_shipping(long is_free_shipping) {
            this.is_free_shipping = is_free_shipping;
        }

        public ArrayList<String> getGoods_content() {
            return goods_content;
        }

        public void setGoods_content(ArrayList<String> goods_content) {
            this.goods_content = goods_content;
        }

        public long getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(long goods_type) {
            this.goods_type = goods_type;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
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
