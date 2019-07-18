package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.AkVideoBean;
import com.zthx.npj.net.been.BuyVideoBean;
import com.zthx.npj.net.been.ConfirmSupplyBean;
import com.zthx.npj.net.been.GoodsDetailBean;
import com.zthx.npj.net.been.NullBean;
import com.zthx.npj.net.been.PayVideoBean;
import com.zthx.npj.net.been.SupplyBuy2Bean;
import com.zthx.npj.net.been.SupplyListBean;
import com.zthx.npj.net.been.UploadCommentBean;
import com.zthx.npj.net.been.UploadCompanyBean;
import com.zthx.npj.net.been.UploadPurchaseBean;
import com.zthx.npj.net.been.UploadSupplyBean;
import com.zthx.npj.net.been.VideoInfoBean;
import com.zthx.npj.net.netutils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by huangxin on 2019/6/15.
 */

public class DiscoverSubscribe {

    /**
     * 获取系统解决方案列表
     * @param subscriber
     */
    public static void getSolutionList( DisposableObserver<ResponseBody> subscriber) {
        NullBean bean = new NullBean();
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getSolutionListForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取系统解决方案视频列表
     * @param id
     * @param subscriber
     */
    public static void getSolutionVideoList(String id, DisposableObserver<ResponseBody> subscriber) {
        GoodsDetailBean bean = new GoodsDetailBean();
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getSolutionVideoListForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取农业知识列表
     * @param id
     * @param subscriber
     */
    public static void getKnowledgeList(String id, DisposableObserver<ResponseBody> subscriber) {
        GoodsDetailBean bean = new GoodsDetailBean();
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getKnowledgeListForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取农业知识视频列表
     * @param id
     * @param user_id
     * @param subscriber
     */
    public static void getKnowledgeVideoList(String id,String user_id, DisposableObserver<ResponseBody> subscriber) {
        AkVideoBean bean = new AkVideoBean();
        bean.setId(id);
        bean.setUser_id(user_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getKnowledgeVideoListForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取视频信息
     * @param id
     * @param status
     * @param user_id
     * @param subscriber
     */
    public static void getVideoInfo(String id,String status, String user_id, DisposableObserver<ResponseBody> subscriber) {
        VideoInfoBean bean = new VideoInfoBean();
        bean.setId(id);
        bean.setUser_id(user_id);
        bean.setStatus(status);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getVideoInfoForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 上传评论
     * @param list_id
     * @param content
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void uploadComment(String list_id,String content, String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        UploadCommentBean bean = new UploadCommentBean();
        bean.setContent(content);
        bean.setList_id(list_id);
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().uploadVideoCommentForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 购买课程
     * @param list_id
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void buyVideo(String list_id, String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        BuyVideoBean bean = new BuyVideoBean();
        bean.setList_id(list_id);
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().buyVideoForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应信息发布上传
     * @param bean
     * @param subscriber
     */
    public static void uploadSupply(UploadSupplyBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().uploadSupplyForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 采购信息发布上传
     * @param bean
     * @param subscriber
     */
    public static void uploadPurchase(UploadPurchaseBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().uploadPurchaseForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应列表
     * @param bean
     * @param subscriber
     */
    public static void supplyList(SupplyListBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().supplyListForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 需求列表
     * @param bean
     * @param subscriber
     */
    public static void needList(SupplyListBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().needListForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }


    /**
     * 供应详情
     * @param id
     * @param subscriber
     */
    public static void supplyDetail(String id, DisposableObserver<ResponseBody> subscriber) {
        GoodsDetailBean bean = new GoodsDetailBean();
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().supplyDetalForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 求购详情
     * @param id
     * @param subscriber
     */
    public static void needDetail(String id, DisposableObserver<ResponseBody> subscriber) {
        GoodsDetailBean bean = new GoodsDetailBean();
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().needDetalForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应订单确认
     * @param user_id
     * @param supply_id
     * @param token
     * @param subscriber
     */
    public static void confirmSupply(String user_id, String supply_id,String token, DisposableObserver<ResponseBody> subscriber) {
        ConfirmSupplyBean bean = new ConfirmSupplyBean();
        bean.setSupply_id(supply_id);
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().confirmSupplyForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 课程购买支付
     * @param pay_code
     * @param order_sn
     * @param pay_money
     * @param subscriber
     */
    public static void payVideo(String pay_code,String order_sn,String pay_money, DisposableObserver<ResponseBody> subscriber) {
        PayVideoBean bean=new PayVideoBean();
        bean.setPay_code(pay_code);
        bean.setOrder_sn(order_sn);
        bean.setPay_money(pay_money);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().payVideo(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应商品立即购买
     * @param bean
     * @param subscriber
     */
    public static void supplyBuy2(SupplyBuy2Bean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setGoods_id(bean.getGoods_id());
        bean.setGoods_num(bean.getGoods_num());
        bean.setPay_code(bean.getPay_code());
        bean.setAddress_id(bean.getAddress_id());
        bean.setShipping_fee(bean.getShipping_fee());
        bean.setRemark(bean.getRemark());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().supplyBuy2(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }


}
