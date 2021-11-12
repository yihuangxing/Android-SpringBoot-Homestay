package com.app.android.homestay.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
    public static <T> T parseJson(String json, Class<T> type){
        T result = null;
        if (!TextUtils.isEmpty(json)) {
            Gson gson = new GsonBuilder().create();
            try {
                result = gson.fromJson(json, type);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                gson = null;
            }
        }
        return result;
    }
}
