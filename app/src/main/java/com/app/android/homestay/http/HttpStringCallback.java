package com.app.android.homestay.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.Window;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class HttpStringCallback extends StringCallback {

    private ProgressDialog dialog;
    private Activity mActivity;

    protected HttpStringCallback(Activity activity) {
        if (null != activity) {
            this.mActivity = activity;
            dialog = new ProgressDialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("请求网络中...");
            dialog.show();
        }
    }

    @Override
    public void onSuccess(Response<String> response) {
        if (!TextUtils.isEmpty(response.body())) {
            try {
                JSONObject jsonObject = new JSONObject(response.body());
                if (jsonObject.optInt("code") == 200) {
                    if (jsonObject.optJSONObject("data") == null) {
                        onError("接口返回数据错误");
                    } else {
                        onSuccess(jsonObject.optString("msg"), jsonObject.optJSONObject("data").toString());
                    }

                } else {
                    onError(jsonObject.optString("msg"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onError(Response<String> response) {
        super.onError(response);

        onError(response.getException().toString());
    }

    @Override
    public void onFinish() {
        if (mActivity != null) {
            if (dialog != null && dialog.isShowing() && !mActivity.isFinishing()) {
                dialog.dismiss();
            }
        }
    }

    protected abstract void onSuccess(String msg, String response);

    protected abstract void onError(String response);
}
