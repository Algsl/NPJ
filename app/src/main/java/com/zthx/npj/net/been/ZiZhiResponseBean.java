package com.zthx.npj.net.been;

public class ZiZhiResponseBean extends BaseReponseBean{
    public class DataBean{
        private long status;
        private long cert_id;

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
            this.status = status;
        }

        public long getCert_id() {
            return cert_id;
        }

        public void setCert_id(long cert_id) {
            this.cert_id = cert_id;
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