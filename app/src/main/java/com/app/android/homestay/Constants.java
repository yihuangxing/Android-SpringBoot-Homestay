package com.app.android.homestay;


import com.app.android.homestay.bean.UserInfo;

public class Constants {

    public static UserInfo sUserInfo;

    public static UserInfo getUserInfo() {
        return sUserInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        sUserInfo = userInfo;
    }

    public final static String BASE_URL = "http://192.168.14.13:8080";

    //注册
    public final static String REGISTER_URL = BASE_URL + "/user/register";

    //登录
    public final static String LOGIN_URL = BASE_URL + "/user/login";


}
