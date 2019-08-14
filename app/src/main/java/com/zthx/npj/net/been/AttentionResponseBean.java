package com.zthx.npj.net.been;

public class AttentionResponseBean extends BaseReponseBean{
    public class DataBean{
        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
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
