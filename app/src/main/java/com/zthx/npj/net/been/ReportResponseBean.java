package com.zthx.npj.net.been;

public class ReportResponseBean extends BaseReponseBean{
    public class DataBean{
        private long status;

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
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
