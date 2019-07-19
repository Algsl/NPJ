package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.BannerBean;
import com.zthx.npj.net.been.CategoryBean;
import com.zthx.npj.net.been.CommentBean;
import com.zthx.npj.net.been.GoodsDetailBean;
import com.zthx.npj.net.been.GoodsListBean;
import com.zthx.npj.net.been.LocalStoreBean;
import com.zthx.npj.net.been.RecommendBean;
import com.zthx.npj.net.been.SearchBean;
import com.zthx.npj.net.been.StoreDetailBean;
import com.zthx.npj.net.been.UserBean;
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
     * @param keyword
     * @param subscriber
     */
    public static void getSearchResult(String keyword, DisposableObserver<ResponseBody> subscriber) {
        SearchBean bean = new SearchBean();
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

}
