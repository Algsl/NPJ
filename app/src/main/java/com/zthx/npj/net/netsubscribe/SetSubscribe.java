package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.AddAddressBean;
import com.zthx.npj.net.been.AddressListBean;
import com.zthx.npj.net.been.EditHeadimgBean;
import com.zthx.npj.net.been.EditNicknameBean;
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

}
