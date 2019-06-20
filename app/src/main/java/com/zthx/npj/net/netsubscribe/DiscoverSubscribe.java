package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.AkVideoBean;
import com.zthx.npj.net.been.BuyVideoBean;
import com.zthx.npj.net.been.GoodsDetailBean;
import com.zthx.npj.net.been.NullBean;
import com.zthx.npj.net.been.UploadCommentBean;
import com.zthx.npj.net.been.UploadCompanyBean;
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
}
