package com.zthx.npj.net.been;

public class MarginResponseBean extends BaseReponseBean{
    public class DataBean{
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
