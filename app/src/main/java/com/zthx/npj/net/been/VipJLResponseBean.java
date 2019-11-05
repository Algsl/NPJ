package com.zthx.npj.net.been;

import java.util.ArrayList;

public class VipJLResponseBean extends BaseReponseBean{
    public class DataBean{
        private String shouyi;

        public class MingXi{
            private long id;
            private long user_id;
            private String title;
            private String price;
            private long type;
            private long status;
            private long create_time;

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
        }

        private ArrayList<MingXi> mingxi;

        public String getShouyi() {
            return shouyi;
        }

        public void setShouyi(String shouyi) {
            this.shouyi = shouyi;
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
