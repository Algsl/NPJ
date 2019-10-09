package com.zthx.npj.net.been;

public class MyOrderDetailResponseBean extends BaseReponseBean{
    public class DataBean{
        private String consignee;
        private String mobile;
        private String address;
        private String house_number;
        private String goods_name;
        private String goods_img;
        private String goods_price;
        private long goods_num;
        private String order_price;
        private String store_name;
        private String shipping_fee;
        private long order_time;
        private String order_sn;
        private long order_state;
        private String express_code;
        private String express_name;
        private String express_number;
        private String jujue_yuanyin;


        public String getExpress_code() {
            return express_code;
        }

        public void setExpress_code(String express_code) {
            this.express_code = express_code;
        }

        public String getJujue_yuanyin() {
            return jujue_yuanyin;
        }

        public void setJujue_yuanyin(String jujue_yuanyin) {
            this.jujue_yuanyin = jujue_yuanyin;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
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

        public String getHouse_number() {
            return house_number;
        }

        public void setHouse_number(String house_number) {
            this.house_number = house_number;
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

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
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
    }
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
