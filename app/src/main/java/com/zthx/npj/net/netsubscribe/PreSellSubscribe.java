package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.CommentBean;
import com.zthx.npj.net.been.ConfirmPreSellBean;
import com.zthx.npj.net.been.GoodsDetailBean;
import com.zthx.npj.net.been.PreSellBean;
import com.zthx.npj.net.netutils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by huangxin on 2019/6/4.
 */

public class PreSellSubscribe {

    /**
     * 获取新品预售列表
     * @
     * @param subscriber
     */
    public static void getPreSell(String type, DisposableObserver<ResponseBody> subscriber) {

        PreSellBean bean = new PreSellBean();
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getPreSellListForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取新品预售详情
     * @param id
     * @param subscriber
     */
    public static void getPreSellDetail(String id, DisposableObserver<ResponseBody> subscriber) {
        GoodsDetailBean bean = new GoodsDetailBean();
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getPreSellDetailForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 新品预售确认订单
     * @param bean
     * @param subscriber
     */
    public static void getConfirmPreSell(ConfirmPreSellBean bean,DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().confirmPreSellForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }
}
