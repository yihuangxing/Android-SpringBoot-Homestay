package com.app.android.homestay.bean;

import java.util.List;

public class OderInfoBean {
    private List<OrderInfo> list;

    public OderInfoBean(List<OrderInfo> list) {
        this.list = list;
    }

    public List<OrderInfo> getList() {
        return list;
    }

    public void setList(List<OrderInfo> list) {
        this.list = list;
    }
}
