package com.app.android.homestay.fragment;


import androidx.recyclerview.widget.RecyclerView;

import com.app.android.homestay.R;
import com.app.android.homestay.adapter.HomeAdapter;
import com.app.android.homestay.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private RecyclerView recyclerview;
    private HomeAdapter mHomeAdapter;
    private List<String> mStringList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        recyclerview = mRootView.findViewById(R.id.recyclerview);

        mHomeAdapter = new HomeAdapter();

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

        recyclerview.setAdapter(mHomeAdapter);

        mStringList.add("1111");
        mStringList.add("1111");
        mStringList.add("1111");
        mStringList.add("1111");
        mHomeAdapter.setNewInstance(mStringList);

    }
}