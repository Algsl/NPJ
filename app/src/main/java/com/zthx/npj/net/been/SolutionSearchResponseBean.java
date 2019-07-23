package com.zthx.npj.net.been;

import java.util.ArrayList;

public class SolutionSearchResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private String title;
        private String img;
        private long sale_num;
        private long look_num;
        private long status;
        private String video_time;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public long getSale_num() {
            return sale_num;
        }

        public void setSale_num(long sale_num) {
            this.sale_num = sale_num;
        }

        public long getLook_num() {
            return look_num;
        }

        public void setLook_num(long look_num) {
            this.look_num = look_num;
        }

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
            this.status = status;
        }

        public String getVideo_time() {
            return video_time;
        }

        public void setVideo_time(String video_time) {
            this.video_time = video_time;
        }
    }

    public ArrayList<DataBean> data;

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }
}
