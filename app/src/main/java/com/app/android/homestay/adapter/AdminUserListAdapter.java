package com.app.android.homestay.adapter;

import androidx.annotation.NonNull;

import com.app.android.homestay.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;


public class AdminUserListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public AdminUserListAdapter() {
        super(R.layout.admin_user_list_item);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, String s) {

    }
}
