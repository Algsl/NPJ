package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.AddAddressBean;
import com.zthx.npj.net.been.AddGoodsBean;
import com.zthx.npj.net.been.AddressInfoBean;
import com.zthx.npj.net.been.AddressListBean;
import com.zthx.npj.net.been.DefaultAddressBean;
import com.zthx.npj.net.been.DelAddressBean;
import com.zthx.npj.net.been.DelGoodsBean;
import com.zthx.npj.net.been.EditAddressBean;
import com.zthx.npj.net.been.EditGoodsBean;
import com.zthx.npj.net.been.EditHeadimgBean;
import com.zthx.npj.net.been.EditNicknameBean;
import com.zthx.npj.net.been.GoodsInfoBean;
import com.zthx.npj.net.been.MyGoodsBean;
import com.zthx.npj.net.been.MyStoreBean;
import com.zthx.npj.net.been.OutGoodsBean;
import com.zthx.npj.net.been.UpLoadFileBean;
import com.zthx.npj.net.been.UploadPicsBean;
import com.zthx.npj.net.been.UserBean;
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
    public static void upLoadFile(File file, DisposableObserver<ResponseBody> subscriber) {
        UpLoadFileBean bean = new UpLoadFileBean();
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
     * @param user_id
     * @param token
     * @param consignee
     * @param mobile
     * @param address
     * @param house_number
     * @param is_default
     * @param subscriber
     */
    public static void addAddress(String user_id,String token,String consignee,String mobile,String address,String house_number,String is_default, DisposableObserver<ResponseBody> subscriber) {
        AddAddressBean bean=new AddAddressBean();
        bean.setUser_id(user_id);
        bean.setToken(token);
        bean.setConsignee(consignee);
        bean.setMobile(mobile);
        bean.setAddress(address);
        bean.setHouse_number(house_number);
        bean.setIs_default(is_default);
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
}
