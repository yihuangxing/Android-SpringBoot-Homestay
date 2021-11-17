package com.app.android.homestay;


import com.app.android.homestay.bean.UserInfo;

public class Config {

    public final static String BASE_URL = "http://192.168.14.13:8080";

    public static UserInfo sUserInfo;

    public static UserInfo getUserInfo() {
        return sUserInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        sUserInfo = userInfo;
    }

    //注册
    public final static String REGISTER_URL = BASE_URL + "/user/register";

    //登录
    public final static String LOGIN_URL = BASE_URL + "/user/login";

    //添加商品
    public final static String HOUSE_ADD_URL = BASE_URL + "/house/add";

    //查询房子列表
    public final static String HOUSE_LIST_URL = BASE_URL + "/house/houseAll";

    //删除房子信息
    public final static String DEL_HOUSE_URL = BASE_URL + "/house/del";

    //修改房子信息
    public final static String HOUSE_UPDATE_URL = BASE_URL + "/house/update";

    //添加到订单
    public final static String ADD_ORDER_URL = BASE_URL + "/order/add";


    //查询订单
    public final static String QUERY_ORDER_URL = BASE_URL + "/order/query";


    //查询注册用户
    public final static String REGISTER_USER_URL = BASE_URL + "/user/query";


    //修改密码
    public final static String UPDATE_PWD_URL = BASE_URL + "/user/pwd";


    //删除订单
    public final static String ORDER_DEL_URL = BASE_URL + "/order/del";


    //收藏
    public final static String COLLECTION_URL = BASE_URL + "/collection/add";


    //收藏列表
    public final static String COLLECTION_LIST_URL = BASE_URL + "/collection/query";


    //取消收藏
    public final static String CANCEL_COLLECTION_URL = BASE_URL + "/collection/del";


    //修改用户信息
    public final static String EDIT_USER_URL = BASE_URL + "/user/edit";


}
