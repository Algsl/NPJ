package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.GoodsDetailBean;
import com.zthx.npj.net.been.NullBean;
import com.zthx.npj.net.netutils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by huangxin on 2019/6/11.
 */

public class SecKillSubscribe {

    /**
     * 获取当天秒杀列表
     * @param subscriber
     */
    public static void getSecKillTodayList(DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getSecKillTodayForBody(new NullBean());
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取已结束秒杀列表
     * @param subscriber
     */
    public static void getSecKillOverList(DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getSecKillTodayForBody(new NullBean());
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     *获取即将开始秒杀列表
     * @param subscriber
     */
    public static void getSecKillStartList(DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getSecKillStartForBody(new NullBean());
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取商品详情
     * @param id
     * @param subscriber
     */
    public static void getSecKillGoodsDetail(String id, DisposableObserver<ResponseBody> subscriber) {
        GoodsDetailBean bean = new GoodsDetailBean();
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getSecKillGoodsDetailForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }
}
