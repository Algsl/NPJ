package com.zthx.npj.net.been;

import java.util.ArrayList;

public class CollectionStoreResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private long list_id;
        private String store_name;
        private String store_img;
        private long count;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getList_id() {
            return list_id;
        }

        public void setList_id(long list_id) {
            this.list_id = list_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_img() {
            return store_img;
        }

        public void setStore_img(String store_img) {
            this.store_img = store_img;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
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
