package com.app.android.homestay.activity;

import androidx.recyclerview.widget.RecyclerView;

import com.app.android.homestay.R;
import com.app.android.homestay.adapter.AdminUserListAdapter;
import com.app.android.homestay.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理
 */
public class AdminUserListActivity extends BaseActivity {
    private AdminUserListAdapter mListAdapter;
    private RecyclerView mRecyclerView;

    private List<String> mStringList =new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected void initView() {
        mRecyclerView =findViewById(R.id.recyclerview);

        mStringList.add("11111");
        mStringList.add("11111");
        mStringList.add("11111");
        mStringList.add("11111");
        mStringList.add("11111");
        mStringList.add("11111");
        mStringList.add("11111");
        mStringList.add("11111");

        mListAdapter =new AdminUserListAdapter();
        mListAdapter.setNewInstance(mStringList);

        mRecyclerView.setAdapter(mListAdapter);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }
}