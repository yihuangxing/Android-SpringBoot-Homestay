package com.app.android.homestay.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.app.android.homestay.Config;
import com.app.android.homestay.R;
import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.bean.UserInfo;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class UserUpdatePwdActivity extends BaseActivity {
    private EditText password;
    private EditText newpwd;
    private EditText newpwd1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_update_pwd;
    }

    @Override
    protected void initView() {
        password = findViewById(R.id.password);
        newpwd = findViewById(R.id.newpwd);
        newpwd1 = findViewById(R.id.newpwd1);

    }

    @Override
    protected void setListener() {

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = password.getText().toString().trim();
                String newpwdstr = newpwd.getText().toString().trim();
                String newpwd1str = newpwd1.getText().toString().trim();
                if (TextUtils.isEmpty(pwd)) {
                    BaseToast("请输入原始密码");
                } else if (TextUtils.isEmpty(newpwdstr)) {
                    BaseToast("请输入新密码");
                } else if (!Utils.isPwd(newpwdstr)) {
                    BaseToast("密码格式不合法");
                } else if (TextUtils.isEmpty(newpwd1str)) {
                    BaseToast("请输入确定密码");
                } else if (!Utils.isPwd(newpwd1str)) {
                    BaseToast("确定密码不合法");
                } else if (!newpwdstr.equals(newpwd1str)) {
                    BaseToast("两次密码不一致");
                } else {
                    UserInfo userInfo = Config.getUserInfo();
                    if (null != userInfo) {
                        updatePwd(userInfo.getUid(), userInfo.getUsername(), newpwdstr);
                    }

                }
            }
        });

    }

    private void updatePwd(int uid, String username, String newpwdstr) {
        OkGo.<String>post(Config.UPDATE_PWD_URL)
                .params("username", username)
                .params("password", newpwdstr)
                .params("uid", uid)
                .execute(new HttpStringCallback(this) {

                    @Override
                    protected void onSuccess(String msg, String response) {
                        BaseToast(msg);
                        setResult(300);
                        finish();
                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);

                    }
                });
    }

    @Override
    protected void initData() {

    }
}