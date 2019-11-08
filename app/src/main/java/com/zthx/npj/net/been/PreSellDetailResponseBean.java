package com.zthx.npj.net.been;

import java.util.ArrayList;

/**
 * Created by huangxin on 2019/6/5.
 */

public class PreSellDetailResponseBean extends BaseReponseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean {
        private long id;
        private String goods_price;
        private String goods_name;
        private String goods_num;
        private ArrayList<String> group_img;
        private long shipment;
        private String content;
        private String user_num;
        //private int sale_price;
        //private double proportion;
        private long is_shoucang;

        private String nick_name;
        private String mobile;

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public long getIs_shoucang() {
            return is_shoucang;
        }

        public void setIs_shoucang(long is_shoucang) {
            this.is_shoucang = is_shoucang;
        }

        /*public double getProportion() {
            return proportion;
        }

        public void setProportion(double proportion) {
            this.proportion = proportion;
        }

        public int getSale_price() {
            return sale_price;
        }

        public void setSale_price(int sale_price) {
            this.sale_price = sale_price;
        }*/

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public ArrayList<String> getGroup_img() {
            return group_img;
        }

        public void setGroup_img(ArrayList<String> group_img) {
            this.group_img = group_img;
        }

        public long getShipment() {
            return shipment;
        }

        public void setShipment(long shipment) {
            this.shipment = shipment;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUser_num() {
            return user_num;
        }

        public void setUser_num(String user_num) {
            this.user_num = user_num;
        }

        public String getSale_num() {
            return sale_num;
        }

        public void setSale_num(String sale_num) {
            this.sale_num = sale_num;
        }

        public ArrayList<Value> getAttribute_value() {
            return attribute_value;
        }

        public void setAttribute_value(ArrayList<Value> attribute_value) {
            this.attribute_value = attribute_value;
        }

        private String sale_num;
        private ArrayList<Value> attribute_value;

        public class Value {
            private long id;
            private String pre_number;
            private String pre_price;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getPre_number() {
                return pre_number;
            }

            public void setPre_number(String pre_number) {
                this.pre_number = pre_number;
            }

            public String getPre_price() {
                return pre_price;
            }

            public void setPre_price(String pre_price) {
                this.pre_price = pre_price;
            }
        }
    }

}
