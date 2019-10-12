package com.zthx.npj.net.been;

public class XTSZResponseBean extends BaseReponseBean{
    public class DataBean{
        private long is_zhifu;

        public long getIs_zhifu() {
            return is_zhifu;
        }

        public void setIs_zhifu(long is_zhifu) {
            this.is_zhifu = is_zhifu;
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
