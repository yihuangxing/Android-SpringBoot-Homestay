package com.app.android.homestay.bean;

import java.util.List;

public class CollectionBean {
    private List<CollectionInfo> list;

    public CollectionBean(List<CollectionInfo> list) {
        this.list = list;
    }

    public List<CollectionInfo> getList() {
        return list;
    }

    public void setList(List<CollectionInfo> list) {
        this.list = list;
    }
}
