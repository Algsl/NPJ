package com.zthx.npj.net.been;

import java.util.ArrayList;

public class BankCardResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private String card_number;
        private String bank_name;
        private String bank_logo;
        private String bank_bg;

        public String getBank_logo() {
            return bank_logo;
        }

        public void setBank_logo(String bank_logo) {
            this.bank_logo = bank_logo;
        }

        public String getBank_bg() {
            return bank_bg;
        }

        public void setBank_bg(String bank_bg) {
            this.bank_bg = bank_bg;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
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
