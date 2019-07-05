package com.zthx.npj.net.been;

public class AddressInfoResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private String consignee;
        private String address;
        private String mobile;
        private String house_number;
        private long is_default;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getHouse_number() {
            return house_number;
        }

        public void setHouse_number(String house_number) {
            this.house_number = house_number;
        }

        public long getIs_default() {
            return is_default;
        }

        public void setIs_default(long is_default) {
            this.is_default = is_default;
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
