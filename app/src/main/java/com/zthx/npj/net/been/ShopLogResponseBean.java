package com.zthx.npj.net.been;

import java.util.ArrayList;

public class ShopLogResponseBean extends BaseReponseBean{
    public class DataBean{

    }
    private ArrayList<DataBean> data;

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }
}
