package com.zthx.npj.net.been;

import java.util.ArrayList;

public class MyOrderListResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private String order_sn;
        private long order_time;
        private String goods_name;
        private String goods_img;
        private long goods_num;
        private String goods_price;
        private String order_state;
        private long refund_time;

        public long getRefund_time() {
            return refund_time;
        }

        public void setRefund_time(long refund_time) {
            this.refund_time = refund_time;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public long getOrder_time() {
            return order_time;
        }

        public void setOrder_time(long order_time) {
            this.order_time = order_time;
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

        public long getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(long goods_num) {
            this.goods_num = goods_num;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
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
