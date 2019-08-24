package com.zthx.npj.net.been;

import java.util.ArrayList;

public class AlsoLikeResponseBean extends BaseReponseBean {
   public class DataBean{
       private long id;
       private String goods_name;
       private String goods_img;
       private String member_price;
       private String user_price;
       private long sold;

       public long getId() {
           return id;
       }

       public void setId(long id) {
           this.id = id;
       }

       public String getGoods_name() {
           return goods_name;
       }

       public void setGoods_name(String goods_name) {
           this.goods_name = goods_name;
       }

       public String getGoods_img() {
           return goods_img;
       }

       public void setGoods_img(String goods_img) {
           this.goods_img = goods_img;
       }

       public String getMember_price() {
           return member_price;
       }

       public void setMember_price(String member_price) {
           this.member_price = member_price;
       }

       public String getUser_price() {
           return user_price;
       }

       public void setUser_price(String user_price) {
           this.user_price = user_price;
       }

       public long getSold() {
           return sold;
       }

       public void setSold(long sold) {
           this.sold = sold;
       }
   }
   private ArrayList<DataBean> data;

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }
}
