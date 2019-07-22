package com.zthx.npj.net.been;

import java.util.ArrayList;

public class MyCouponResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private long status;
        private String title;
        private String money;
        private long type;
        private String condition;
        private long begin_time;
        private long end_time;
        private long coup_status;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public long getType() {
            return type;
        }

        public void setType(long type) {
            this.type = type;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public long getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(long begin_time) {
            this.begin_time = begin_time;
        }

        public long getEnd_time() {
            return end_time;
        }

        public void setEnd_time(long end_time) {
            this.end_time = end_time;
        }

        public long getCoup_status() {
            return coup_status;
        }

        public void setCoup_status(long coup_status) {
            this.coup_status = coup_status;
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
