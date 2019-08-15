package com.zthx.npj.net.been;

import java.util.ArrayList;

public class CategoryResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private String name;
        private String image;
        private boolean isSelected;


        public class Child{
            private long id;
            private String name;
            private String image;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        private ArrayList<Child> child;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public ArrayList<Child> getChild() {
            return child;
        }

        public void setChild(ArrayList<Child> child) {
            this.child = child;
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
