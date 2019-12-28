package com.zthx.npj.net.been;

import java.util.ArrayList;

public class SupplyLogResponseBean extends BaseReponseBean{
    public class DataBean{
        public class Mingxi{
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
        private String shouyi;
        private ArrayList<SupplyLogResponseBean.DataBean.Mingxi> mingxi;

        public String getShouyi() {
            return shouyi;
        }

        public void setShouyi(String shouyi) {
            this.shouyi = shouyi;
        }

        public ArrayList<SupplyLogResponseBean.DataBean.Mingxi> getMingxi() {
            return mingxi;
        }

        public void setMingxi(ArrayList<SupplyLogResponseBean.DataBean.Mingxi> mingxi) {
            this.mingxi = mingxi;
        }
    }

    private SupplyLogResponseBean.DataBean data;

    public SupplyLogResponseBean.DataBean getData() {
        return data;
    }

    public void setData(SupplyLogResponseBean.DataBean data) {
        this.data = data;
    }
}
