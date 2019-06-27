package com.zthx.npj.net.netsubscribe;

import com.zthx.npj.net.been.GiftListBean;
import com.zthx.npj.net.been.UpLoadFileBean;
import com.zthx.npj.net.been.UploadPicsBean;
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
}
