package com.zthx.npj.net.been;

public class UploadImgResponseBean extends BaseReponseBean{
    public class DataBean{
        private String src;
        private String srcall;

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
    }
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
