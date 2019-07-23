package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.GiftDetailBean;
import com.zthx.npj.net.been.GiftListBean;
import com.zthx.npj.net.been.LocalSpokesmanBeen;
import com.zthx.npj.net.been.ReferrerBean;
import com.zthx.npj.net.netutils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by huangxin on 2019/6/11.
 */

public class GiftSubscribe {

    /**
     * 获取礼包列表
     * @param id
     * @param token
     * @param subscriber
     */
    public static void getGiftList(String id,String token, DisposableObserver<ResponseBody> subscriber) {
        GiftListBean bean = new GiftListBean();
        bean.setId(id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getGiftListForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取代言人权益
     * @param id
     * @param token
     * @param subscriber
     */
    public static void getSpokesmanQuan(String id,String token, DisposableObserver<ResponseBody> subscriber) {
        GiftListBean bean = new GiftListBean();
        bean.setId(id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getSpokesmanQuanForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取礼包详情
     * @param id
     * @param token
     * @param giftId
     * @param subscriber
     */
    public static void getGiftDetail(String id,String token, String giftId, DisposableObserver<ResponseBody> subscriber) {
        GiftDetailBean bean = new GiftDetailBean();
        bean.setUser_id(id);
        bean.setGift_id(giftId);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getGiftDetailForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }


    /**
     * 获取礼包确认
     * @param id
     * @param token
     * @param giftId
     * @param subscriber
     */
    public static void getGiftConfirm(String id,String token, String giftId, DisposableObserver<ResponseBody> subscriber) {
        GiftDetailBean bean = new GiftDetailBean();
        bean.setUser_id(id);
        bean.setGift_id(giftId);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getGiftConfirmForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 联系推荐人
     * @param id
     * @param token
     * @param subscriber
     */
    public static void referrer(String id,String token, DisposableObserver<ResponseBody> subscriber) {
        ReferrerBean bean=new ReferrerBean();
        bean.setUser_id(id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().referrer(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

}
