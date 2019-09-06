package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.EditInviterBean;
import com.zthx.npj.net.been.GiftBuyOneBean;
import com.zthx.npj.net.been.GiftDetailBean;
import com.zthx.npj.net.been.GiftListBean;
import com.zthx.npj.net.been.LocalSpokesmanBeen;
import com.zthx.npj.net.been.PayBean;
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
        bean.setUser_id(id);
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
        bean.setUser_id(id);
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

    /**
     * 购买
     * @param pay_code
     * @param order_sn
     * @param pay_money
     * @param subscriber
     */
    public static void pay(String pay_code,String order_sn,String pay_money, DisposableObserver<ResponseBody> subscriber) {
        PayBean bean=new PayBean();
        bean.setPay_code(pay_code);
        bean.setOrder_sn(order_sn);
        bean.setPay_money(pay_money);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().pay(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 购买礼包生成订单
     * @param bean
     * @param subscriber
     */
    public static void giftBuyOne(GiftBuyOneBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().giftBuyOne(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 修改联系人
     * @param user_id
     * @param token
     * @param mobile
     * @param subscriber
     */
    public static void editInviter(String user_id,String token,String mobile, DisposableObserver<ResponseBody> subscriber) {
        EditInviterBean bean=new EditInviterBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setMobile(mobile);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().editInviter(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

}
