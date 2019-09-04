package com.zthx.npj.net.api;

import com.zthx.npj.net.been.*;

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

    @POST("index.php/api/user/pdetail.html")
    Observable<ResponseBody> pdetail(@Body PDetailBean bean);

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

    @POST("index.php/api/user/giftlist.html")
    Observable<ResponseBody> getGiftListForBody(@Body GiftListBean bean);

    @POST("index.php/api/user/useradvantage.html")
    Observable<ResponseBody> getSpokesmanQuanForBody(@Body GiftListBean bean);

    @POST("index.php/api/user/giftdetail.html")
    Observable<ResponseBody> getGiftDetailForBody(@Body GiftDetailBean bean);

    @POST("index.php/api/user/giftorder.html")
    Observable<ResponseBody> getGiftConfirmForBody(@Body GiftDetailBean bean);

    @POST("index.php/api/usercert/cert.html")
    Observable<ResponseBody> getMyCertForBody(@Body CertBean bean);

    @POST("index.php/api/set/uploadimg.html")
    Observable<ResponseBody> upLoadFileForBody(@Body UpLoadFileBean bean);

    @POST("index.php/api/usercert/realname2.html")
    Observable<ResponseBody> upLoadMyCertForBody(@Body UpLoadMyCertBean bean);

    @POST("index.php/api/usercert/realname3.html")
    Observable<ResponseBody> upLoadMyCertForBody3(@Body UpLoadMyCertBean bean);

    @POST("index.php/api/usercert/company2.html")
    Observable<ResponseBody> isPersonCertDoneForBody(@Body CertBean bean);

    @POST("index.php/api/usercert/company3.html")
    Observable<ResponseBody> uploadCompanyForBody(@Body UploadCompanyBean bean);

    @POST("index.php/api/usercert/company4.html")
    Observable<ResponseBody> uploadCompanyForBody4(@Body UploadCompanyBean bean);

    @POST("index.php/api/usercert/stock2.html")
    Observable<ResponseBody> uploadCaigouForBody(@Body UploadCaigouBean bean);

    @POST("index.php/api/usercert/stock3.html")
    Observable<ResponseBody> uploadCaigouForBody3(@Body UploadCaigouBean bean);

    @POST("index.php/api/usercert/integrity.html")
    Observable<ResponseBody> isChengxinCerAlreadyForBody(@Body CertBean bean);

    @POST("index.php/api/usercert/integrity2.html")
    Observable<ResponseBody> isChengxinCerAlready2ForBody(@Body CertBean bean);

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


    @POST("index.php/api/set/uploadimagegroup.html")
    Observable<ResponseBody> uploadPicsForBody(@Body UploadPicsBean bean);


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

    @POST("index.php/api/user/refund2.html")
    Observable<ResponseBody> refund2(@Body RefundBean bean);

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

    @POST("index.php/api/set/lookkd.html")
    Observable<ResponseBody> lookKD(@Body LookKDBean bean);

    @POST("index.php/api/user/confirm.html")
    Observable<ResponseBody> receiveConfirm(@Body ReceiveConfirmBean bean);

    @POST("index.php/api/payment/payvideo.html")
    Observable<ResponseBody> payVideo(@Body PayVideoBean bean);

    @POST("index.php/api/user/bankcard.html")
    Observable<ResponseBody> bankCard(@Body BankCardBean bean);

    @POST("index.php/api/user/addbankcard.html")
    Observable<ResponseBody> addBankCard(@Body AddBankCardBean bean);

    @POST("index.php/api/set/bank.html")
    Observable<ResponseBody> bank(@Body BankBean bean);

    @POST("index.php/api/set/kuaidi.html")
    Observable<ResponseBody> kuaidi(@Body KuaiDiBean bean);

    @POST("index.php/api/user/recharge.html")
    Observable<ResponseBody> recharge(@Body RechargeBean bean);

    @POST("index.php/api/user/withdraw.html")
    Observable<ResponseBody> withdraw(@Body WithdrawBean bean);

    @POST("index.php/api/login/authlogin.html")
    Observable<ResponseBody> authLogin(@Body AuthLoginBean bean);

    @POST("index.php/api/login/authloginbymobile.html")
    Observable<ResponseBody> authLoginbymobile(@Body AuthLoginByMoBileBean bean);

    @POST("index.php/api/user/income.html")
    Observable<ResponseBody> inCome(@Body InComeBean bean);

    @POST("index.php/api/user/shoplog.html")
    Observable<ResponseBody> shopLog(@Body ShopLogBean bean);

    @POST("index.php/api/user/incomelog.html")
    Observable<ResponseBody> inComeLog(@Body InComeLogBean bean);

    @POST("index.php/api/user/tiqu.html")
    Observable<ResponseBody> tiQu(@Body TiQuBean bean);

    @POST("index.php/api/user/vipjl.html")
    Observable<ResponseBody> vipJL(@Body VipJLBean bean);

    @POST("index.php/api/user/tqincome.html")
    Observable<ResponseBody> tqIncome(@Body TqIncomeBean bean);

    @POST("index.php/api/user/usermoney.html")
    Observable<ResponseBody> userMoney(@Body UserMoneyBean bean);

    @POST("index.php/api/user/offlinestore.html")
    Observable<ResponseBody> offlineStore(@Body OfflineStoreBean bean);

    @POST("index.php/api/user/myofflinestore.html")
    Observable<ResponseBody> myOfflineStore(@Body MyOfflineStoreBean bean);

    @POST("index.php/api/user/editofflinestore.html")
    Observable<ResponseBody> editOfflineStore(@Body EditOfflineStoreBean bean);

    @POST("index.php/api/user/problem.html")
    Observable<ResponseBody> problem(@Body ProblemBean bean);

    @POST("index.php/api/user/feedback.html")
    Observable<ResponseBody> feedBack(@Body FeedBackBean bean);

    @POST("index.php/api/user/addcollection.html")
    Observable<ResponseBody> addCollection(@Body AddCollectionBean bean);

    @POST("index.php/api/user/collection.html")
    Observable<ResponseBody> collection(@Body CollectionBean bean);

    @POST("index.php/api/user/delcollection.html")
    Observable<ResponseBody> delCollection(@Body DelCollectionBean bean);

    @POST("index.php/api/user/supplylist.html")
    Observable<ResponseBody> mySupplyList(@Body MySupplyListBean bean);

    @POST("index.php/api/user/supplydown.html")
    Observable<ResponseBody> mySupplyDown(@Body SupplyDownBean bean);

    @POST("index.php/api/user/supplyup.html")
    Observable<ResponseBody> mySupplyUp(@Body SupplyUpBean bean);

    @POST("index.php/api/user/supplydel.html")
    Observable<ResponseBody> mySupplyDel(@Body SupplyDelBean bean);

    @POST("index.php/api/user/purchaselist.html")
    Observable<ResponseBody> purchaseList(@Body PurchaseListBean bean);

    @POST("index.php/api/user/purchasedel.html")
    Observable<ResponseBody> purchaseDel(@Body PurchaseDelBean bean);

    @POST("index.php/api/user/purchasedown.html")
    Observable<ResponseBody> purchaseDown(@Body PurchaseDownBean bean);

    @POST("index.php/api/user/purchaseup.html")
    Observable<ResponseBody> purchaseUp(@Body PurchaseUpBean bean);

    @POST("index.php/api/user/purchaseedit.html")
    Observable<ResponseBody> purchaseEdit(@Body PurchaseEditBean bean);

    @POST("index.php/api/user/supplyorder.html")
    Observable<ResponseBody> supplyOrder(@Body SupplyOrderBean bean);

    @POST("index.php/api/user/baojialist.html")
    Observable<ResponseBody> baojiaList(@Body BaojiaListBean bean);

    @POST("index.php/api/user/baojiauserlist.html")
    Observable<ResponseBody> baojiaUserList(@Body BaojiaUserListBean bean);

    @POST("index.php/api/user/baojiauser.html")
    Observable<ResponseBody> baojiaUserDetail(@Body BaojiaUserDetailBean bean);

    @POST("index.php/api/user/mysupplyorder.html")
    Observable<ResponseBody> mySupplyOrder(@Body MySupplyOrderBean bean);


    @POST("index.php/api/user/supplybuy2.html")
    Observable<ResponseBody> supplyBuy2(@Body SupplyBuy2Bean bean);

    @POST("index.php/api/user/supplyfahuo.html")
    Observable<ResponseBody> mySupplyOrderFahuo(@Body MySupplyOrderFahuoBean bean);

    @POST("index.php/api/user/supplyrefund.html")
    Observable<ResponseBody> mySupplyOrderRefund(@Body MySupplyOrderRefundBean bean);



    @POST("index.php/api/user/supplyrefund2.html")
    Observable<ResponseBody> mySupplyOrderRefund2(@Body MySupplyOrderRefund2Bean bean);

    @POST("index.php/api/user/supplycancelorder.html")
    Observable<ResponseBody> mySupplyOrderCancel(@Body MySupplyOrderCancelBean bean);

    @POST("index.php/api/user/supplydelorder.html")
    Observable<ResponseBody> mySupplyOrderDel(@Body MySupplyOrderDelBean bean);

    @POST("index.php/api/user/supplycomment.html")
    Observable<ResponseBody> mySupplyOrderComment(@Body MySupplyOrderCommentBean bean);

    @POST("index.php/api/user/supplyrefund3.html")
    Observable<ResponseBody> mySupplyOrderRefund3(@Body MySupplyOrderRefund3Bean bean);

    @POST("index.php/api/user/supplyconfirm.html")
    Observable<ResponseBody> mySupplyGoodsConfirm(@Body MySupplyGoodsConfirmBean bean);

    @POST("index.php/api/user/supplyconfirmorder.html")
    Observable<ResponseBody> mySupplyOrderConfirm(@Body MySupplyOrderConfirmBean bean);

    @POST("index.php/api/user/supplyorderbuy.html")
    Observable<ResponseBody> mySupplyOrderBuy(@Body MySupplyOrderBuyBean bean);

    @POST("index.php/api/index/category.html")
    Observable<ResponseBody> category(@Body CategoryBean bean);

    @POST("index.php/api/user/addcart.html")
    Observable<ResponseBody> addCart(@Body AddCartBean bean);

    @POST("index.php/api/user/cartlist.html")
    Observable<ResponseBody> cartList(@Body CartListBean bean);

    @POST("index.php/api/user/updatecart.html")
    Observable<ResponseBody> updateCart(@Body UpdateCartBean bean);

    @POST("index.php/api/user/delcart.html")
    Observable<ResponseBody> delCart(@Body DelCartBean bean);

    @POST("index.php/api/user/cartorder.html")
    Observable<ResponseBody> cartOrder(@Body CartOrderBean bean);

    @POST("index.php/api/user/cartorderone.html")
    Observable<ResponseBody> cartOrderOne(@Body CartOrderOneBean bean);

    @POST("index.php/api/index/goodslist.html")
    Observable<ResponseBody> goodsList(@Body GoodsListBean bean);

    @POST("index.php/api/user/goodscate.html")
    Observable<ResponseBody> goodsCate(@Body GoodsCateBean bean);

    @POST("index.php/api/user/mycoupon.html")
    Observable<ResponseBody> myCoupon(@Body MyCouponBean bean);

    @POST("index.php/api/set/orderpush.html")
    Observable<ResponseBody> orderPush(@Body OrderPushBean bean);

    @POST("index.php/api/index/history.html")
    Observable<ResponseBody> history(@Body HistoryBean bean);

    @POST("index.php/api/index/delhistory.html")
    Observable<ResponseBody> delHistory(@Body DelHistoryBean bean);

    @POST("index.php/api/index/hotsearch.html")
    Observable<ResponseBody> hotSearch(@Body HotSearchBean bean);

    @POST("index.php/api/index/searchstore.html")
    Observable<ResponseBody> searchStore(@Body SearchStoreBean bean);

    @POST("index.php/api/user/referrer.html")
    Observable<ResponseBody> referrer(@Body ReferrerBean bean);

    @POST("index.php/api/index/solutionsearch.html")
    Observable<ResponseBody> solutionSearch(@Body SolutionSearchBean bean);

    @POST("index.php/api/index/newslist.html")
    Observable<ResponseBody> newsList(@Body NewsListBean bean);

    @POST("index.php/api/index/news.html")
    Observable<ResponseBody> newsDetail(@Body NewsBean bean);

    @POST("index.php/api/user/videoorder.html")
    Observable<ResponseBody> videoOrder(@Body VideoOrderBean bean);

    @POST("index.php/api/user/myteam.html")
    Observable<ResponseBody> myTeam(@Body MyTeamBean bean);

    @POST("index.php/api/user/userapp.html")
    Observable<ResponseBody> userApp(@Body UserAppBean bean);

    @POST("index.php/api/user/userapplog.html")
    Observable<ResponseBody> userAppLog(@Body UserAppLogBean bean);

    @POST("index.php/api/payment/pay.html")
    Observable<ResponseBody> pay(@Body PayBean bean);

    @POST("index.php/api/user/addsupply.html")
    Observable<ResponseBody> addSupply(@Body AddSupplyBean bean);

    @POST("index.php/api/user/addpurchase.html")
    Observable<ResponseBody> addPurchase(@Body AddPurchaseBean bean);

    @POST("index.php/api/user/baojia.html")
    Observable<ResponseBody> baoJia(@Body BaoJiaBean bean);

    @POST("index.php/api/index/twjclist.html")
    Observable<ResponseBody> twjcList(@Body TwjcListBean bean);

    @POST("index.php/api/usercert/zizhi.html")
    Observable<ResponseBody> zizhi(@Body ZiZhiBean bean);

    @POST("index.php/api/usercert/zizhi2.html")
    Observable<ResponseBody> zizhi2(@Body ZiZhi2Bean bean);

    @POST("index.php/api/usercert/zizhi3.html")
    Observable<ResponseBody> zizhi3(@Body ZiZhi2Bean bean);

    @POST("index.php/api/usercert/zizhi4.html")
    Observable<ResponseBody> zizhi4(@Body ZiZhiBean bean);

    @POST("index.php/api/usercert/realname.html")
    Observable<ResponseBody> realName(@Body RealNameBean bean);

    @POST("index.php/api/usercert/company.html")
    Observable<ResponseBody> company(@Body CompanyBean bean);

    @POST("index.php/api/usercert/stock.html")
    Observable<ResponseBody> stock(@Body StockBean bean);

    @POST("index.php/api/usercert/integrity.html")
    Observable<ResponseBody> integrity(@Body IntegrityBean bean);

    @POST("index.php/api/index/lookuser.html")
    Observable<ResponseBody> lookUser(@Body LookUserBean bean);

    @POST("index.php/api/user/attention.html")
    Observable<ResponseBody> attention(@Body AttentionBean bean);

    @POST("index.php/api/index/storeinfo.html")
    Observable<ResponseBody> storeInfo(@Body StoreInfoBean bean);

    @POST("index.php/api/index/storegoodslist.html")
    Observable<ResponseBody> storeGoodsList(@Body StoreGoodsListBean bean);

    @POST("index.php/api/index/searchstoregoods.html")
    Observable<ResponseBody> searchStoreGoods(@Body SearchStoreGoodsBean bean);

    @POST("index.php/api/index/goodsbycate.html")
    Observable<ResponseBody> goodsByCate(@Body GoodsByCateBean bean);

    @POST("index.php/api/user/goodsorder.html")
    Observable<ResponseBody> goodsOrder(@Body GoodsOrderBean bean);

    @POST("index.php/api/index/childhome.html")
    Observable<ResponseBody> childHome(@Body ChildHomeBean bean);

    @POST("index.php/api/user/goodsbuyone.html")
    Observable<ResponseBody> goodsBuyOne(@Body GoodsBuyOneBean bean);

    @POST("index.php/api/user/giftbuyone.html")
    Observable<ResponseBody> giftBuyOne(@Body GiftBuyOneBean bean);

    @POST("index.php/api/index/alsolike.html")
    Observable<ResponseBody> alsoLike(@Body AlsoLikeBean bean);

    @POST("index.php/api/index/searchsolution.html")
    Observable<ResponseBody> searchSolution(@Body SearchSolutionBean bean);

    @POST("index.php/api/index/searchsupply.html")
    Observable<ResponseBody> supplySearch(@Body SupplySearchBean bean);

    @POST("index.php/api/index/searchpurchase.html")
    Observable<ResponseBody> qiugouSearch(@Body QiuGouBean bean);

    @POST("index.php/api/index/searchpurchase.html")
    Observable<ResponseBody> knowledge(@Body KnowledgeBean bean);

    @POST("index.php/api/payment/pay.html")
    Observable<ResponseBody> supplyPay(@Body SupplyPayBean bean);


}

