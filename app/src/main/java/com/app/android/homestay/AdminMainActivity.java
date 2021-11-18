package com.app.android.homestay;

import android.content.Intent;
import android.view.View;

import com.app.android.homestay.activity.AdminHouseListActivity;
import com.app.android.homestay.activity.AdminFeedBookActivity;
import com.app.android.homestay.activity.AdminOrderListActivity;
import com.app.android.homestay.activity.AdminUserListActivity;
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
//                startActivity(new Intent(mActivity, HouseListActivity.class));
                startActivity(new Intent(mActivity, AdminHouseListActivity.class));
            }
        });

        findViewById(R.id.float2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, AdminOrderListActivity.class));
            }
        });

        findViewById(R.id.float3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, AdminFeedBookActivity.class));
            }
        });

        findViewById(R.id.float4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(mActivity, AdminUserListActivity.class));
            }
        });

    }

    @Override
    protected void initData() {

    }
}