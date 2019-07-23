package com.zthx.npj.net.been;

import java.util.ArrayList;

/**
 * Created by huangxin on 2019/6/4.
 */

public class CommentResponseBean extends BaseReponseBean {

    public class DataBean{
        private long id;
        private long user_id;
        private long goods_id;
        private long store_id;
        private String content;
        private ArrayList<String> img;
        private long status;
        private long create_time;
        private long type;
        private long goods_star;
        private long logistics_star;
        private long service_star;
        private String nick_name;
        private String head_img;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public long getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(long goods_id) {
            this.goods_id = goods_id;
        }

        public long getStore_id() {
            return store_id;
        }

        public void setStore_id(long store_id) {
            this.store_id = store_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public ArrayList<String> getImg() {
            return img;
        }

        public void setImg(ArrayList<String> img) {
            this.img = img;
        }

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
            this.status = status;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public long getType() {
            return type;
        }

        public void setType(long type) {
            this.type = type;
        }

        public long getGoods_star() {
            return goods_star;
        }

        public void setGoods_star(long goods_star) {
            this.goods_star = goods_star;
        }

        public long getLogistics_star() {
            return logistics_star;
        }

        public void setLogistics_star(long logistics_star) {
            this.logistics_star = logistics_star;
        }

        public long getService_star() {
            return service_star;
        }

        public void setService_star(long service_star) {
            this.service_star = service_star;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
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
