package com.zthx.npj.net.been;

public class InComeResponseBean extends BaseReponseBean{
    public class DataBean{
        private String income_amount;
        private long zt_push;
        private long jt_push;
        private long all_push;
        private String store_money;
        private String reward;
        private String extracted;

        public String getIncome_amount() {
            return income_amount;
        }

        public void setIncome_amount(String income_amount) {
            this.income_amount = income_amount;
        }

        public long getZt_push() {
            return zt_push;
        }

        public void setZt_push(long zt_push) {
            this.zt_push = zt_push;
        }

        public long getJt_push() {
            return jt_push;
        }

        public void setJt_push(long jt_push) {
            this.jt_push = jt_push;
        }

        public long getAll_push() {
            return all_push;
        }

        public void setAll_push(long all_push) {
            this.all_push = all_push;
        }

        public String getStore_money() {
            return store_money;
        }

        public void setStore_money(String store_money) {
            this.store_money = store_money;
        }

        public String getReward() {
            return reward;
        }

        public void setReward(String reward) {
            this.reward = reward;
        }

        public String getExtracted() {
            return extracted;
        }

        public void setExtracted(String extracted) {
            this.extracted = extracted;
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
