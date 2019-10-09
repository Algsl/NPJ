package com.zthx.npj.net.been;

import java.util.ArrayList;

public class MySupplyOrderDetailResponseBean extends BaseReponseBean{
    public class DataBean{
        private String address;
        private String address_id;
        private String consignee;
        private String express_code;
        private String express_id;
        private String express_name;
        private String express_number;
        private String goods_id;
        private ArrayList<String> goods_img;
        private String goods_name;
        private String goods_price;
        private long id;
        private String mobile;
        private String order_num;
        private String order_price;
        private String order_sn;
        private String order_state;
        private long order_time;
        private String pay_code;
        private long pay_time;
        private String refund_desc;
        private String refund_img;
        private String refund_price;
        private String refund_reason;
        private String refund_state;
        private String remark;
        private String seller_id;
        private String shipping_fee;
        private String title;
        private long update_time;
        private String user_id;

        private String jujue_yuanyin;


        public String getJujue_yuanyin() {
            return jujue_yuanyin;
        }

        public void setJujue_yuanyin(String jujue_yuanyin) {
            this.jujue_yuanyin = jujue_yuanyin;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getExpress_code() {
            return express_code;
        }

        public void setExpress_code(String express_code) {
            this.express_code = express_code;
        }

        public String getExpress_id() {
            return express_id;
        }

        public void setExpress_id(String express_id) {
            this.express_id = express_id;
        }

        public String getExpress_name() {
            return express_name;
        }

        public void setExpress_name(String express_name) {
            this.express_name = express_name;
        }

        public String getExpress_number() {
            return express_number;
        }

        public void setExpress_number(String express_number) {
            this.express_number = express_number;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public ArrayList<String> getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(ArrayList<String> goods_img) {
            this.goods_img = goods_img;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }

        public String getPay_code() {
            return pay_code;
        }

        public void setPay_code(String pay_code) {
            this.pay_code = pay_code;
        }


        public String getRefund_desc() {
            return refund_desc;
        }

        public void setRefund_desc(String refund_desc) {
            this.refund_desc = refund_desc;
        }

        public String getRefund_img() {
            return refund_img;
        }

        public void setRefund_img(String refund_img) {
            this.refund_img = refund_img;
        }

        public String getRefund_price() {
            return refund_price;
        }

        public void setRefund_price(String refund_price) {
            this.refund_price = refund_price;
        }

        public String getRefund_reason() {
            return refund_reason;
        }

        public void setRefund_reason(String refund_reason) {
            this.refund_reason = refund_reason;
        }

        public String getRefund_state() {
            return refund_state;
        }

        public void setRefund_state(String refund_state) {
            this.refund_state = refund_state;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getShipping_fee() {
            return shipping_fee;
        }

        public void setShipping_fee(String shipping_fee) {
            this.shipping_fee = shipping_fee;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getOrder_time() {
            return order_time;
        }

        public void setOrder_time(long order_time) {
            this.order_time = order_time;
        }

        public long getPay_time() {
            return pay_time;
        }

        public void setPay_time(long pay_time) {
            this.pay_time = pay_time;
        }

        public long getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(long update_time) {
            this.update_time = update_time;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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
