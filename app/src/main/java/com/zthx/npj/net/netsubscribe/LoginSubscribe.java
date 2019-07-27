package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.AuthLoginBean;
import com.zthx.npj.net.been.AuthLoginByMoBileBean;
import com.zthx.npj.net.been.InvitationBean;
import com.zthx.npj.net.been.LocalSpokesmanBeen;
import com.zthx.npj.net.been.LocalSpokesmanResponseBean;
import com.zthx.npj.net.been.MsgCodeBeen;
import com.zthx.npj.net.been.PhoneLoginBean;
import com.zthx.npj.net.netutils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by huangxin on 2019/5/28.
 */

public class LoginSubscribe {


    /**
     * 获取附近代言人
     * @param lat
     * @param lng
     * @param subscriber
     */
    public static void getLocalSpokesman(String lat,String lng, DisposableObserver<ResponseBody> subscriber) {
        LocalSpokesmanBeen bean = new LocalSpokesmanBeen();
        bean.setLng(lng);
        bean.setLat(lat);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getLocalSpokesmanForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 发送短信验证码
     * @param mobile
     * @param subscriber
     */
    public static void getMobileCode(String mobile, DisposableObserver<ResponseBody> subscriber) {
        MsgCodeBeen bean = new MsgCodeBeen();
        bean.setMobile(mobile);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getMobileCodeForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 用户登录
     * @param bean
     * @param subscriber
     */
    public static void MobileLogin(PhoneLoginBean bean, DisposableObserver<ResponseBody> subscriber){
        bean.setMobile(bean.getMobile());
        bean.setCode(bean.getCode());
        bean.setSession_id(bean.getSession_id());
        bean.setLat(bean.getLat());
        bean.setLng(bean.getLng());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mobileLoginForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 绑定邀请人
     * @param mobile
     * @param id
     * @param subscriber
     */
    public static void invitation(String mobile,String id,DisposableObserver<ResponseBody> subscriber){
        InvitationBean bean = new InvitationBean();
        bean.setMobile(mobile);
        bean.setUser_id(id);
        Observable<ResponseBody> observable = RetrofitFactory.getInstance().getHttpApi().invitationForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 第三方登录
     * @param bean
     * @param subscriber
     */
    public static void authLogin(AuthLoginBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setId(bean.getId());
        bean.setNick_name(bean.getNick_name());
        bean.setHead_img(bean.getHead_img());
        bean.setLat(bean.getLat());
        bean.setLng(bean.getLng());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().authLogin(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 第三方登录绑定手机号
     * @param bean
     * @param subscriber
     */
    public static void authLoginByMobile(AuthLoginByMoBileBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setMobile(bean.getMobile());
        bean.setCode(bean.getCode());
        bean.setSession_id(bean.getSession_id());
        bean.setUser_id(bean.getUser_id());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().authLoginbymobile(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }
}
