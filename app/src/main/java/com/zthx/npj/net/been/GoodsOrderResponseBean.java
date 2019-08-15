package com.zthx.npj.net.been;

/**
 * Created by huangxin on 2019/6/10.
 */

public class GoodsOrderResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private String goods_name;
        private String goods_img;
        private String goods_price;
        private String freight;
        private String store_name;
        public class Attributes{
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
        private Attributes attributes;
        private long address_id;
        private String consignee;
        private String mobile;
        private String address;

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
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

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }


        public long getAddress_id() {
            return address_id;
        }

        public void setAddress_id(long address_id) {
            this.address_id = address_id;
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
    }
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
