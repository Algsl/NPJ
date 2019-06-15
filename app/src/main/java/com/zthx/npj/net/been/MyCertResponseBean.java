package com.zthx.npj.net.been;

/**
 * Created by huangxin on 2019/6/12.
 */

public class MyCertResponseBean extends BaseReponseBean {

    private int name_cert;
    private int company_cert;
    private int stock_cert;
    private int integrity_cert;

    public int getName_cert() {
        return name_cert;
    }

    public void setName_cert(int name_cert) {
        this.name_cert = name_cert;
    }

    public int getCompany_cert() {
        return company_cert;
    }

    public void setCompany_cert(int company_cert) {
        this.company_cert = company_cert;
    }

    public int getStock_cert() {
        return stock_cert;
    }

    public void setStock_cert(int stock_cert) {
        this.stock_cert = stock_cert;
    }

    public int getIntegrity_cert() {
        return integrity_cert;
    }

    public void setIntegrity_cert(int integrity_cert) {
        this.integrity_cert = integrity_cert;
    }
}
