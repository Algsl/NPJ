package com.zthx.npj.net.been;

import java.util.ArrayList;

public class CommonListResponseBean extends BaseReponseBean{
    public class DataBean{
        public class List{
            private long id;
            private long user_id;
            private long goods_id;
            private long store_id;
            private String content;
            private ArrayList<String> img;
            private long status;
            private long create_time;
            private long type;
            private long goods_star;
            private long logistics_star;
            private long service_star;
            private long is_reply;
            private String nick_name;
            private String head_img;

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getHead_img() {
                return head_img;
            }

            public void setHead_img(String head_img) {
                this.head_img = head_img;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getUser_id() {
                return user_id;
            }

            public void setUser_id(long user_id) {
                this.user_id = user_id;
            }

            public long getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(long goods_id) {
                this.goods_id = goods_id;
            }

            public long getStore_id() {
                return store_id;
            }

            public void setStore_id(long store_id) {
                this.store_id = store_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public ArrayList<String> getImg() {
                return img;
            }

            public void setImg(ArrayList<String> img) {
                this.img = img;
            }

            public long getStatus() {
                return status;
            }

            public void setStatus(long status) {
                this.status = status;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }

            public long getGoods_star() {
                return goods_star;
            }

            public void setGoods_star(long goods_star) {
                this.goods_star = goods_star;
            }

            public long getLogistics_star() {
                return logistics_star;
            }

            public void setLogistics_star(long logistics_star) {
                this.logistics_star = logistics_star;
            }

            public long getService_star() {
                return service_star;
            }

            public void setService_star(long service_star) {
                this.service_star = service_star;
            }

            public long getIs_reply() {
                return is_reply;
            }

            public void setIs_reply(long is_reply) {
                this.is_reply = is_reply;
            }
        }
        public class List2{
            private long id;
            private long com_id;
            private String content;
            private ArrayList<String> img;
            private long goods_star;
            private long status;
            private long create_time;

            public long getGoods_star() {
                return goods_star;
            }

            public void setGoods_star(long goods_star) {
                this.goods_star = goods_star;
            }

            public long getStatus() {
                return status;
            }

            public void setStatus(long status) {
                this.status = status;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public long getCom_id() {
                return com_id;
            }

            public void setCom_id(long com_id) {
                this.com_id = com_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public ArrayList<String> getImg() {
                return img;
            }

            public void setImg(ArrayList<String> img) {
                this.img = img;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }
        }

        private List list;
        private ArrayList<List2> list2;

        public List getList() {
            return list;
        }

        public void setList(List list) {
            this.list = list;
        }

        public ArrayList<List2> getList2() {
            return list2;
        }

        public void setList2(ArrayList<List2> list2) {
            this.list2 = list2;
        }
    }

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
