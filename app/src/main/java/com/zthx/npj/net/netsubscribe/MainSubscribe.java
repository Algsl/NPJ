package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.entity.StoreInfo;
import com.zthx.npj.net.been.BannerBean;
import com.zthx.npj.net.been.CategoryBean;
import com.zthx.npj.net.been.ChildHomeBean;
import com.zthx.npj.net.been.CommentBean;
import com.zthx.npj.net.been.DelHistoryBean;
import com.zthx.npj.net.been.GoodsByCateBean;
import com.zthx.npj.net.been.GoodsDetailBean;
import com.zthx.npj.net.been.GoodsListBean;
import com.zthx.npj.net.been.HistoryBean;
import com.zthx.npj.net.been.HotSearchBean;
import com.zthx.npj.net.been.LocalStoreBean;
import com.zthx.npj.net.been.OrderPushBean;
import com.zthx.npj.net.been.RecommendBean;
import com.zthx.npj.net.been.SearchBean;
import com.zthx.npj.net.been.SearchStoreBean;
import com.zthx.npj.net.been.SearchStoreGoodsBean;
import com.zthx.npj.net.been.StoreDetailBean;
import com.zthx.npj.net.been.StoreGoodsListBean;
import com.zthx.npj.net.been.StoreInfoBean;
import com.zthx.npj.net.been.UserBean;
import com.zthx.npj.net.been.VideoInfoBean;
import com.zthx.npj.net.been.VideoOrderBean;
import com.zthx.npj.net.netutils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by huangxin on 2019/5/30.
 */

public class MainSubscribe {

    /**
     * 获取轮训图
     * @param type
     * @param subscriber
     */
    public static void getMainBanner(String type, DisposableObserver<ResponseBody> subscriber) {
        BannerBean bean = new BannerBean();
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getBannerForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 精品推荐
     * @param id
     * @param page
     * @param subscriber
     */
    public static void getRecommend(String id, String page, DisposableObserver<ResponseBody> subscriber){
        RecommendBean bean = new RecommendBean();
        bean.setPage(page);
        bean.setUser_id(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getRecommendForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 搜索结果
     * @param user_id
     * @param keyword
     * @param subscriber
     */
    public static void getSearchResult(String user_id,String keyword, DisposableObserver<ResponseBody> subscriber) {
        SearchBean bean = new SearchBean();
        bean.setUser_id(user_id);
        bean.setKeyword(keyword);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getSearchResultForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取商品详情
     * @param id
     * @param subscriber
     */
    public static void getGoodsDetail(String id, DisposableObserver<ResponseBody> subscriber) {
        GoodsDetailBean bean = new GoodsDetailBean();
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getGoodsDetailForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取附近商家列表
     * @param bean
     * @param subscriber
     */
    public static void getLocalStore(LocalStoreBean bean, DisposableObserver<ResponseBody> subscriber){
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getLocalStoreForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取商家详情
     * @param id
     * @param subscriber
     */
    public static void getStoreDetail(String id, DisposableObserver<ResponseBody> subscriber) {
        StoreDetailBean bean = new StoreDetailBean();
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getStoreDetailForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取评价
     * @param id
     * @param type
     * @param subscriber
     */
    public static void getStoreComment(String id,String type, DisposableObserver<ResponseBody> subscriber) {
        CommentBean bean = new CommentBean();
        bean.setId(id);
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getCommentForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取商品分类
     * @param subscriber
     */
    public static void category(DisposableObserver<ResponseBody> subscriber) {
        CategoryBean bean=new CategoryBean();
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().category(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 分类列表页
     * @param cate_id
     * @param page
     * @param subscriber
     */
    public static void goodsList(String cate_id,String page,DisposableObserver<ResponseBody> subscriber) {
        GoodsListBean bean=new GoodsListBean();
        bean.setCate_id(cate_id);
        bean.setPage(page);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().goodsList(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 订单消息推送
     * @param bean
     * @param subscriber
     */
    public static void orderPush(OrderPushBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().orderPush(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 搜索历史
     * @param user_id
     * @param subscriber
     */
    public static void history(String user_id, DisposableObserver<ResponseBody> subscriber) {
        HistoryBean bean=new HistoryBean();
        bean.setUser_id(user_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().history(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 删除搜索历史
     * @param user_id
     * @param subscriber
     */
    public static void delHistory(String user_id, DisposableObserver<ResponseBody> subscriber) {
        DelHistoryBean bean=new DelHistoryBean();
        bean.setUser_id(user_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().delHistory(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 热门搜索
     * @param bean
     * @param subscriber
     */
    public static void hotSearch(HotSearchBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().hotSearch(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 搜索商家
     * @param lng
     * @param lat
     * @param title
     * @param subscriber
     */
    public static void searchStore(String lng,String lat,String title, DisposableObserver<ResponseBody> subscriber) {
        SearchStoreBean bean=new SearchStoreBean();
        bean.setLng(lng);
        bean.setLat(lat);
        bean.setTitle(title);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().searchStore(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 店铺信息
     * @param store_id
     * @param user_id
     * @param subscriber
     */
    public static void storeInfo(String store_id,String user_id, DisposableObserver<ResponseBody> subscriber) {
        StoreInfoBean bean=new StoreInfoBean();
        bean.setStore_id(store_id);
        bean.setUser_id(user_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().storeInfo(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 店铺商品
     * @param store_id
     * @param type
     * @param subscriber
     */
    public static void storeGoodsList(String store_id,String type, DisposableObserver<ResponseBody> subscriber) {
        StoreGoodsListBean bean=new StoreGoodsListBean();
        bean.setStore_id(store_id);
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().storeGoodsList(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 店铺搜索商品
     * @param store_id
     * @param keyword
     * @param subscriber
     */
    public static void searchStoreGoods(String store_id,String keyword, DisposableObserver<ResponseBody> subscriber) {
        SearchStoreGoodsBean bean=new SearchStoreGoodsBean();
        bean.setStore_id(store_id);
        bean.setKeyword(keyword);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().searchStoreGoods(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 店铺分类商品
     * @param store_id
     * @param cate_id
     * @param type
     * @param subscriber
     */
    public static void goodsByCate(String store_id,String cate_id,String type, DisposableObserver<ResponseBody> subscriber) {
        GoodsByCateBean bean=new GoodsByCateBean();
        bean.setStore_id(store_id);
        bean.setCate_id(cate_id);
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().goodsByCate(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 下级用户首页
     * @param user_id
     * @param page
     * @param subscriber
     */
    public static void childHome(String user_id,String page, DisposableObserver<ResponseBody> subscriber) {
        ChildHomeBean bean=new ChildHomeBean();
        bean.setUser_id(user_id);
        bean.setPage(page);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().childHome(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }
}
