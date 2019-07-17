package com.zthx.npj.net.been;

/**
 * Created by huangxin on 2019/6/13.
 */

public class UpLoadPicResponseBean extends BaseReponseBean {

    public class DataBean{
        private String src;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getSrcall() {
            return srcall;
        }

        public void setSrcall(String srcall) {
            this.srcall = srcall;
        }

        private String srcall;
    }
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
