package com.zthx.npj.net.been;

import java.util.ArrayList;

public class HistoryResponseBean extends BaseReponseBean{
    public class DataBean{
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
    private ArrayList<DataBean> data;

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }
}
