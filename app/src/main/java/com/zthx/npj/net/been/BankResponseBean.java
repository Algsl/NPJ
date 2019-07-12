package com.zthx.npj.net.been;

import java.util.ArrayList;

public class BankResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private String bank_name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }
    }
    public ArrayList<DataBean> data;

    public ArrayList<BankResponseBean.DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }
}
