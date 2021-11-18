package com.app.android.homestay.bean;

import java.util.List;

public class FeedBackInfoBean {
    private List<FeedBackInfo> list;

    public FeedBackInfoBean(List<FeedBackInfo> list) {
        this.list = list;
    }

    public List<FeedBackInfo> getList() {
        return list;
    }

    public void setList(List<FeedBackInfo> list) {
        this.list = list;
    }
}
