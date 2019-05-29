package com.zthx.npj.net.been;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangxin on 2019/5/28.
 */

public class LocalSpokesmanResponseBean extends BaseReponseBean{

    private ArrayList<LocalSpokesmanDetail> data;

    public ArrayList<LocalSpokesmanDetail> getList() {
        return data;
    }

    public void setList(ArrayList<LocalSpokesmanDetail> data) {
        this.data = data;
    }

    public class LocalSpokesmanDetail {

        private int id;
        private String nick_name;
        private String head_img;
        private String mobile;
        private String lat;
        private String lng;
        private int level;
        private long distance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        @Override
        public String toString() {
            return "LocalSpokesmanDetail{" +
                    "id=" + id +
                    ", nick_name='" + nick_name + '\'' +
                    ", head_img='" + head_img + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lng='" + lng + '\'' +
                    ", level=" + level +
                    ", distance=" + distance +
                    '}';
        }

        public long getDistance() {
            return distance;
        }

        public void setDistance(long distance) {
            this.distance = distance;
        }
    }
}
