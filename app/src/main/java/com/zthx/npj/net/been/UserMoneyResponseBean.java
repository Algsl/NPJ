package com.zthx.npj.net.been;

import java.util.ArrayList;

public class UserMoneyResponseBean extends BaseReponseBean{
    public class DataBean{
        public class MingXi{
            private long id;
            private long user_id;
            private String title;
            private String price;
            private long type;
            private long create_time;
            private long status;
            private String detail_type;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public long getStatus() {
                return status;
            }

            public void setStatus(long status) {
                this.status = status;
            }

            public String getDetail_type() {
                return detail_type;
            }

            public void setDetail_type(String detail_type) {
                this.detail_type = detail_type;
            }
        }
        private ArrayList<MingXi> mingxi;
        private long recharge;
        private long withdraw;

        public ArrayList<MingXi> getMingxi() {
            return mingxi;
        }

        public void setMingxi(ArrayList<MingXi> mingxi) {
            this.mingxi = mingxi;
        }

        public long getRecharge() {
            return recharge;
        }

        public void setRecharge(long recharge) {
            this.recharge = recharge;
        }

        public long getWithdraw() {
            return withdraw;
        }

        public void setWithdraw(long withdraw) {
            this.withdraw = withdraw;
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
