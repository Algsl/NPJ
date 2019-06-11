package com.zthx.npj.net.api;

import com.zthx.npj.net.been.BannerBean;
import com.zthx.npj.net.been.CommentBean;
import com.zthx.npj.net.been.ConfirmPreSellBean;
import com.zthx.npj.net.been.GoodsDetailBean;
import com.zthx.npj.net.been.InvitationBean;
import com.zthx.npj.net.been.LocalSpokesmanBeen;
import com.zthx.npj.net.been.LocalSpokesmanResponseBean;
import com.zthx.npj.net.been.LocalStoreBean;
import com.zthx.npj.net.been.MsgCodeBeen;
import com.zthx.npj.net.been.PhoneLoginBean;
import com.zthx.npj.net.been.PreSellBean;
import com.zthx.npj.net.been.RecommendBean;
import com.zthx.npj.net.been.SearchBean;
import com.zthx.npj.net.been.StoreDetailBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by huangxin on 2019/5/27.
 */

public interface HttpApi  {

    @POST("index.php/api/login/spokesperson.html")
    Observable<ResponseBody> getLocalSpokesmanForBody(@Body LocalSpokesmanBeen requestBean);

    @POST("index.php/api/login/sendcode.html")
    Observable<ResponseBody> getMobileCodeForBody(@Body MsgCodeBeen been);

    @POST("index.php/api/login/login.html")
    Observable<ResponseBody> mobileLoginForBody(@Body PhoneLoginBean been);

    @POST("index.php/api/login/invitation.html")
    Observable<ResponseBody> invitationForBody(@Body InvitationBean been);

    @POST("index.php/api/index/banner.html")
    Observable<ResponseBody> getBannerForBody(@Body BannerBean bean);

    @POST("index.php/api/index/recommend.html")
    Observable<ResponseBody> getRecommendForBody(@Body RecommendBean bean);

    @POST("index.php/api/index/search.html")
    Observable<ResponseBody> getSearchResultForBody(@Body SearchBean bean);

    @POST("index.php/api/index/goods.html")
    Observable<ResponseBody> getGoodsDetailForBody(@Body GoodsDetailBean bean);

    @POST("index.php/api/index/nearbystore.html")
    Observable<ResponseBody> getLocalStoreForBody(@Body LocalStoreBean bean);

    @POST("index.php/api/index/storedetails.html")
    Observable<ResponseBody> getStoreDetailForBody(@Body StoreDetailBean bean);

    @POST("index.php/api/index/commentlist.html")
    Observable<ResponseBody> getCommentForBody(@Body CommentBean bean);

    @POST("index.php/api/index/newbooking.html")
    Observable<ResponseBody> getPreSellListForBody(@Body PreSellBean bean);

    @POST("index.php/api/index/ysdetails.html")
    Observable<ResponseBody> getPreSellDetailForBody(@Body GoodsDetailBean bean);

    @POST("index.php/api/user/ysorder.html")
    Observable<ResponseBody> confirmPreSellForBody(@Body ConfirmPreSellBean bean);
}
