package com.app.android.homestay.activity;

import android.content.Intent;
import android.view.View;

import com.app.android.homestay.AdminMainActivity;
import com.app.android.homestay.R;
import com.app.android.homestay.base.BaseActivity;

/**
 * 管理员登录页
 */
public class AdministratorLoginActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_administrator_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, UserLoginActivity.class));
            }
        });


        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, RegisterActivity.class);
                intent.putExtra("title", "管理员注册");
                startActivity(intent);
            }
        });

        findViewById(R.id.userLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, AdminMainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }
}