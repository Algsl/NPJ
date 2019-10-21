package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.CertBean;
import com.zthx.npj.net.been.CompanyBean;
import com.zthx.npj.net.been.GiftListBean;
import com.zthx.npj.net.been.IntegrityBean;
import com.zthx.npj.net.been.MarginBean;
import com.zthx.npj.net.been.RealNameBean;
import com.zthx.npj.net.been.StockBean;
import com.zthx.npj.net.been.UpLoadMyCertBean;
import com.zthx.npj.net.been.UploadCaigouBean;
import com.zthx.npj.net.been.UploadChengXinCertBean;
import com.zthx.npj.net.been.UploadCompanyBean;
import com.zthx.npj.net.been.ZiZhi2Bean;
import com.zthx.npj.net.been.ZiZhiBean;
import com.zthx.npj.net.netutils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by huangxin on 2019/6/12.
 */

public class CertSubscribe {

    /**
     * 获取我的认证
     * @param id
     * @param token
     * @param subscriber
     */
    public static void getMyCert(String id,String token, DisposableObserver<ResponseBody> subscriber) {
        CertBean bean=new CertBean();
        bean.setUser_id(id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getMyCertForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 实名认证上传
     * @param bean
     * @param subscriber
     */
    public static void upLoadMyCert(UpLoadMyCertBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().upLoadMyCertForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 实名认证审核未通过
     * @param bean
     * @param subscriber
     */
    public static void upLoadMyCert3(UpLoadMyCertBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().upLoadMyCertForBody3(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 是否以进行实名认证
     * @param subscriber
     */
    public static void isPersonCertDone(String id,String token, DisposableObserver<ResponseBody> subscriber) {
        CertBean bean=new CertBean();
        bean.setUser_id(id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().isPersonCertDoneForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 企业认证上传
     * @param bean
     * @param subscriber
     */
    public static void upLoadCompanyCert(UploadCompanyBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().uploadCompanyForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 企业认证重新上传
     * @param bean
     * @param subscriber
     */
    public static void upLoadCompanyCert4(UploadCompanyBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().uploadCompanyForBody4(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 采购商上传
     * @param bean
     * @param subscriber
     */
    public static void upLoadCaigouCert(UploadCaigouBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().uploadCaigouForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 采购商重新上传
     * @param bean
     * @param subscriber
     */
    public static void upLoadCaigouCert3(UploadCaigouBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().uploadCaigouForBody3(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 诚信认证
     * @param id
     * @param token
     * @param subscriber
     */
    public static void isChengXinAlreadyCert(String id,String token, DisposableObserver<ResponseBody> subscriber) {
        CertBean bean=new CertBean();
        bean.setUser_id(id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().isChengxinCerAlreadyForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 开始诚信认证
     * @param id
     * @param token
     * @param subscriber
     */
    public static void isChengXinAlready2Cert(String id,String token, DisposableObserver<ResponseBody> subscriber) {
        CertBean bean=new CertBean();
        bean.setUser_id(id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().isChengxinCerAlready2ForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 上传诚信认证
     * @param bean
     * @param subscriber
     */
    public static void uploadChengxinCert(UploadChengXinCertBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().uploadChengxinCertForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 资质认证
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void zizhi(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        ZiZhiBean bean=new ZiZhiBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().zizhi(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 资质认证判断
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void zizhi4(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        ZiZhiBean bean=new ZiZhiBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().zizhi4(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 资质认证确认提交
     * @param bean
     * @param subscriber
     */
    public static void zizhi2(ZiZhi2Bean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().zizhi2(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 资质认证重新提交
     * @param bean
     * @param subscriber
     */
    public static void zizhi3(ZiZhi2Bean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().zizhi3(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }




    /**
     * 实人认证
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void realName(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        RealNameBean bean=new RealNameBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().realName(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 企业认证
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void company(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        CompanyBean bean=new CompanyBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().company(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 采购认证
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void stock(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        StockBean bean=new StockBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().stock(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 诚信认证
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void integrity(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        IntegrityBean bean=new IntegrityBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().integrity(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 退换保证金
     * @param user_id
     * @param token
     * @param money
     * @param subscriber
     */
    public static void margin(String user_id,String token,String money, DisposableObserver<ResponseBody> subscriber) {
        MarginBean bean=new MarginBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setMoney(money);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().margin(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

}
