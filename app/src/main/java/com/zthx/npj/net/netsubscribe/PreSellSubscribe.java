package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.CommentBean;
import com.zthx.npj.net.been.ConfirmPreSellBean;
import com.zthx.npj.net.been.GoodsDetailBean;
import com.zthx.npj.net.been.PreSellBean;
import com.zthx.npj.net.been.YsBuyOneBean;
import com.zthx.npj.net.netutils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by huangxin on 2019/6/4.
 */

public class PreSellSubscribe {

    /**
     * 获取新品众筹列表
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
     * 获取新品众筹详情
     * @param user_id
     * @param id
     * @param subscriber
     */
    public static void getPreSellDetail(String id,String user_id, DisposableObserver<ResponseBody> subscriber) {
        GoodsDetailBean bean = new GoodsDetailBean();
        bean.setId(id);
        bean.setUser_id(user_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getPreSellDetailForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 新品众筹确认订单
     * @param bean
     * @param subscriber
     */
    public static void getConfirmPreSell(ConfirmPreSellBean bean,DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().confirmPreSellForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 新品众筹生成订单
     * @param bean
     * @param subscriber
     */
    public static void ysBuyOne(YsBuyOneBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setAtt_id(bean.getAtt_id());
        bean.setAddress_id(bean.getAddress_id());
        bean.setPay_code(bean.getPay_code());
        bean.setPre_id(bean.getPre_id());
        bean.setRemark(bean.getRemark());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().ysBuyOne(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }
}
