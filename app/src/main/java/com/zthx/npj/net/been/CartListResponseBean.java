package com.zthx.npj.net.been;

import android.content.ClipData;

import java.util.ArrayList;

public class CartListResponseBean extends BaseReponseBean{
    public class DataBean{
        public class DataItem {
            private long id;
            private long goods_num;
            private long store_id;
            private long goods_id;
            private String goods_name;
            private String goods_img;
            private String member_price;
            private String user_price;
            private String store_name;
            private String price;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(long goods_num) {
                this.goods_num = goods_num;
            }

            public long getStore_id() {
                return store_id;
            }

            public void setStore_id(long store_id) {
                this.store_id = store_id;
            }

            public long getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(long goods_id) {
                this.goods_id = goods_id;
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

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
        private ArrayList<DataItem> item;

        public ArrayList<DataItem> getItem() {
            return item;
        }

        public void setItem(ArrayList<DataItem> item) {
            this.item = item;
        }
    }
    private DataBean[] data;

    public DataBean[] getData() {
        return data;
    }

    public void setData(DataBean[] data) {
        this.data = data;
    }
}
