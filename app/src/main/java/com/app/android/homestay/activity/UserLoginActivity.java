package com.app.android.homestay.activity;

import android.content.Intent;
import android.view.View;

import com.app.android.homestay.R;
import com.app.android.homestay.base.BaseActivity;

/**
 * 用户登录
 */
public class UserLoginActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_login;
    }

    @Override
    protected void initView() {

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

    }

    @Override
    protected void initData() {

    }
}