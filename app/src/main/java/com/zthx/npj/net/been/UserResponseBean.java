package com.zthx.npj.net.been;

public class UserResponseBean extends BaseReponseBean{
    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    private DataBean data;
    public class DataBean{
        private long id;
        private String nick_name;
        private String head_img;
        private String mobile;
        private String signature;
        private int level;
        private long gourd_coin;

        private int team_level;
        private int boss_level;
        private int city_level;


        public int getTeam_level() {
            return team_level;
        }

        public void setTeam_level(int team_level) {
            this.team_level = team_level;
        }

        public int getBoss_level() {
            return boss_level;
        }

        public void setBoss_level(int boss_level) {
            this.boss_level = boss_level;
        }

        public int getCity_level() {
            return city_level;
        }

        public void setCity_level(int city_level) {
            this.city_level = city_level;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

        public long getGourd_coin() {
            return gourd_coin;
        }

        public void setGourd_coin(long gourd_coin) {
            this.gourd_coin = gourd_coin;
        }

        public long getCoupon_num() {
            return coupon_num;
        }

        public void setCoupon_num(long coupon_num) {
            this.coupon_num = coupon_num;
        }

        public String getReputation() {
            return reputation;
        }

        public void setReputation(String reputation) {
            this.reputation = reputation;
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

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        private long   coupon_num;
        private String reputation;
        private String lat;
        private String lng;
        private String balance;
        private long collection_num;

        public long getCollection_num() {
            return collection_num;
        }

        public void setCollection_num(long collection_num) {
            this.collection_num = collection_num;
        }
    }
}
