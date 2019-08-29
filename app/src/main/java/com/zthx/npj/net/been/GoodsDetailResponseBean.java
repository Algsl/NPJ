package com.zthx.npj.net.been;

import java.util.ArrayList;

/**
 * Created by huangxin on 2019/6/5.
 */

public class GoodsDetailResponseBean extends BaseReponseBean {
    public class DataBean{
        private long id;
        private String user_id;
        private String goods_name;
        private String goods_desc;
        private ArrayList<String> goods_img;
        private String member_price;
        private String user_price;
        private String market_price;
        private long inventory;
        private long sold;
        private long is_free_shipping;
        private ArrayList<String> goods_content;
        private String spec;
        private long collection;
        public class Attributes{
            private long item_id;
            private long goods_id;
            private String key;
            private String key_name;
            private String spec_member_price;
            private String spec_user_price;
            private long store_count;

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

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
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

            public long getStore_count() {
                return store_count;
            }

            public void setStore_count(long store_count) {
                this.store_count = store_count;
            }
        }
        private ArrayList<Attributes> attributes;

        public long getCollection() {
            return collection;
        }

        public void setCollection(long collection) {
            this.collection = collection;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getUser_price() {
            return user_price;
        }

        public void setUser_price(String user_price) {
            this.user_price = user_price;
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

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public ArrayList<Attributes> getAttributes() {
            return attributes;
        }

        public void setAttributes(ArrayList<Attributes> attributes) {
            this.attributes = attributes;
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
