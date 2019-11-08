package com.zthx.npj.net.been;

import java.util.ArrayList;

public class CartOrderResponseBean extends BaseReponseBean{
    public class DataBean{
        public class List{
            private long id;
            private long goods_num;
            private long store_id;
            private long goods_id;
            private String goods_name;
            private String goods_img;
            private String member_price;
            private String user_price;
            private long is_free_shipping;
            private String store_name;
            private String price;
            private String item_id;
            private String key_name;


            public boolean ziti=false;
            public String ziti_id;
            public String remark;

            public String getKey_name() {
                return key_name;
            }

            public void setKey_name(String key_name) {
                this.key_name = key_name;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }


            public boolean isZiti() {
                return ziti;
            }

            public void setZiti(boolean ziti) {
                this.ziti = ziti;
            }

            public String getZiti_id() {
                return ziti_id;
            }

            public void setZiti_id(String ziti_id) {
                this.ziti_id = ziti_id;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(long goods_num) {
                this.goods_num = goods_num;
            }

            public long getStore_id() {
                return store_id;
            }

            public void setStore_id(long store_id) {
                this.store_id = store_id;
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

            public String getMember_price() {
                return member_price;
            }

            public void setMember_price(String member_price) {
                this.member_price = member_price;
            }

            public String getUser_price() {
                return user_price;
            }

            public void setUser_price(String user_price) {
                this.user_price = user_price;
            }

            public long getIs_free_shipping() {
                return is_free_shipping;
            }

            public void setIs_free_shipping(long is_free_shipping) {
                this.is_free_shipping = is_free_shipping;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
        private long add_id;
        private ArrayList<ArrayList<List>>  list;

        public long getAdd_id() {
            return add_id;
        }

        public void setAdd_id(long add_id) {
            this.add_id = add_id;
        }

        public ArrayList<ArrayList<List>> getList() {
            return list;
        }

        public void setList(ArrayList<ArrayList<List>> list) {
            this.list = list;
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
