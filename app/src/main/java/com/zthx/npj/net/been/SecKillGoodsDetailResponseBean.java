package com.zthx.npj.net.been;

import java.util.ArrayList;

/**
 * Created by huangxin on 2019/6/11.
 */

public class SecKillGoodsDetailResponseBean extends BaseReponseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean{

        private long id;
        private long user_id;
        private String goods_name;
        private String goods_desc;
        private String goods_num;
        private String goods_price;
        private String market_price;
        private String goods_img;
        private ArrayList<String> group_img;
        private String sold;
        private String sale_num;
        private int is_free_shipping;
        private boolean isCollect;

        public boolean isCollect() {
            return isCollect;
        }

        public void setCollect(boolean collect) {
            isCollect = collect;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
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

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
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

        public ArrayList<String> getGroup_img() {
            return group_img;
        }

        public void setGroup_img(ArrayList<String> group_img) {
            this.group_img = group_img;
        }

        public String getSold() {
            return sold;
        }

        public void setSold(String sold) {
            this.sold = sold;
        }

        public String getSale_num() {
            return sale_num;
        }

        public void setSale_num(String sale_num) {
            this.sale_num = sale_num;
        }

        public int getIs_free_shipping() {
            return is_free_shipping;
        }

        public void setIs_free_shipping(int is_free_shipping) {
            this.is_free_shipping = is_free_shipping;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(long begin_time) {
            this.begin_time = begin_time;
        }

        public long getEnd_time() {
            return end_time;
        }

        public void setEnd_time(long end_time) {
            this.end_time = end_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        private String content;
        private long begin_time;
        private long end_time;
        private int status;
        private String freight;

    }
}
