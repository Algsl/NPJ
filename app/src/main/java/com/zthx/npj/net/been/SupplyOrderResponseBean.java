package com.zthx.npj.net.been;

import java.util.ArrayList;

public class SupplyOrderResponseBean {
    public class DataBean{
        private String goods_img;
        private String title;
        private String goods_name;
        private String order_sn;
        private String order_price;
        private long order_num;
        private long order_time;
        private long order_state;

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public long getOrder_num() {
            return order_num;
        }

        public void setOrder_num(long order_num) {
            this.order_num = order_num;
        }

        public long getOrder_time() {
            return order_time;
        }

        public void setOrder_time(long order_time) {
            this.order_time = order_time;
        }

        public long getOrder_state() {
            return order_state;
        }

        public void setOrder_state(long order_state) {
            this.order_state = order_state;
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
