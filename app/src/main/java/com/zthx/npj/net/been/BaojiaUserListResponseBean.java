package com.zthx.npj.net.been;

import java.util.ArrayList;

public class BaojiaUserListResponseBean extends BaseReponseBean{
    public class DataBean{
        private String head_img;
        private String nick_name;
        private long level;
        private long create_time;
        private String certification;
        private long reputation;
        private long id;

        /*private long baojia_time;
        private long uid;
        private String mobile;

        public long getBaojia_time() {
            return baojia_time;
        }

        public void setBaojia_time(long baojia_time) {
            this.baojia_time = baojia_time;
        }

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }*/

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public long getLevel() {
            return level;
        }

        public void setLevel(long level) {
            this.level = level;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getCertification() {
            return certification;
        }

        public void setCertification(String certification) {
            this.certification = certification;
        }

        public long getReputation() {
            return reputation;
        }

        public void setReputation(long reputation) {
            this.reputation = reputation;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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
