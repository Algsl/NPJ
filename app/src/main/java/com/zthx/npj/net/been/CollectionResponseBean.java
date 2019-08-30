package com.zthx.npj.net.been;

import java.util.ArrayList;

public class CollectionResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private long list_id;
        private String goods_name;
        private String goods_img;
        private String user_price;
        private String goods_price;

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getUser_price() {
            return user_price;
        }

        public void setUser_price(String user_price) {
            this.user_price = user_price;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getList_id() {
            return list_id;
        }

        public void setList_id(long list_id) {
            this.list_id = list_id;
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
    }
    private ArrayList<DataBean> data;

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }
}
