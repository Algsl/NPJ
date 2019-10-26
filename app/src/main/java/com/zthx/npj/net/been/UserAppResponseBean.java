package com.zthx.npj.net.been;

import java.util.ArrayList;

public class UserAppResponseBean extends BaseReponseBean{
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
