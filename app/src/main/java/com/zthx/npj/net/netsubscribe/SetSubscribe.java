package com.zthx.npj.net.netsubscribe;

import android.util.Log;

import com.zthx.npj.net.been.*;
import com.zthx.npj.net.netutils.RetrofitFactory;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by huangxin on 2019/6/12.
 */

public class SetSubscribe {


    /**
     * 上传单张图片
     * @param file
     * @param subscriber
     */
    public static void upLoadFile(File file,DisposableObserver<ResponseBody> subscriber) {
        UpLoadFileBean bean=new UpLoadFileBean();
        bean.setFile(file);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().upLoadFileForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }


    /**
     * 上传多张图片
     * @param bean
     * @param subscriber
     */
    public static void upLoadFiles(UploadPicsBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().uploadPicsForBody(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取用户信息
     * @param user_id
     * @param token
     */
    public static void getUserInfo(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        UserBean bean =new UserBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getUserInfo(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 修改用户头像
     * @param user_id
     * @param token
     * @param head_img
     */
    public static void editHeadImg(String user_id,String token,String head_img, DisposableObserver<ResponseBody> subscriber) {
        EditHeadimgBean bean=new EditHeadimgBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setHead_img(head_img);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().editHeadImg(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     *修改用户昵称和个性签名
     * @param user_id
     * @param token
     * @param type
     * @param title
     * @param subscriber
     */
    public static void editNickname(String user_id,String token,String type,String title, DisposableObserver<ResponseBody> subscriber) {
        EditNicknameBean bean=new EditNicknameBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setType(type);
        bean.setTitle(title);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().editNickname(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 添加收货地址
     * @param bean
     * @param subscriber
     */
    public static void addAddress(AddAddressBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().addAddress(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取收货地址列表
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void getAddressList(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        AddressListBean bean=new AddressListBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getAddressList(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取收货地址详情
     * @param user_id
     * @param token
     * @param address_id
     * @param subscriber
     */
    public static void getAddressInfo(String user_id,String token,String address_id, DisposableObserver<ResponseBody> subscriber) {
        AddressInfoBean bean=new AddressInfoBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setAddress_id(address_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getAddressInfo(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 修改收货地址
     * @param bean
     * @param subscriber
     */
    public static void editAddressInfo(EditAddressBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setAddress_id(bean.getAddress_id());
        bean.setConsignee(bean.getConsignee());
        bean.setAddress(bean.getAddress());
        bean.setMobile(bean.getMobile());
        bean.setHouse_number(bean.getHouse_number());
        bean.setIs_default(bean.getIs_default());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().editAddressInfo(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 删除收货地址
     * @param user_id
     * @param token
     * @param address_id
     * @param subscriber
     */
    public static void delAddress(String user_id,String token,String address_id, DisposableObserver<ResponseBody> subscriber) {
        DelAddressBean bean=new DelAddressBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setAddress_id(address_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().delAddress(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 设置默认地址
     * @param user_id
     * @param token
     * @param address_id
     * @param subscriber
     */
    public static void defaultAddress(String user_id,String token,String address_id, DisposableObserver<ResponseBody> subscriber) {
        DefaultAddressBean bean=new DefaultAddressBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setAddress_id(address_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().defaultAddress(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的店铺
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void myStore(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        MyStoreBean bean=new MyStoreBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().myStore(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 发布商品
     * @param bean
     * @param subscriber
     */
    public static void addGoods(AddGoodsBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setGoods_name(bean.getGoods_name());
        bean.setGoods_desc(bean.getGoods_desc());
        bean.setGoods_img(bean.getGoods_img());
        bean.setPlatform_price(bean.getPlatform_price());
        bean.setMember_price(bean.getMember_price());
        bean.setMarket_price(bean.getMarket_price());
        bean.setInventory(bean.getInventory());
        bean.setCate_id(bean.getCate_id());
        bean.setIs_free_shipping(bean.getIs_free_shipping());
        bean.setGoods_type(bean.getGoods_type());
        bean.setGoods_content(bean.getGoods_content());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().addGoods(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我发布的商品列表
     * @param user_id
     * @param token
     * @param type
     * @param subscriber
     */
    public static void myGoods(String user_id,String token,String type, DisposableObserver<ResponseBody> subscriber) {
        MyGoodsBean bean=new MyGoodsBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().myGoods(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我发布的商品信息
     * @param user_id
     * @param token
     * @param goods_id
     * @param subscriber
     */
    public static void goodsInfo(String user_id,String token,String goods_id, DisposableObserver<ResponseBody> subscriber) {
        GoodsInfoBean bean=new GoodsInfoBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setGoods_id(goods_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().goodsInfo(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 编辑我发布的商品
     * @param bean
     * @param subscriber
     */
    public static void editGoods(EditGoodsBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setGoods_id(bean.getGoods_id());
        bean.setGoods_name(bean.getGoods_name());
        bean.setGoods_desc(bean.getGoods_desc());
        bean.setGoods_img(bean.getGoods_img());
        bean.setPlatform_price(bean.getPlatform_price());
        bean.setMember_price(bean.getMember_price());
        bean.setMarket_price(bean.getMarket_price());
        bean.setInventory(bean.getInventory());
        bean.setCate_id(bean.getCate_id());
        bean.setIs_free_shipping(bean.getIs_free_shipping());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().editGoods(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 删除我发布的商品
     * @param user_id
     * @param token
     * @param goods_id
     * @param subscriber
     */
    public static void delGoods(String user_id,String token,String goods_id, DisposableObserver<ResponseBody> subscriber) {
        DelGoodsBean bean=new DelGoodsBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setGoods_id(goods_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().delGoods(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 上架和下架我发布的商品
     * @param user_id
     * @param token
     * @param goods_id
     * @param type
     * @param subscriber
     */
    public static void outGoods(String user_id,String token,String goods_id,String type, DisposableObserver<ResponseBody> subscriber) {
        OutGoodsBean bean=new OutGoodsBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setGoods_id(goods_id);
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().outGoods(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的商铺商品订单列表
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void myOrderList(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        MyOrderListBean bean=new MyOrderListBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().myOrderList(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的商铺商铺订单详情
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void myOrderDetail(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        MyOrderDetailBean bean=new MyOrderDetailBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().myOrderDetail(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的商铺商铺订单发货
     * @param bean
     * @param subscriber
     */
    public static void ship(ShipBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setOrder_id(bean.getOrder_id());
        bean.setExpress_id(bean.getExpress_id());
        bean.setExpress_number(bean.getExpress_number());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().ship(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的商铺商品订单同意退款
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void refund(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        RefundBean bean=new RefundBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().refund(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的商铺商品订单退款原因
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void refund2(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        RefundBean bean=new RefundBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().refund2(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 设置店铺信息
     * @param user_id
     * @param token
     * @param store_name
     * @param store_img
     * @param subscriber
     */
    public static void setStore(String user_id,String token,String store_name,String store_img, DisposableObserver<ResponseBody> subscriber) {
        SetStoreBean bean=new SetStoreBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setStore_name(store_name);
        bean.setStore_img(store_img);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().setStore(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的订单
     * @param user_id
     * @param token
     * @param order_state
     * @param subscriber
     */
    public static void myOrder(String user_id,String token,String order_state, DisposableObserver<ResponseBody> subscriber) {
        OrderBean bean=new OrderBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_state(order_state);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().order(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 取消订单
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void cancelOrder(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        CancelOrderBean bean=new CancelOrderBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().cancelOrder(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 删除订单
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void delOrder(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        DelOrderBean bean=new DelOrderBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().delOrder(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 待支付去付款确认订单
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void confirmOrder(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        ConfirmOrderBean bean=new ConfirmOrderBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().confirmOrder(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 待付款订单支付
     * @param bean
     * @param subscriber
     */
    public static void buy(BuyBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setAddress_id(bean.getAddress_id());
        bean.setOrder_id(bean.getOrder_id());
        bean.setPay_code(bean.getPay_code());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().buy(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 订单评价
     * @param bean
     * @param subscriber
     */
    public static void orderComment(OrderCommentBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setOrder_id(bean.getOrder_id());
        bean.setContent(bean.getContent());
        bean.setImg(bean.getImg());
        bean.setGoods_star(bean.getGoods_star());
        bean.setLogistics_star(bean.getLogistics_star());
        bean.setService_star(bean.getService_star());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().orderComment(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 申请退款
     * @param bean
     * @param subscriber
     */
    public static void applyRefund(ApplyRefundBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setOrder_id(bean.getOrder_id());
        bean.setRefund_state(bean.getRefund_state());
        bean.setRefund_reason(bean.getRefund_reason());
        bean.setRefund_price(bean.getRefund_price());
        bean.setRefund_desc(bean.getRefund_desc());
        bean.setRefund_img(bean.getRefund_img());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().applyRefund(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 查看物流
     * @param express_code
     * @param express_number
     * @param subscriber
     */
    public static void lookKD(String express_code,String express_number, DisposableObserver<ResponseBody> subscriber) {
        LookKDBean bean=new LookKDBean();
        bean.setExpress_code(express_code);
        bean.setExpress_number(express_number);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().lookKD(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 确认收货
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void receiveConfirm(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        ReceiveConfirmBean bean=new ReceiveConfirmBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().receiveConfirm(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的银行卡
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void bankCard(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        BankCardBean bean=new BankCardBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().bankCard(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 添加银行卡
     * @param bean
     * @param subscriber
     */
    public static void addBankCard(AddBankCardBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setCardholder(bean.getCardholder());
        bean.setCard_number(bean.getCard_number());
        bean.setBank_id(bean.getBank_id());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().addBankCard(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取银行列表
     * @param subscriber
     */
    public static void bank(DisposableObserver<ResponseBody> subscriber) {
        BankBean bean=new BankBean();
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().bank(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取快递列表
     * @param subscriber
     */
    public static void getKuaiDiList(DisposableObserver<ResponseBody> subscriber) {
        KuaiDiBean bean=new KuaiDiBean();
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().kuaidi(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的钱包充值
     * @param user_id
     * @param token
     * @param money
     * @param pay_code
     * @param subscriber
     */
    public static void recharge(String user_id,String token,String money,String pay_code,DisposableObserver<ResponseBody> subscriber) {
        RechargeBean bean=new RechargeBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setMoney(money);
        bean.setPay_code(pay_code);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().recharge(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的钱包提现
     * @param user_id
     * @param token
     * @param card_id
     * @param money
     * @param subscriber
     */
    public static void withdraw(String user_id,String token,String card_id,String money,DisposableObserver<ResponseBody> subscriber) {
        WithdrawBean bean=new WithdrawBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setCard_id(card_id);
        bean.setMoney(money);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().withdraw(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的钱包收益管理
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void inCome(String user_id,String token,DisposableObserver<ResponseBody> subscriber) {
        InComeBean bean=new InComeBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().inCome(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 收益管理店铺收益
     * @param user_id
     * @param token
     * @param begin_time
     * @param end_time
     * @param subscriber
     */
    public static void shopLog(String user_id,String token,String begin_time,String end_time,DisposableObserver<ResponseBody> subscriber) {
        ShopLogBean bean=new ShopLogBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setBegin_time(begin_time);
        bean.setEnd_time(end_time);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().shopLog(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 收益明细
     * @param user_id
     * @param token
     * @param begin_time
     * @param end_time
     * @param subscriber
     */
    public static void inComeLog(String user_id,String token,String begin_time,String end_time,DisposableObserver<ResponseBody> subscriber) {
        InComeLogBean bean=new InComeLogBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setBegin_time(begin_time);
        bean.setEnd_time(end_time);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().inComeLog(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 收益管理已提取金额
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void tiqu(String user_id,String token,DisposableObserver<ResponseBody> subscriber) {
        TiQuBean bean=new TiQuBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().tiQu(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 收益管理代言人奖励
     * @param user_id
     * @param token
     * @param type
     * @param begin_time
     * @param end_time
     * @param subscriber
     */
    public static void vipJL(String user_id,String token,String type,String begin_time,String end_time,DisposableObserver<ResponseBody> subscriber) {
        VipJLBean bean=new VipJLBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setType(type);
        bean.setBegin_time(begin_time);
        bean.setEnd_time(end_time);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().vipJL(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 提取收益
     * @param user_id
     * @param token
     * @param money
     * @param subscriber
     */
    public static void tqIncome(String user_id,String token,String money,DisposableObserver<ResponseBody> subscriber) {
        TqIncomeBean bean=new TqIncomeBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setMoney(money);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().tqIncome(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 用户钱包明细
     * @param user_id
     * @param token
     * @param status
     * @param begin_time
     * @param end_time
     * @param subscriber
     */
    public static void userMoney(String user_id,String token,String status,String begin_time,String end_time,DisposableObserver<ResponseBody> subscriber) {
        UserMoneyBean bean=new UserMoneyBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setStatus(status);
        bean.setBegin_time(begin_time);
        bean.setEnd_time(end_time);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().userMoney(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 立即入驻
     * @param bean
     * @param subscriber
     */
    public static void offlineStore(OfflineStoreBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setStore_name(bean.getStore_name());
        bean.setConsumption(bean.getConsumption());
        bean.setBusiness_hours(bean.getBusiness_hours());
        bean.setContact(bean.getContact());
        bean.setAddress(bean.getAddress());
        bean.setAddress2(bean.getAddress2());
        bean.setOffer(bean.getOffer());
        bean.setRelief(bean.getRelief());
        bean.setStore_img(bean.getStore_img());
        bean.setLat(bean.getLat());
        bean.setLng(bean.getLng());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().offlineStore(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 会员中心线下门店
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void myOfflineStore(String user_id,String token,DisposableObserver<ResponseBody> subscriber) {
        MyOfflineStoreBean bean=new MyOfflineStoreBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().myOfflineStore(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 会员中心线下门店设置
     * @param bean
     * @param subscriber
     */
    public static void editOfflineStore(EditOfflineStoreBean bean,DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setId(bean.getId());
        bean.setStore_name(bean.getStore_name());
        bean.setConsumption(bean.getConsumption());
        bean.setBusiness_hours(bean.getBusiness_hours());
        bean.setContact(bean.getContact());
        bean.setAddress(bean.getAddress());
        bean.setAddress2(bean.getAddress2());
        bean.setOffer(bean.getOffer());
        bean.setRelief(bean.getRelief());
        bean.setStore_img(bean.getStore_img());
        bean.setLat(bean.getLat());
        bean.setLng(bean.getLng());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().editOfflineStore(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 常见问题和热门问题
     * @param user_id
     * @param token
     * @param type
     * @param subscriber
     */
    public static void problem(String user_id,String token,String type,DisposableObserver<ResponseBody> subscriber) {
        ProblemBean bean=new ProblemBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().problem(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 问题详情页面
     * @param user_id
     * @param token
     * @param id
     * @param subscriber
     */
    public static void pdetail(String user_id,String token,String id,DisposableObserver<ResponseBody> subscriber) {
        PDetailBean bean=new PDetailBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().pdetail(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 意见反馈
     * @param bean
     * @param subscriber
     */
    public static void feedBack(FeedBackBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setDescription(bean.getDescription());
        bean.setTitle(bean.getTitle());
        bean.setImg(bean.getImg());
        bean.setRealname(bean.getRealname());
        bean.setMobile(bean.getMobile());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().feedBack(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 添加收藏
     * @param user_id
     * @param token
     * @param id
     * @param type
     * @param subscriber
     */
    public static void addCollection(String user_id,String token,String id,String type,DisposableObserver<ResponseBody> subscriber) {
        AddCollectionBean bean=new AddCollectionBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(id);
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().addCollection(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 收藏列表
     * @param user_id
     * @param token
     * @param type
     * @param subscriber
     */
    public static void collectionList(String user_id,String token,String type,DisposableObserver<ResponseBody> subscriber) {
        CollectionBean bean=new CollectionBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().collection(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 删除收藏
     * @param user_id
     * @param token
     * @param id
     * @param subscriber
     */
    public static void delCollection(String user_id,String token,String id,DisposableObserver<ResponseBody> subscriber) {
        DelCollectionBean bean=new DelCollectionBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().delCollection(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的供求供应列表
     * @param user_id
     * @param token
     * @param type
     * @param subscriber
     */
    public static void mySupplyList(String user_id,String token,String type,DisposableObserver<ResponseBody> subscriber) {
        MySupplyListBean bean=new MySupplyListBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyList(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的供应下架
     * @param user_id
     * @param token
     * @param id
     * @param subscriber
     */
    public static void mySupplyDown(String user_id,String token,String id,DisposableObserver<ResponseBody> subscriber) {
        SupplyDownBean bean=new SupplyDownBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyDown(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的供应上架
     * @param user_id
     * @param token
     * @param id
     * @param subscriber
     */
    public static void mySupplyUp(String user_id,String token,String id,DisposableObserver<ResponseBody> subscriber) {
        SupplyUpBean bean=new SupplyUpBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyUp(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的供应删除
     * @param user_id
     * @param token
     * @param id
     * @param subscriber
     */
    public static void mySupplyDel(String user_id,String token,String id,DisposableObserver<ResponseBody> subscriber) {
        SupplyDelBean bean=new SupplyDelBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyDel(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的求购列表
     * @param user_id
     * @param token
     * @param type
     * @param subscriber
     */
    public static void purchaseList(String user_id,String token,String type,DisposableObserver<ResponseBody> subscriber) {
        PurchaseListBean bean=new PurchaseListBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setType(type);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().purchaseList(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的求购列表删除
     * @param user_id
     * @param token
     * @param id
     * @param subscriber
     */
    public static void purchaseDel(String user_id,String token,String id,DisposableObserver<ResponseBody> subscriber) {
        PurchaseDelBean bean=new PurchaseDelBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().purchaseDel(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的求购下架
     * @param user_id
     * @param token
     * @param id
     * @param subscriber
     */
    public static void purchaseDown(String user_id,String token,String id,DisposableObserver<ResponseBody> subscriber) {
        PurchaseDownBean bean=new PurchaseDownBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().purchaseDown(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的求购上架
     * @param user_id
     * @param token
     * @param id
     * @param subscriber
     */
    public static void purchaseUp(String user_id,String token,String id,DisposableObserver<ResponseBody> subscriber) {
        PurchaseUpBean bean=new PurchaseUpBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().purchaseUp(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的求购编辑
     * @param user_id
     * @param token
     * @param id
     * @param subscriber
     */
    public static void purchaseEdit(String user_id,String token,String id,DisposableObserver<ResponseBody> subscriber) {
        PurchaseEditBean bean=new PurchaseEditBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().purchaseEdit(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的求购编辑提交
     * @param bean
     * @param subscriber
     */
    public static void purchaseEdit2(PurchaseEdit2Bean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().purchaseEdit2(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的供应编辑
     * @param user_id
     * @param token
     * @param id
     * @param subscriber
     */
    public static void supplyEdit(String user_id,String token,String id,DisposableObserver<ResponseBody> subscriber) {
        SupplyEditBean bean=new SupplyEditBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().supplyEdit(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的供应编辑提交
     * @param bean
     * @param subscriber
     */
    public static void supplyEdit2(SupplyEdit2Bean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().supplyEdit2(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }




    /**
     * 供应管理供应订单
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void supplyOrder(String user_id,String token,DisposableObserver<ResponseBody> subscriber) {
        SupplyOrderBean bean=new SupplyOrderBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().supplyOrder(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的报价列表
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void baojiaList(String user_id,String token,DisposableObserver<ResponseBody> subscriber) {
        BaojiaListBean bean=new BaojiaListBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().baojiaList(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 报价商家列表
     * @param user_id
     * @param token
     * @param id
     * @param subscriber
     */
    public static void baojiaUserList(String user_id,String token,String id,DisposableObserver<ResponseBody> subscriber) {
        BaojiaUserListBean bean=new BaojiaUserListBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().baojiaUserList(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 商家报价信息
     * @param user_id
     * @param token
     * @param id
     * @param subscriber
     */
    public static void baojiaUserDetail(String user_id,String token,String id,DisposableObserver<ResponseBody> subscriber) {
        BaojiaUserDetailBean bean=new BaojiaUserDetailBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setId(id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().baojiaUserDetail(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的供求我的订单
     * @param user_id
     * @param token
     * @param order_state
     * @param subscriber
     */
    public static void mySupplyOrder(String user_id,String token,String order_state,DisposableObserver<ResponseBody> subscriber) {
        MySupplyOrderBean bean=new MySupplyOrderBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_state(order_state);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyOrder(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供求订单发货
     * @param bean
     * @param subscriber
     */
    public static void mySupplyOrderFahuo(MySupplyOrderFahuoBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setOrder_id(bean.getOrder_id());
        bean.setExpress_id(bean.getExpress_id());
        bean.setExpress_number(bean.getExpress_number());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyOrderFahuo(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应订单申请退款
     * @param bean
     * @param subscriber
     */
    public static void mySupplyOrderRefund(MySupplyOrderRefundBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setOrder_id(bean.getOrder_id());
        bean.setRefund_state(bean.getRefund_state());
        bean.setRefund_reason(bean.getRefund_reason());
        bean.setRefund_price(bean.getRefund_price());
        bean.setRefund_desc(bean.getRefund_desc());
        bean.setRefund_img(bean.getRefund_img());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyOrderRefund(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应订单同意退款
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void mySupplyOrderRefund2(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        MySupplyOrderRefund2Bean bean=new MySupplyOrderRefund2Bean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyOrderRefund2(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应订单取消订单
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void mySupplyOrderCancel(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        MySupplyOrderCancelBean bean=new MySupplyOrderCancelBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyOrderCancel(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应订单删除
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void mySupplyOrderDel(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        MySupplyOrderDelBean bean=new MySupplyOrderDelBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyOrderDel(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应订单评价
     * @param bean
     * @param subscriber
     */
    public static void mySupplyOrderComment(MySupplyOrderCommentBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setOrder_id(bean.getOrder_id());
        bean.setContent(bean.getContent());
        bean.setImg(bean.getImg());
        bean.setGoods_star(bean.getGoods_star());
        bean.setLogistics_star(bean.getLogistics_star());
        bean.setService_star(bean.getService_star());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyOrderComment(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应订单退款原因
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void mySupplyOrderRefund3(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        MySupplyOrderRefund3Bean bean=new MySupplyOrderRefund3Bean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyOrderRefund3(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应订单我的订单确认收货
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void mySupplyGoodsConfirm(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        MySupplyGoodsConfirmBean bean=new MySupplyGoodsConfirmBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyGoodsConfirm(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应订单待付款去支付确认订单
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void mySupplyOrderConfirm(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        MySupplyOrderConfirmBean bean=new MySupplyOrderConfirmBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyOrderConfirm(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 供应订单待付款订单支付
     * @param bean
     * @param subscriber
     */
    public static void mySupplyOrderBuy(MySupplyOrderBuyBean bean, DisposableObserver<ResponseBody> subscriber) {
       bean.setUser_id(bean.getUser_id());
       bean.setToken(bean.getToken());
       bean.setOrder_id(bean.getOrder_id());
       bean.setPay_code(bean.getPay_code());
       bean.setAddress_id(bean.getAddress_id());
       Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyOrderBuy(bean);
       RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 加入购物车
     * @param bean
     * @param subscriber
     */
    public static void addCart(AddCartBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setGoods_id(bean.getGoods_id());
        bean.setGoods_num(bean.getGoods_num());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().addCart(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 购物车列表
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void cartList(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        CartListBean bean=new CartListBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().cartList(bean);//定义被观察者，获取RetrofitFactory的单例，在获取cartList对应的实体的链接，确定被观察者
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 购物车更新商品数量
     * @param bean
     * @param subscriber
     */
    public static void updateCart(UpdateCartBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setCart_id(bean.getCart_id());
        bean.setGoods_num(bean.getGoods_num());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().updateCart(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 购物车删除商品
     * @param user_id
     * @param token
     * @param cart_id
     * @param subscriber
     */
    public static void delCart(String user_id,String token,String cart_id, DisposableObserver<ResponseBody> subscriber) {
        DelCartBean bean=new DelCartBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setCart_id(cart_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().delCart(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 购物车确认订单
     * @param user_id
     * @param token
     * @param cart_id
     * @param subscriber
     */
    public static void cartOrder(String user_id,String token,String cart_id, DisposableObserver<ResponseBody> subscriber) {
        CartOrderBean bean=new CartOrderBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setCart_id(cart_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().cartOrder(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 购物车提交订单
     * @param bean
     * @param subscriber
     */
    public static void cartOrderOne(CartOrderOneBean bean, DisposableObserver<ResponseBody> subscriber) {
        bean.setUser_id(bean.getUser_id());
        bean.setToken(bean.getToken());
        bean.setCart_id(bean.getCart_id());
        bean.setAddress_id(bean.getAddress_id());
        bean.setPay_code(bean.getPay_code());
        bean.setType(bean.getType());
        bean.setItem_id(bean.getItem_id());
        bean.setRemark(bean.getRemark());
        bean.setZiti_id(bean.getZiti_id());
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().cartOrderOne(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 商品分类选择
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void goodsCate(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        GoodsCateBean bean=new GoodsCateBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().goodsCate(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 优惠券列表
     * @param user_id
     * @param token
     * @param status
     * @param subscriber
     */
    public static void myCoupon(String user_id,String token,String status, DisposableObserver<ResponseBody> subscriber) {
        MyCouponBean bean=new MyCouponBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setStatus(status);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().myCoupon(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     *我的业绩
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void myTeam(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        MyTeamBean bean=new MyTeamBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().myTeam(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 申请升级
     * @param user_id
     * @param token
     * @param type
     * @param app_level
     * @param subscriber
     */
    public static void userApp(String user_id,String token,long type,long app_level, DisposableObserver<ResponseBody> subscriber) {
        UserAppBean bean=new UserAppBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setType(type);
        bean.setApp_level(app_level);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().userApp(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 申请记录
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void userAppLog(String user_id,String token, DisposableObserver<ResponseBody> subscriber) {
        UserAppLogBean bean=new UserAppLogBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().userAppLog(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 普通商品确认订单
     * @param bean
     * @param subscriber
     */
    public static void goodsOrder(GoodsOrderBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().goodsOrder(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     *抢购商品确认订单
     * @param bean
     * @param subscriber
     */
    public static void seckillOrder(SeckillOrderBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().seckillOrder(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 抢购商品生成订单
     * @param bean
     * @param subscriber
     */
    public static void spikeOrderBuyOne(SpikeOrderBuyOneBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().spikeOrderBuyOne(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }



    /**
     * 购买商品提交订单
     * @param bean
     * @param subscriber
     */
    public static void goodsBuyOne(GoodsBuyOneBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().goodsBuyOne(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 举报
     * @param bean
     * @param subscriber
     */
    public static void report(ReportBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().report(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的供应订单详情
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void mySupplyOrderDetail(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        MySupplyOrderDetailBean bean=new MySupplyOrderDetailBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyOrderDetail(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的商品订单拒绝退款
     * @param bean
     * @param subscriber
     */
    public static void storeOrderRefuseRefund(StoreOrderRefuseRefundBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().storeOrderRefuseRefund(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的供应订单拒绝退款
     * @param bean
     * @param subscriber
     */
    public static void mySupplyOrderRefuseRefund(MySupplyOrderRefuseRefundBean bean, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().mySupplyOrderRefuseRefund(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 催单
     * @param user_id
     * @param token
     * @param order_id
     * @param subscriber
     */
    public static void reminders(String user_id,String token,String order_id, DisposableObserver<ResponseBody> subscriber) {
        RemindersBean bean=new RemindersBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setOrder_id(order_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().reminders(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 合伙人申请等级
     * @param user_id
     * @param token
     * @param type
     * @param app_level
     * @param subscriber
     */
    public static void userApply(String user_id,String token,long type,long app_level, DisposableObserver<ResponseBody> subscriber) {
        UserApplyBean bean=new UserApplyBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setType(type);
        bean.setApp_level(app_level);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().userApply(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 自提列表
     * @param user_id
     * @param token
     * @param lng
     * @param lat
     * @param subscriber
     */
    public static void selfLifting(String user_id,String token,String lng,String lat, DisposableObserver<ResponseBody> subscriber) {
        SelfLiftingBean bean=new SelfLiftingBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setLng(lng);
        bean.setLat(lat);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().selfLifting(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 直推代言人
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void userOne(String user_id,String token,DisposableObserver<ResponseBody> subscriber) {
        UserOneBean bean=new UserOneBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().userOne(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 间推代言人
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void userTwo(String user_id,String token,DisposableObserver<ResponseBody> subscriber) {
        UserTwoBean bean=new UserTwoBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().userTwo(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取省列表
     * @param subscriber
     */
    public static void getProvince(DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getProvince(new ProvinceBean());
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取市列表
     * @param pid
     * @param subscriber
     */
    public static void getCity(String pid,DisposableObserver<ResponseBody> subscriber) {
        CityBean bean=new CityBean();
        bean.setPid(pid);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getCity(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取区列表
     * @param pid
     * @param subscriber
     */
    public static void getDistrict(String pid,DisposableObserver<ResponseBody> subscriber) {
        DistrictBean bean=new DistrictBean();
        bean.setPid(pid);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getDistrict(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取乡镇列表
     * @param pid
     * @param subscriber
     */
    public static void getTown(String pid,DisposableObserver<ResponseBody> subscriber) {
        TownBean bean=new TownBean();
        bean.setPid(pid);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().getTown(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的订单中搜索订单
     * @param user_id
     * @param token
     * @param goods_name
     * @param subscriber
     */
    public static void searchOrder(String user_id,String token,String goods_name,DisposableObserver<ResponseBody> subscriber) {
        SearchOrderBean bean=new SearchOrderBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setGoods_name(goods_name);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().searchOrder(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 我的供应订单搜索
     * @param user_id
     * @param token
     * @param title
     * @param subscriber
     */
    public static void searchSupplyOrder(String user_id,String token,String title,DisposableObserver<ResponseBody> subscriber) {
        SearchSupplyOrderBean bean=new SearchSupplyOrderBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setTitle(title);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().searchSupplyOrder(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 取消关注
     * @param user_id
     * @param token
     * @param att_user_id
     * @param subscriber
     */
    public static void delAttention(String user_id,String token,String att_user_id,DisposableObserver<ResponseBody> subscriber) {
        DelAttentionBean bean=new DelAttentionBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setAtt_user_id(att_user_id);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().delAttention(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 追加评价
     * @param bean
     * @param subscriber
     */
    public static void addComment(AddCommentBean bean,DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().addComment(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 追加评论列表
     * @param bean
     * @param subscriber
     */
    public static void commonList(CommonListBean bean,DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().commonList(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 诚信认证退款记录
     * @param user_id
     * @param token
     * @param subscriber
     */
    public static void tuiList(String user_id,String token,DisposableObserver<ResponseBody> subscriber) {
        TuiListBean bean=new TuiListBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        Observable<ResponseBody> observable =  RetrofitFactory.getInstance().getHttpApi().tuiList(bean);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 线下门店收益
     * @param bean
     * @param subscriber
     */
    public static void offlineLog(OfflineLogBean bean,DisposableObserver<ResponseBody> subscriber){
        Observable<ResponseBody> observable=RetrofitFactory.getInstance().getHttpApi().offlineLog(bean);
        RetrofitFactory.getInstance().toSubscribe(observable,subscriber);
    }

    /**
     * 供应商品收益
     * @param bean
     * @param subscriber
     */
    public static void supplyLog(SupplyLogBean bean,DisposableObserver<ResponseBody> subscriber){
        Observable<ResponseBody> observable=RetrofitFactory.getInstance().getHttpApi().supplyLog(bean);
        RetrofitFactory.getInstance().toSubscribe(observable,subscriber);
    }

    /**
     * 供求置顶
     * @param bean
     * @param subscriber
     */
    public static void isTop(IsTopBean bean,DisposableObserver<ResponseBody> subscriber){
        Observable<ResponseBody> observable=RetrofitFactory.getInstance().getHttpApi().isTop(bean);
        RetrofitFactory.getInstance().toSubscribe(observable,subscriber);
    }
}
