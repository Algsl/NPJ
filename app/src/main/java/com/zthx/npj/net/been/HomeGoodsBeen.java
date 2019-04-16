package com.zthx.npj.net.been;

public class HomeGoodsBeen {
    private String goodsPic;
    private String goodsTitle;
    private String goodsNewPrice;
    private String goodsOldPrice;

    public String getGoodsPic() {
        return goodsPic;
    }

    @Override
    public String toString() {
        return "HomeGoodsBeen{" +
                "goodsPic='" + goodsPic + '\'' +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", goodsNewPrice='" + goodsNewPrice + '\'' +
                ", goodsOldPrice='" + goodsOldPrice + '\'' +
                ", mallPic='" + mallPic + '\'' +
                ", mallName='" + mallName + '\'' +
                '}';
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsNewPrice() {
        return goodsNewPrice;
    }

    public void setGoodsNewPrice(String goodsNewPrice) {
        this.goodsNewPrice = goodsNewPrice;
    }

    public String getGoodsOldPrice() {
        return goodsOldPrice;
    }

    public void setGoodsOldPrice(String goodsOldPrice) {
        this.goodsOldPrice = goodsOldPrice;
    }

    public String getMallPic() {
        return mallPic;
    }

    public void setMallPic(String mallPic) {
        this.mallPic = mallPic;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    private String mallPic;
    private String mallName;
}
