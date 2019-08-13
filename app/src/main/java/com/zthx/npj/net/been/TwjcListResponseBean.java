package com.zthx.npj.net.been;

import java.util.ArrayList;

public class TwjcListResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private long crop_id;
        private String title;
        private long create_time;
        private long update_time;
        private boolean isSelected;
        public class List{
            private long id;
            private String title;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        private ArrayList<List> list;

        public ArrayList<List> getList() {
            return list;
        }

        public void setList(ArrayList<List> list) {
            this.list = list;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getCrop_id() {
            return crop_id;
        }

        public void setCrop_id(long crop_id) {
            this.crop_id = crop_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public long getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(long update_time) {
            this.update_time = update_time;
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
