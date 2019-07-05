package com.zthx.npj.net.been;

public class MyStoreResponseBean extends BaseReponseBean{
    public class DataBean{
        private String store_name;
        private String store_img;
        private long reputation;

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

        public long getReputation() {
            return reputation;
        }

        public void setReputation(long reputation) {
            this.reputation = reputation;
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
