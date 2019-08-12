package com.zthx.npj.net.been;

import java.util.ArrayList;

public class BaojiaUserDetailResponseBean extends BaseReponseBean{
    /*public class DataBean{
        private String head_img;
        private String nick_name;
        private long level;
        private long create_time;
        private String certification;
        private long reputation;
        private long id;

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
    }*/
    public class DataBean{
        private long id;
        private long list_id;
        private String content;
        private String user_id;
        private long create_time;
        private String remark;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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
