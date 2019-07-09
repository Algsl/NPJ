package com.zthx.npj.net.been;

import java.util.ArrayList;

public class LookKDResponseBean extends BaseReponseBean{
    public class DataBean{
        public class DataBean1{
            private String time;
            private String ftime;
            private String context;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }
        }
        private ArrayList<DataBean1> data;

        public ArrayList<DataBean1> getData() {
            return data;
        }

        public void setData(ArrayList<DataBean1> data) {
            this.data = data;
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
