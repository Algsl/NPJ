package com.zthx.npj.net.been;

/**
 * Created by huangxin on 2019/6/15.
 */

public class ChengXinCertResponseBean extends BaseReponseBean{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean{
        private int status;
        private String bail;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getBail() {
            return bail;
        }

        public void setBail(String bail) {
            this.bail = bail;
        }
    }
}
