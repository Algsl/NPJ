package com.zthx.npj.net.been;

public class StoreInfoResponseBean extends BaseReponseBean{
    public class DataBean{
        private String store_name;
        private String store_img;
        private long level;
        private String att_num;
        private String goods_num;
        private String is_shoucang;

        private boolean isCollect;

        public boolean isCollect() {
            return isCollect;
        }

        public void setCollect(boolean collect) {
            isCollect = collect;
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

        public long getLevel() {
            return level;
        }

        public void setLevel(long level) {
            this.level = level;
        }

        public String getAtt_num() {
            return att_num;
        }

        public void setAtt_num(String att_num) {
            this.att_num = att_num;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public String getIs_shoucang() {
            return is_shoucang;
        }

        public void setIs_shoucang(String is_shoucang) {
            this.is_shoucang = is_shoucang;
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
