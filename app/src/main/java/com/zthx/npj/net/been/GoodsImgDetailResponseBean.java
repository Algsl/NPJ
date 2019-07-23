package com.zthx.npj.net.been;

import java.util.ArrayList;

public class GoodsImgDetailResponseBean extends BaseReponseBean{
    public class DataBean{
        private ArrayList<String> imgs;

        public ArrayList<String> getImgs() {
            return imgs;
        }

        public void setImgs(ArrayList<String> imgs) {
            this.imgs = imgs;
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
