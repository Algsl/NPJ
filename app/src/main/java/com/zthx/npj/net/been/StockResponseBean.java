package com.zthx.npj.net.been;

public class StockResponseBean extends BaseReponseBean{
    public class DataBean{
        private String cert_id;
        private String status;

        public String getCert_id() {
            return cert_id;
        }

        public void setCert_id(String cert_id) {
            this.cert_id = cert_id;
        }

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
