package com.app.android.homestay;

import android.content.Intent;
import android.view.View;

import com.app.android.homestay.activity.HouseListActivity;
import com.app.android.homestay.activity.OrderListActivity;
import com.app.android.homestay.activity.UserListActivity;
import com.app.android.homestay.base.BaseActivity;

public class AdminMainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_admin_main;
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void setListener() {
        findViewById(R.id.float1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, HouseListActivity.class));
            }
        });

        findViewById(R.id.float2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, OrderListActivity.class));
            }
        });

        findViewById(R.id.float3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, OrderListActivity.class));
            }
        });

        findViewById(R.id.float4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, UserListActivity.class));
            }
        });

    }

    @Override
    protected void initData() {

    }
}