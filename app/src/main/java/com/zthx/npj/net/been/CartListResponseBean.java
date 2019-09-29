package com.zthx.npj.net.been;

import android.content.ClipData;

import java.util.ArrayList;
import java.util.List;

public class CartListResponseBean extends BaseReponseBean{
    public static class DataBean{
            private long id;
            private long goods_num;
            private long store_id;
            private long goods_id;
            private String goods_name;
            private String goods_img;
            private String goods_price;
            private String spec_key_name;
            private String store_name;
            private Boolean isSelect=false;
            private Boolean isSelectShop=false;

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
        }

        public String getSpec_key_name() {
            return spec_key_name;
        }

        public void setSpec_key_name(String spec_key_name) {
            this.spec_key_name = spec_key_name;
        }

        public Boolean getSelectShop() {
            return isSelectShop;
        }

        public void setSelectShop(Boolean selectShop) {
            isSelectShop = selectShop;
        }

        public Boolean getSelect() {
            return isSelect;
        }

        public void setSelect(Boolean select) {
            isSelect = select;
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


            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }


    }
    private ArrayList<ArrayList<DataBean>> data;

    public ArrayList<ArrayList<DataBean>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<DataBean>> data) {
        this.data = data;
    }


}
