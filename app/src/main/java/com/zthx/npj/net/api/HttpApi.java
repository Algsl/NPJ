package com.zthx.npj.net.api;

import com.zthx.npj.net.been.AkVideoBean;
import com.zthx.npj.net.been.BannerBean;
import com.zthx.npj.net.been.BuyVideoBean;
import com.zthx.npj.net.been.CommentBean;
import com.zthx.npj.net.been.ConfirmPreSellBean;
import com.zthx.npj.net.been.ConfirmSupplyBean;
import com.zthx.npj.net.been.GiftDetailBean;
import com.zthx.npj.net.been.GiftListBean;
import com.zthx.npj.net.been.GoodsDetailBean;
import com.zthx.npj.net.been.InvitationBean;
import com.zthx.npj.net.been.LocalSpokesmanBeen;
import com.zthx.npj.net.been.LocalSpokesmanResponseBean;
import com.zthx.npj.net.been.LocalStoreBean;
import com.zthx.npj.net.been.MsgCodeBeen;
import com.zthx.npj.net.been.NullBean;
import com.zthx.npj.net.been.PhoneLoginBean;
import com.zthx.npj.net.been.PreSellBean;
import com.zthx.npj.net.been.RecommendBean;
import com.zthx.npj.net.been.SearchBean;
import com.zthx.npj.net.been.StoreDetailBean;
import com.zthx.npj.net.been.SupplyListBean;
import com.zthx.npj.net.been.UpLoadFileBean;
import com.zthx.npj.net.been.UpLoadMyCertBean;
import com.zthx.npj.net.been.UploadCaigouBean;
import com.zthx.npj.net.been.UploadChengXinCertBean;
import com.zthx.npj.net.been.UploadCommentBean;
import com.zthx.npj.net.been.UploadCompanyBean;
import com.zthx.npj.net.been.UploadPicsBean;
import com.zthx.npj.net.been.UploadPurchaseBean;
import com.zthx.npj.net.been.UploadSupplyBean;
import com.zthx.npj.net.been.VideoInfoBean;

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

    @POST("index.php/api/index/spike.html")
    Observable<ResponseBody> getSecKillTodayForBody(@Body NullBean bean);

    @POST("index.php/api/index/spikeend.html")
    Observable<ResponseBody> getSecKillOverForBody(@Body NullBean bean);

    @POST("index.php/api/index/spikebegin.html")
    Observable<ResponseBody> getSecKillStartForBody(@Body NullBean bean);

    @POST("index.php/api/index/spikedetails.html")
    Observable<ResponseBody> getSecKillGoodsDetailForBody(@Body GoodsDetailBean bean);

    @POST("index.php/api/user/giftlist.htm")
    Observable<ResponseBody> getGiftListForBody(@Body GiftListBean bean);

    @POST("index.php/api/user/useradvantage.htm")
    Observable<ResponseBody> getSpokesmanQuanForBody(@Body GiftListBean bean);

    @POST("index.php/api/user/giftdetail.htm")
    Observable<ResponseBody> getGiftDetailForBody(@Body GiftDetailBean bean);

    @POST("index.php/api/user/giftorder.htm")
    Observable<ResponseBody> getGiftConfirmForBody(@Body GiftDetailBean bean);

    @POST("index.php/api/usercert/cert.html")
    Observable<ResponseBody> getMyCertForBody(@Body GiftListBean bean);

    @POST("index.php/api/set/uploadimg.html")
    Observable<ResponseBody> upLoadFileForBody(@Body UpLoadFileBean bean);

    @POST("index.php/api/usercert/realname2.html")
    Observable<ResponseBody> upLoadMyCertForBody(@Body UpLoadMyCertBean bean);

    @POST("index.php/api/usercert/company2.html")
    Observable<ResponseBody> isPersonCertDoneForBody(@Body GiftListBean bean);

    @POST("index.php/api/usercert/company3.html")
    Observable<ResponseBody> uploadCompanyForBody(@Body UploadCompanyBean bean);

    @POST("index.php/api/usercert/stock2.html")
    Observable<ResponseBody> uploadCaigouForBody(@Body UploadCaigouBean bean);

    @POST("index.php/api/usercert/integrity.html")
    Observable<ResponseBody> isChengxinCerAlreadyForBody(@Body GiftListBean bean);

    @POST("index.php/api/usercert/integrity2.html")
    Observable<ResponseBody> isChengxinCerAlready2ForBody(@Body GiftListBean bean);

    @POST("index.php/api/usercert/integrity3.html")
    Observable<ResponseBody> uploadChengxinCertForBody(@Body UploadChengXinCertBean bean);

    @POST("index.php/api/index/solution.html")
    Observable<ResponseBody> getSolutionListForBody(@Body NullBean bean);

    @POST("index.php/api/index/videolist.html")
    Observable<ResponseBody> getSolutionVideoListForBody(@Body GoodsDetailBean bean);

    @POST("index.php/api/index/knowledgelist.html")
    Observable<ResponseBody> getKnowledgeListForBody(@Body GoodsDetailBean bean);

    @POST("index.php/api/index/knowledgevideo.html")
    Observable<ResponseBody> getKnowledgeVideoListForBody(@Body AkVideoBean bean);

    @POST("index.php/api/index/video.html")
    Observable<ResponseBody> getVideoInfoForBody(@Body VideoInfoBean bean);

    @POST("index.php/api/user/coursepj.html")
    Observable<ResponseBody> uploadVideoCommentForBody(@Body UploadCommentBean bean);

    @POST("index.php/api/user/buyvideo.html")
    Observable<ResponseBody> buyVideoForBody(@Body BuyVideoBean bean);

    @POST("index.php/api/user/addsupply.html")
    Observable<ResponseBody> uploadSupplyForBody(@Body UploadSupplyBean bean);

    @POST("index.php/api/set/uploadimagegroup.html")
    Observable<ResponseBody> uploadPicsForBody(@Body UploadPicsBean bean);

    @POST("index.php/api/user/addpurchase.html")
    Observable<ResponseBody> uploadPurchaseForBody(@Body UploadPurchaseBean bean);

    @POST("index.php/api/index/supplylist.html")
    Observable<ResponseBody> supplyListForBody(@Body SupplyListBean bean);

    @POST("index.php/api/index/qglist.html")
    Observable<ResponseBody> needListForBody(@Body SupplyListBean bean);

    @POST("index.php/api/index/supplydetail.html")
    Observable<ResponseBody> supplyDetalForBody(@Body GoodsDetailBean bean);

    @POST("index.php/api/index/qgdetail.html")
    Observable<ResponseBody> needDetalForBody(@Body GoodsDetailBean bean);

    @POST("index.php/api/user/supplybuy.html")
    Observable<ResponseBody> confirmSupplyForBody(@Body ConfirmSupplyBean bean);
}

