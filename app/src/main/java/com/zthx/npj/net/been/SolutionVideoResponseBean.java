package com.zthx.npj.net.been;

import java.util.ArrayList;

/**
 * Created by huangxin on 2019/6/17.
 */

public class SolutionVideoResponseBean extends BaseReponseBean {

    private ArrayList<DataBean> data;

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public class DataBean{

        private String title;
        private String video;
        private String duration;
        private String img;
        private String content;
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
