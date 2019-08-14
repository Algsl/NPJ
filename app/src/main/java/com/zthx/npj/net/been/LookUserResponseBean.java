package com.zthx.npj.net.been;

public class LookUserResponseBean extends BaseReponseBean{
    public class DataBean{
        private String nick_name;
        private String head_img;
        private String signature;
        private long level;
        private String lat;
        private String lng;
        private String reputation;
        private String hits;
        private String history_money;
        private String certification;
        private String att_num;

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public long getLevel() {
            return level;
        }

        public void setLevel(long level) {
            this.level = level;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getReputation() {
            return reputation;
        }

        public void setReputation(String reputation) {
            this.reputation = reputation;
        }

        public String getHits() {
            return hits;
        }

        public void setHits(String hits) {
            this.hits = hits;
        }

        public String getHistory_money() {
            return history_money;
        }

        public void setHistory_money(String history_money) {
            this.history_money = history_money;
        }

        public String getCertification() {
            return certification;
        }

        public void setCertification(String certification) {
            this.certification = certification;
        }

        public String getAtt_num() {
            return att_num;
        }

        public void setAtt_num(String att_num) {
            this.att_num = att_num;
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
