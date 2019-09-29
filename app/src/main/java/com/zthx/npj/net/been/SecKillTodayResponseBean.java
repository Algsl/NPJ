package com.zthx.npj.net.been;

import java.util.ArrayList;

/**
 * Created by huangxin on 2019/6/11.
 */

public class SecKillTodayResponseBean {

    private ArrayList<DataBean> data;

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public class DataBean{
        private long id;
        private String goods_name;
        private String goods_desc;
        private String goods_price;
        private String market_price;
        private String goods_img;
        private long goods_num;
        private long sale_num;
        private long begin_time;
        private long sold;

        public long getSold() {
            return sold;
        }

        public void setSold(long sold) {
            this.sold = sold;
        }

        public long getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(long begin_time) {
            this.begin_time = begin_time;
        }

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

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public long getSale_num() {
            return sale_num;
        }

        public void setSale_num(long sale_num) {
            this.sale_num = sale_num;
        }

        public long getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(long goods_num) {
            this.goods_num = goods_num;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getEnd_time() {
            return end_time;
        }

        public void setEnd_time(long end_time) {
            this.end_time = end_time;
        }

        private int status;
        private long end_time;
    }
}
