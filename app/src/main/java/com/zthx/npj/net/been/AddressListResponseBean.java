package com.zthx.npj.net.been;

import java.util.ArrayList;

public class AddressListResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private String consignee;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getHouse_number() {
            return house_number;
        }

        public void setHouse_number(String house_number) {
            this.house_number = house_number;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public long getIs_default() {
            return is_default;
        }

        public void setIs_default(long is_default) {
            this.is_default = is_default;
        }

        public String getAlladdress() {
            return alladdress;
        }

        public void setAlladdress(String alladdress) {
            this.alladdress = alladdress;
        }

        private String address;
        private String house_number;
        private String mobile;
        private long   is_default;
        private String alladdress;
    }
    private ArrayList<DataBean> data;

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }
}
