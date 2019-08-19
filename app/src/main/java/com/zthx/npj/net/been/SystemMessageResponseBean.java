package com.zthx.npj.net.been;

import java.util.ArrayList;

public class SystemMessageResponseBean extends BaseReponseBean{
    public class DataBean{
        private String msgImg;
        private String msgTitle;
        private long msgTime;
        private String msgContent;
        private String msgFrom;

        public String getMsgImg() {
            return msgImg;
        }

        public void setMsgImg(String msgImg) {
            this.msgImg = msgImg;
        }

        public String getMsgTitle() {
            return msgTitle;
        }

        public void setMsgTitle(String msgTitle) {
            this.msgTitle = msgTitle;
        }

        public long getMsgTime() {
            return msgTime;
        }

        public void setMsgTime(long msgTime) {
            this.msgTime = msgTime;
        }

        public String getMsgContent() {
            return msgContent;
        }

        public void setMsgContent(String msgContent) {
            this.msgContent = msgContent;
        }

        public String getMsgFrom() {
            return msgFrom;
        }

        public void setMsgFrom(String msgFrom) {
            this.msgFrom = msgFrom;
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
