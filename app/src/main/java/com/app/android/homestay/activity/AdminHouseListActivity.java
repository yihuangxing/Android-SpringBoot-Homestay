package com.app.android.homestay.activity;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.app.android.homestay.R;
import com.app.android.homestay.adapter.AdminHouseListAdapter;
import com.app.android.homestay.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 房间管理
 */
public class AdminHouseListActivity extends BaseActivity {
    private AdminHouseListAdapter mAdminHouseListAdapter;
    private List<String> mStringList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TextView add;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_admin_house_list;
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        add = findViewById(R.id.add);

    }

    @Override
    protected void setListener() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivityForResult(new Intent(AdminHouseListActivity.this,AdminAddHouseActivity.class),200);
            }
        });

    }

    @Override
    protected void initData() {

        mAdminHouseListAdapter = new AdminHouseListAdapter();
        mStringList.add("1111");
        mStringList.add("1111");
        mStringList.add("1111");
        mStringList.add("1111");
        mStringList.add("1111");

        mAdminHouseListAdapter.setNewInstance(mStringList);
        mRecyclerView.setAdapter(mAdminHouseListAdapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}