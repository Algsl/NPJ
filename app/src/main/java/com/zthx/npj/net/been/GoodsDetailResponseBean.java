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

        public class Attributes{
            private long item_id;
            private long goods_id;
            private long key;
            private String key_name;
            private String spec_member_price;
            private String spec_user_price;
            private String store_count;

            public long getItem_id() {
                return item_id;
            }

            public void setItem_id(long item_id) {
                this.item_id = item_id;
            }

            public long getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(long goods_id) {
                this.goods_id = goods_id;
            }

            public long getKey() {
                return key;
            }

            public void setKey(long key) {
                this.key = key;
            }

            public String getKey_name() {
                return key_name;
            }

            public void setKey_name(String key_name) {
                this.key_name = key_name;
            }

            public String getSpec_member_price() {
                return spec_member_price;
            }

            public void setSpec_member_price(String spec_member_price) {
                this.spec_member_price = spec_member_price;
            }

            public String getSpec_user_price() {
                return spec_user_price;
            }

            public void setSpec_user_price(String spec_user_price) {
                this.spec_user_price = spec_user_price;
            }

            public String getStore_count() {
                return store_count;
            }

            public void setStore_count(String store_count) {
                this.store_count = store_count;
            }
        }
        public ArrayList<Attributes> attributes;

        public ArrayList<Attributes> getAttributes() {
            return attributes;
        }

        public void setAttributes(ArrayList<Attributes> attributes) {
            this.attributes = attributes;
        }

        public int getYunfei() {
            return yunfei;
        }

        public void setYunfei(int yunfei) {
            this.yunfei = yunfei;
        }
    }
}
