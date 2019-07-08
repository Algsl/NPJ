package com.zthx.npj.net.api;

import com.zthx.npj.net.been.AddAddressBean;
import com.zthx.npj.net.been.AddGoodsBean;
import com.zthx.npj.net.been.AddressInfoBean;
import com.zthx.npj.net.been.AddressListBean;
import com.zthx.npj.net.been.AkVideoBean;
import com.zthx.npj.net.been.ApplyRefundBean;
import com.zthx.npj.net.been.BannerBean;
import com.zthx.npj.net.been.BuyBean;
import com.zthx.npj.net.been.BuyVideoBean;
import com.zthx.npj.net.been.CancelOrderBean;
import com.zthx.npj.net.been.CommentBean;
import com.zthx.npj.net.been.ConfirmOrderBean;
import com.zthx.npj.net.been.ConfirmPreSellBean;
import com.zthx.npj.net.been.ConfirmSupplyBean;
import com.zthx.npj.net.been.DefaultAddressBean;
import com.zthx.npj.net.been.DelAddressBean;
import com.zthx.npj.net.been.DelGoodsBean;
import com.zthx.npj.net.been.DelOrderBean;
import com.zthx.npj.net.been.EditAddressBean;
import com.zthx.npj.net.been.EditGoodsBean;
import com.zthx.npj.net.been.EditHeadimgBean;
import com.zthx.npj.net.been.EditNicknameBean;
import com.zthx.npj.net.been.GiftDetailBean;
import com.zthx.npj.net.been.GiftListBean;
import com.zthx.npj.net.been.GoodsDetailBean;
import com.zthx.npj.net.been.GoodsInfoBean;
import com.zthx.npj.net.been.InvitationBean;
import com.zthx.npj.net.been.LocalSpokesmanBeen;
import com.zthx.npj.net.been.LocalStoreBean;
import com.zthx.npj.net.been.MsgCodeBeen;
import com.zthx.npj.net.been.MyGoodsBean;
import com.zthx.npj.net.been.MyOrderDetailBean;
import com.zthx.npj.net.been.MyOrderListBean;
import com.zthx.npj.net.been.MyStoreBean;
import com.zthx.npj.net.been.NullBean;
import com.zthx.npj.net.been.OrderBean;
import com.zthx.npj.net.been.OrderCommentBean;
import com.zthx.npj.net.been.OutGoodsBean;
import com.zthx.npj.net.been.PhoneLoginBean;
import com.zthx.npj.net.been.PreSellBean;
import com.zthx.npj.net.been.RecommendBean;
import com.zthx.npj.net.been.RefundBean;
import com.zthx.npj.net.been.SearchBean;
import com.zthx.npj.net.been.SetStoreBean;
import com.zthx.npj.net.been.ShipBean;
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
import com.zthx.npj.net.been.UserBean;
import com.zthx.npj.net.been.VideoInfoBean;
import com.zthx.npj.net.been.YsBuyOneBean;

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

    @POST("index.php/api/user/ysbuyone.html")
    Observable<ResponseBody> ysBuyOne(@Body YsBuyOneBean bean);


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

    @POST("index.php/api/user/userinfo.html")
    Observable<ResponseBody> getUserInfo(@Body UserBean bean);

    @POST("index.php/api/user/editheadimg.html")
    Observable<ResponseBody> editHeadImg(@Body EditHeadimgBean bean);

    @POST("index.php/api/user/editnickname.html")
    Observable<ResponseBody> editNickname(@Body EditNicknameBean bean);

    @POST("index.php/api/user/addaddress.html")
    Observable<ResponseBody> addAddress(@Body AddAddressBean bean);

    @POST("index.php/api/user/addresslist.html")
    Observable<ResponseBody> getAddressList(@Body AddressListBean bean);

    @POST("index.php/api/user/addressinfo.html")
    Observable<ResponseBody> getAddressInfo(@Body AddressInfoBean bean);

    @POST("index.php/api/user/editaddress.html")
    Observable<ResponseBody> editAddressInfo(@Body EditAddressBean bean);

    @POST("index.php/api/user/deladdress.html")
    Observable<ResponseBody> delAddress(@Body DelAddressBean bean);

    @POST("index.php/api/user/defaultaddress.html")
    Observable<ResponseBody> defaultAddress(@Body DefaultAddressBean bean);

    @POST("index.php/api/user/mystore.html")
    Observable<ResponseBody> myStore(@Body MyStoreBean bean);

    @POST("index.php/api/user/addgoods.html")
    Observable<ResponseBody> addGoods(@Body AddGoodsBean bean);

    @POST("index.php/api/user/mygoods.html")
    Observable<ResponseBody> myGoods(@Body MyGoodsBean bean);

    @POST("index.php/api/user/goodsinfo.html")
    Observable<ResponseBody> goodsInfo(@Body GoodsInfoBean bean);

    @POST("index.php/api/user/editgoods.html")
    Observable<ResponseBody> editGoods(@Body EditGoodsBean bean);

    @POST("index.php/api/user/delgoods.html")
    Observable<ResponseBody> delGoods(@Body DelGoodsBean bean);

    @POST("index.php/api/user/outgoods.html")
    Observable<ResponseBody> outGoods(@Body OutGoodsBean bean);

    @POST("index.php/api/user/myorderlist.html")
    Observable<ResponseBody> myOrderList(@Body MyOrderListBean bean);

    @POST("index.php/api/user/myorderdetail.html")
    Observable<ResponseBody> myOrderDetail(@Body MyOrderDetailBean bean);

    @POST("index.php/api/user/ship.html")
    Observable<ResponseBody> ship(@Body ShipBean bean);

    @POST("index.php/api/user/refund.html")
    Observable<ResponseBody> refund(@Body RefundBean bean);

    @POST("index.php/api/user/setstore.html")
    Observable<ResponseBody> setStore(@Body SetStoreBean bean);

    @POST("index.php/api/user/order.html")
    Observable<ResponseBody> order(@Body OrderBean bean);

    @POST("index.php/api/user/cancelorder.html")
    Observable<ResponseBody> cancelOrder(@Body CancelOrderBean bean);

    @POST("index.php/api/user/delorder.html")
    Observable<ResponseBody> delOrder(@Body DelOrderBean bean);

    @POST("index.php/api/user/confirmorder.html")
    Observable<ResponseBody> confirmOrder(@Body ConfirmOrderBean bean);

    @POST("index.php/api/user/buy.html")
    Observable<ResponseBody> buy(@Body BuyBean bean);

    @POST("index.php/api/user/comment.html")
    Observable<ResponseBody> orderComment(@Body OrderCommentBean bean);

    @POST("index.php/api/user/applyrefund.html")
    Observable<ResponseBody> applyRefund(@Body ApplyRefundBean bean);
}

