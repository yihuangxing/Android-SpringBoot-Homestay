package com.app.android.homestay.bean;

import java.util.List;

public class HouseBean {
    private List<HouseInfo> list;

    public HouseBean(List<HouseInfo> list) {
        this.list = list;
    }

    public List<HouseInfo> getList() {
        return list;
    }

    public void setList(List<HouseInfo> list) {
        this.list = list;
    }
}
