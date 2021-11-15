package com.app.android.homestay.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.app.android.homestay.AdminMainActivity;
import com.app.android.homestay.Constants;
import com.app.android.homestay.R;
import com.app.android.homestay.UserMainActivity;
import com.app.android.homestay.base.BaseActivity;
import com.app.android.homestay.bean.UserInfo;
import com.app.android.homestay.http.HttpStringCallback;
import com.app.android.homestay.utils.GsonUtils;
import com.lzy.okgo.OkGo;

/**
 * 用户登录
 */
public class UserLoginActivity extends BaseActivity {
    private EditText username, password;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_login;
    }

    @Override
    protected void initView() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    @Override
    protected void setListener() {

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, RegisterActivity.class);
                intent.putExtra("title", "用户注册");
                startActivity(intent);
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mActivity, UserMainActivity.class);
                startActivity(intent);
                String name = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    BaseToast("请输入用户名");
                } else if (TextUtils.isEmpty(pwd)) {
                    BaseToast("请输入密码");
                } else {
                    login(name, pwd);
                }

            }
        });

    }

    @Override
    protected void initData() {

    }

    private void login(String username, String pwd) {
        OkGo.<String>get(Constants.LOGIN_URL)
                .params("username", username)
                .params("password", pwd)
                .params("identity", 0)
                .execute(new HttpStringCallback(mActivity) {
                    @Override
                    protected void onSuccess(String msg, String response) {
                        UserInfo userInfo = GsonUtils.parseJson(response, UserInfo.class);
                        Constants.setUserInfo(userInfo);
                        startActivity(new Intent(mActivity, UserMainActivity.class));
                        BaseToast(msg);
                        finish();
                    }

                    @Override
                    protected void onError(String response) {
                        BaseToast(response);
                    }
                });
    }

}