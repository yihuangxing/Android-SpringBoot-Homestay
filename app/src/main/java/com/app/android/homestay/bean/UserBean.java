package com.app.android.homestay.bean;

import java.util.List;

public class UserBean {
    private List<UserInfo> list;

    public UserBean(List<UserInfo> list) {
        this.list = list;
    }

    public List<UserInfo> getList() {
        return list;
    }

    public void setList(List<UserInfo> list) {
        this.list = list;
    }
}
