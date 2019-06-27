package com.zthx.npj.net.been;

import java.util.ArrayList;

/**
 * Created by huangxin on 2019/6/24.
 */

public class UploadPicsResponseBean extends BaseReponseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean{

        private ArrayList<String> imgaes;

        public ArrayList<String> getImgaes() {
            return imgaes;
        }

        public void setImgaes(ArrayList<String> imgaes) {
            this.imgaes = imgaes;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        private String img;
    }
}
