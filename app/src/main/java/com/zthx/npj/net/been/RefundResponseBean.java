package com.zthx.npj.net.been;

public class RefundResponseBean extends BaseReponseBean{
    public class DataBean{
        private String refund_desc;
        private String refund_img;
        private String refund_price;
        private String refund_reason;
        private long refund_state;

        public String getRefund_desc() {
            return refund_desc;
        }

        public void setRefund_desc(String refund_desc) {
            this.refund_desc = refund_desc;
        }

        public String getRefund_img() {
            return refund_img;
        }

        public void setRefund_img(String refund_img) {
            this.refund_img = refund_img;
        }

        public String getRefund_price() {
            return refund_price;
        }

        public void setRefund_price(String refund_price) {
            this.refund_price = refund_price;
        }

        public String getRefund_reason() {
            return refund_reason;
        }

        public void setRefund_reason(String refund_reason) {
            this.refund_reason = refund_reason;
        }

        public long getRefund_state() {
            return refund_state;
        }

        public void setRefund_state(long refund_state) {
            this.refund_state = refund_state;
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
