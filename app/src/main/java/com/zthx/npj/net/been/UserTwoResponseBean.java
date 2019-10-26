package com.zthx.npj.net.been;

import java.util.ArrayList;

public class UserTwoResponseBean extends BaseReponseBean{
    public class DataBean{
        private int boss_level;
        private int city_level;
        private long create_time;
        private String head_img;
        private long id;
        private int level;
        private String mobile;
        private String nick_name;
        private int team_level;


        public int getBoss_level() {
            return boss_level;
        }

        public void setBoss_level(int boss_level) {
            this.boss_level = boss_level;
        }

        public int getCity_level() {
            return city_level;
        }

        public void setCity_level(int city_level) {
            this.city_level = city_level;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getTeam_level() {
            return team_level;
        }

        public void setTeam_level(int team_level) {
            this.team_level = team_level;
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
