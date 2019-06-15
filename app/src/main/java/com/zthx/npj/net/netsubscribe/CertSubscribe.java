package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.GiftListBean;
import com.zthx.npj.net.been.UpLoadMyCertBean;
import com.zthx.npj.net.been.UploadCaigouBean;
import com.zthx.npj.net.been.UploadChengXinCertBean;
import com.zthx.npj.net.been.UploadCompanyBean;
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
        GiftListBean bean = new GiftListBean();
        bean.setId(id);
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
     * 是否以进行实名认证
     * @param subscriber
     */
    public static void isPersonCertDone(String id,String token, DisposableObserver<ResponseBody> subscriber) {
        GiftListBean bean = new GiftListBean();
        bean.setId(id);
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
     * 采购商上传
     * @param bean
     * @param subscriber
     */
    public static void upLoadCaigouCert(UploadCaigouBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().uploadCaigouForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 诚信认证
     * @param id
     * @param token
     * @param subscriber
     */
    public static void isChengXinAlreadyCert(String id,String token, DisposableObserver<ResponseBody> subscriber) {
        GiftListBean bean = new GiftListBean();
        bean.setId(id);
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
        GiftListBean bean = new GiftListBean();
        bean.setId(id);
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

}
