package com.zthx.npj.net.been;

public class LookUserResponseBean extends BaseReponseBean{
    public class DataBean{
        private String nick_name;
        private String head_img;
        private String signature;
        private int level;
        private String lat;
        private String lng;
        private String reputation;
        private String hits;
        private String history_money;
        private String certification;
        private String att_num;

        private int city_level;
        private int boss_level;
        private int team_level;
        private long is_attention;

        public long getIs_attention() {
            return is_attention;
        }

        public void setIs_attention(long is_attention) {
            this.is_attention = is_attention;
        }

        public int getCity_level() {
            return city_level;
        }

        public void setCity_level(int city_level) {
            this.city_level = city_level;
        }

        public int getBoss_level() {
            return boss_level;
        }

        public void setBoss_level(int boss_level) {
            this.boss_level = boss_level;
        }

        public int getTeam_level() {
            return team_level;
        }

        public void setTeam_level(int team_level) {
            this.team_level = team_level;
        }

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

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
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
