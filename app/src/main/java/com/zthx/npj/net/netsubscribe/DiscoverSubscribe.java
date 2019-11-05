package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.AddPurchaseBean;
import com.zthx.npj.net.been.AddSupplyBean;
import com.zthx.npj.net.been.AkVideoBean;
import com.zthx.npj.net.been.AttentionBean;
import com.zthx.npj.net.been.BaoJiaBean;
import com.zthx.npj.net.been.BuyVideoBean;
import com.zthx.npj.net.been.ConfirmSupplyBean;
import com.zthx.npj.net.been.GoodsDetailBean;
import com.zthx.npj.net.been.KnowledgeBean;
import com.zthx.npj.net.been.LookUserBean;
import com.zthx.npj.net.been.NewsBean;
import com.zthx.npj.net.been.NewsListBean;
import com.zthx.npj.net.been.NullBean;
import com.zthx.npj.net.been.OtherSearchBean;
import com.zthx.npj.net.been.PayVideoBean;
import com.zthx.npj.net.been.QiuGouBean;
import com.zthx.npj.net.been.SearchSolutionBean;
import com.zthx.npj.net.been.SolutionSearchBean;
import com.zthx.npj.net.been.SupplyBuy2Bean;
import com.zthx.npj.net.been.SupplyListBean;
import com.zthx.npj.net.been.SupplyPayBean;
import com.zthx.npj.net.been.SupplySearchBean;
import com.zthx.npj.net.been.TwjcListBean;
import com.zthx.npj.net.been.UploadCommentBean;
import com.zthx.npj.net.been.VideoInfoBean;
import com.zthx.npj.net.been.VideoOrderBean;
import com.zthx.npj.net.been.XTSZBean;
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
    public static void getKnowledgeList(String user_id,String id, DisposableObserver<ResponseBody> subscriber) {
        GoodsDetailBean bean = new GoodsDetailBean();
        bean.setId(id);
        bean.setUser_id(user_id);
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

    /**
     * 农业知识搜索
     * @param title
     * @param subscriber
     */
    public static void solutionSearch(String title, DisposableObserver<ResponseBody> subscriber) {
        SolutionSearchBean bean=new SolutionSearchBean();
        bean.setTitle(title);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().solutionSearch(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 疑难杂症文章列表
     * @param type
     * @param subscriber
     */
    public static void newsList(String type, DisposableObserver<ResponseBody> subscriber) {
        NewsListBean bean=new NewsListBean();
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().newsList(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 其他类型搜索
     * @param type
     * @param subscriber
     */
    public static void otherSearch(String type, DisposableObserver<ResponseBody> subscriber) {
        OtherSearchBean bean=new OtherSearchBean();
        bean.setTitle(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().otherSearch(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 疑难杂症文章详情
     * @param id
     * @param subscriber
     */
    public static void newsDetail(String id, DisposableObserver<ResponseBody> subscriber) {
        NewsBean bean=new NewsBean();
        bean.setId(Long.valueOf(id));
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().newsDetail(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 课程购买支付订单
     * @param bean
     * @param subscriber
     */
    public static void videoOrder(VideoOrderBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setList_id(bean.getList_id());
        bean.setPay_code(bean.getPay_code());
        bean.setRemark(bean.getRemark());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().videoOrder(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 发布供应信息
     * @param bean
     * @param subscriber
     */
    public static void addSupply(AddSupplyBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setGoods_img(bean.getGoods_img());
        bean.setTitle(bean.getTitle());
        bean.setGoods_name(bean.getGoods_name());
        bean.setGoods_num(bean.getGoods_num());
        bean.setGoods_unit(bean.getGoods_unit());
        bean.setCity(bean.getCity());
        bean.setPrice(bean.getPrice());
        bean.setContent(bean.getContent());
        bean.setLat(bean.getLat());
        bean.setLng(bean.getLng());
        bean.setBuy_num(bean.getBuy_num());
        bean.setIs_top(bean.getIs_top());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().addSupply(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 发布采购信息
     * @param bean
     * @param subscriber
     */
    public static void addPurchase(AddPurchaseBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setImg(bean.getImg());
        bean.setTitle(bean.getTitle());
        bean.setAmount(bean.getAmount());
        bean.setUnit(bean.getUnit());
        bean.setCity(bean.getCity());
        bean.setMin_price(bean.getMin_price());
        bean.setMax_price(bean.getMax_price());
        bean.setContent(bean.getContent());
        bean.setLat(bean.getLat());
        bean.setLng(bean.getLng());
        bean.setIs_top(bean.getIs_top());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().addPurchase(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我要报价
     * @param bean
     * @param subscriber
     */
    public static void baojia(BaoJiaBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().baoJia(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 图文教程
     * @param id
     * @param subscriber
     */
    public static void twjcList(String id, DisposableObserver<ResponseBody> subscriber) {
        TwjcListBean bean=new TwjcListBean();
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().twjcList(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 查看名片
     * @param user_id
     * @param subscriber
     */
    public static void lookUser(String user_id, DisposableObserver<ResponseBody> subscriber) {
        LookUserBean bean=new LookUserBean();
        bean.setUser_id(user_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().lookUser(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 关注
     * @param user_id
     * @param token
     * @param att_user_id
     * @param subscriber
     */
    public static void attention(String user_id,String token,String att_user_id, DisposableObserver<ResponseBody> subscriber) {
        AttentionBean bean=new AttentionBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setAtt_user_id(att_user_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().attention(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 解决方案搜索
     * @param keyword
     * @param subscriber
     */
    public static void searchSolution(String keyword, DisposableObserver<ResponseBody> subscriber) {
        SearchSolutionBean bean=new SearchSolutionBean();
        bean.setKeyword(keyword);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().searchSolution(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应搜索
     * @param keyword
     * @param subscriber
     */
    public static void supplySearch(String keyword, DisposableObserver<ResponseBody> subscriber) {
        SupplySearchBean bean=new SupplySearchBean();
        bean.setKeyword(keyword);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().supplySearch(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 求购搜索
     * @param keyword
     * @param subscriber
     */
    public static void qiugouSearch(String keyword, DisposableObserver<ResponseBody> subscriber) {
        QiuGouBean bean=new QiuGouBean();
        bean.setKeyword(keyword);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().qiugouSearch(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 农业知识分类
     * @param subscriber
     */
    public static void knowledge( DisposableObserver<ResponseBody> subscriber) {
        KnowledgeBean bean=new KnowledgeBean();
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().knowledge(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应商品支付
     * @param pay_code
     * @param order_sn
     * @param pay_money
     * @param type
     * @param subscriber
     */
    public static void supplyPay(String pay_code,String order_sn,String pay_money,String type, DisposableObserver<ResponseBody> subscriber) {
        SupplyPayBean bean=new SupplyPayBean();
        bean.setPay_code(pay_code);
        bean.setOrder_sn(order_sn);
        bean.setPay_money(pay_money);
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().supplyPay(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     *
     * @param subscriber
     */
    public static void xtsz(DisposableObserver<ResponseBody> subscriber) {
        XTSZBean bean=new XTSZBean();
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().xtsz(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }
}
