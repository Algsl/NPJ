package com.zthx.npj.net.been;

import java.util.ArrayList;

public class UserMoneyResponseBean extends BaseReponseBean{
    public class DataBean{
        private String recharge;
        private String withdraw;
        public class MingXi{
            private long create_time;
            private String id;
            private String price;
            private String status;
            private String title;
            private String type;
            private String user_id;

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
        }
        private ArrayList<MingXi> mingxi;

        public String getRecharge() {
            return recharge;
        }

        public void setRecharge(String recharge) {
            this.recharge = recharge;
        }

        public String getWithdraw() {
            return withdraw;
        }

        public void setWithdraw(String withdraw) {
            this.withdraw = withdraw;
        }

        public ArrayList<MingXi> getMingxi() {
            return mingxi;
        }

        public void setMingxi(ArrayList<MingXi> mingxi) {
            this.mingxi = mingxi;
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
