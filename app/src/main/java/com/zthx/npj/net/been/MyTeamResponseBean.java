package com.zthx.npj.net.been;

public class MyTeamResponseBean extends BaseReponseBean{
    public class DataBean{

        private long status;
        public class Result{
            private String head_img;
            private String nick_name;
            private long level;
            private long total_myteam;
            private long myteam;
            private long myamount;

            public String getHead_img() {
                return head_img;
            }

            public void setHead_img(String head_img) {
                this.head_img = head_img;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public long getLevel() {
                return level;
            }

            public void setLevel(long level) {
                this.level = level;
            }

            public long getTotal_myteam() {
                return total_myteam;
            }

            public void setTotal_myteam(long total_myteam) {
                this.total_myteam = total_myteam;
            }

            public long getMyteam() {
                return myteam;
            }

            public void setMyteam(long myteam) {
                this.myteam = myteam;
            }

            public long getMyamount() {
                return myamount;
            }

            public void setMyamount(long myamount) {
                this.myamount = myamount;
            }
        }
        private Result result;

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
            this.status = status;
        }

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
