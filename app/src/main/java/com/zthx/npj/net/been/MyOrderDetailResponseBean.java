package com.zthx.npj.net.been;

public class MyOrderDetailResponseBean extends BaseReponseBean{
    public class DataBean{
        private String consignee;
        private String mobile;
        private String address;
        private long goods_id;
        private String goods_name;
        private String goods_img;
        private long item_id;
        private String goods_price;
        private long goods_num;
        private long order_type;
        private long store_id;
        private String order_price;
        private String store_name;
        private String shipping_fee;
        private long order_time;
        private String order_sn;
        private long order_state;
        private String express_name;
        private String express_number;
        private String jujue_yuanyin;
        private long refund_time;
        private String key_name;
        private long pay_time;
        private String refund_price;
        private String refund_reason;

        public String getRefund_reason() {
            return refund_reason;
        }

        public void setRefund_reason(String refund_reason) {
            this.refund_reason = refund_reason;
        }

        public String getRefund_price() {
            return refund_price;
        }

        public void setRefund_price(String refund_price) {
            this.refund_price = refund_price;
        }

        public long getPay_time() {
            return pay_time;
        }

        public void setPay_time(long pay_time) {
            this.pay_time = pay_time;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public long getItem_id() {
            return item_id;
        }

        public void setItem_id(long item_id) {
            this.item_id = item_id;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public long getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(long goods_num) {
            this.goods_num = goods_num;
        }

        public long getOrder_type() {
            return order_type;
        }

        public void setOrder_type(long order_type) {
            this.order_type = order_type;
        }

        public long getStore_id() {
            return store_id;
        }

        public void setStore_id(long store_id) {
            this.store_id = store_id;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getShipping_fee() {
            return shipping_fee;
        }

        public void setShipping_fee(String shipping_fee) {
            this.shipping_fee = shipping_fee;
        }

        public long getOrder_time() {
            return order_time;
        }

        public void setOrder_time(long order_time) {
            this.order_time = order_time;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public long getOrder_state() {
            return order_state;
        }

        public void setOrder_state(long order_state) {
            this.order_state = order_state;
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

        public String getJujue_yuanyin() {
            return jujue_yuanyin;
        }

        public void setJujue_yuanyin(String jujue_yuanyin) {
            this.jujue_yuanyin = jujue_yuanyin;
        }

        public long getRefund_time() {
            return refund_time;
        }

        public void setRefund_time(long refund_time) {
            this.refund_time = refund_time;
        }

        public String getKey_name() {
            return key_name;
        }

        public void setKey_name(String key_name) {
            this.key_name = key_name;
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
