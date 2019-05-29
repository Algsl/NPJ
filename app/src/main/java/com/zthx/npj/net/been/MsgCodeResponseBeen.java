package com.zthx.npj.net.been;

/**
 * Created by huangxin on 2019/5/29.
 */

public class MsgCodeResponseBeen extends BaseReponseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean {
        private String mobile;
        private String session_id;

        @Override
        public String toString() {
            return "DataBean{" +
                    "mobile='" + mobile + '\'' +
                    ", session_id='" + session_id + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSession_id() {
            return session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        private String code;
    }
}
