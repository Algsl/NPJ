package com.zthx.npj.net.been;

import java.util.ArrayList;

public class GoodsCateResponseBean extends BaseReponseBean{
    public class DataBean{
        private long id;
        private String name;
        public class Child{
            private long id;
            private String name;

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
