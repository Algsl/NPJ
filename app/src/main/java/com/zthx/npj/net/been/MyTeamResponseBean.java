package com.zthx.npj.net.been;

public class MyTeamResponseBean extends BaseReponseBean{
    public class DataBean {

        private long status;

        public class Result {
            private int boss_level;
            private int city_level;
            private String head_img;
            private int level;
            private String myamount;
            private long myteam;
            private String nick_name;
            private int team_level;
            private long total_myteam;

            public int getBoss_level() {
                return boss_level;
            }


            public int getCity_level() {
                return city_level;
            }



            public String getHead_img() {
                return head_img;
            }

            public void setHead_img(String head_img) {
                this.head_img = head_img;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getMyamount() {
                return myamount;
            }

            public void setMyamount(String myamount) {
                this.myamount = myamount;
            }

            public long getMyteam() {
                return myteam;
            }

            public void setMyteam(long myteam) {
                this.myteam = myteam;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public int getTeam_level() {
                return team_level;
            }

            public void setBoss_level(int boss_level) {
                this.boss_level = boss_level;
            }

            public void setCity_level(int city_level) {
                this.city_level = city_level;
            }

            public void setTeam_level(int team_level) {
                this.team_level = team_level;
            }

            public long getTotal_myteam() {
                return total_myteam;
            }

            public void setTotal_myteam(long total_myteam) {
                this.total_myteam = total_myteam;
            }
        }

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
            this.status = status;
        }

        private Result result;

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
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
