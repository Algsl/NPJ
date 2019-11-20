package com.zthx.npj.net.been;

public class BonusListResponseBean extends BaseReponseBean{
    public class DataBean{
        private long status;
        public class List{
            private String type_money;
            private long bonus_id;

            public String getType_money() {
                return type_money;
            }

            public void setType_money(String type_money) {
                this.type_money = type_money;
            }

            public long getBonus_id() {
                return bonus_id;
            }

            public void setBonus_id(long bonus_id) {
                this.bonus_id = bonus_id;
            }
        }
        private List list;

        public long getStatus() {
            return status;
        }

        public void setStatus(long status) {
            this.status = status;
        }

        public List getList() {
            return list;
        }

        public void setList(List list) {
            this.list = list;
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
